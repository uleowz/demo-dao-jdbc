package model.dao.impl;

import db.DB;
import model.dao.DepartmentDao;
import model.entities.Department;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConn();
            st = conn.prepareStatement("INSERT INTO department" +
                    "(Name) " +
                    "VALUES " +
                    "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }

            }
            else {throw new RuntimeException("Erro ao inserir Department!");}

        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Department instantiateDepartment(PreparedStatement st) {
        return null;
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try {
            conn = DB.getConn();
            st = conn.prepareStatement("UPDATE department " +
                    "SET Name = ?" +
                    "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            conn = DB.getConn();
            st = conn.prepareStatement("DELETE FROM department " +
                    "WHERE Id = ?");
            st.setInt(1, id);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());

        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
