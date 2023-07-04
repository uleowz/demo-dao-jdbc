package model.dao.impl;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



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
                 Department dep = new Department();
                 dep.setId(rs.getInt("DepartmentId"));
                 dep.setName(rs.getString("DepName"));

                 Seller sell = new Seller();
                 sell.setName(rs.getString("Name"));
                 sell.setId(rs.getInt("Id"));
                 sell.setBaseSalary(rs.getDouble("BaseSalary"));
                 sell.setEmail(rs.getString("Email"));
                 sell.setBirthDate(rs.getDate("BirthDate"));
                 sell.setDepartment(dep);
                 return sell;
             }
             return null;

         }
         catch (SQLException e) {
             throw new RuntimeException(e.getMessage());
         }

    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
