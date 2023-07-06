package model.dao.impl;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st= null;
         ResultSet rs=null;
         try {
             conn = DB.getConn();
             st =  conn.prepareStatement("SELECT seller.*, department.Name as DepName " +
                     "FROM seller INNER JOIN department " +
                     "ON seller.DepartmentId = department.Id " +
                     "WHERE seller.id = ?");
             st.setInt(1, id);
             rs = st.executeQuery();
             if (rs.next()) {
                 Department dep = instantiateDepartment(rs);

                 Seller sell = instantiateSeller(rs, dep);
                 return sell;
             }
             return null;

         }
         catch (SQLException e) {
             throw new RuntimeException(e.getMessage());
         }
         finally {
             DB.closeStatement(st);
             DB.closeResultSet(rs);
         }

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
        Seller sell = new Seller();
        sell.setName(rs.getString("Name"));
        sell.setId(rs.getInt("Id"));
        sell.setBaseSalary(rs.getDouble("BaseSalary"));
        sell.setEmail(rs.getString("Email"));
        sell.setBirthDate(rs.getDate("BirthDate"));
        sell.setDepartment(dep);
        return sell;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConn();
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.DepartmentId = ? " +
                    "ORDER BY Name");
            st.setInt(1, department.getId());
            rs = st.executeQuery();
            List<Seller> sellerList = new ArrayList<Seller>();
            Map<Integer, Department> map = new HashMap<>();
                while (rs.next()) {
                    Department dep = map.get(rs.getInt("DepartmentId"));

                    if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                    }
                    Seller sell = instantiateSeller(rs, dep);
                    sellerList.add(sell);
            }
            return sellerList;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
}
