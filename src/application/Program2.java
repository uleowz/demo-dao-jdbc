package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

public class Program2 {
    public static void main (String[] args){
        System.out.println("TESTE Department Insert");
        DepartmentDao depDao = DaoFactory.createDepartmentDao();
        Department dep = new Department(null, "Recursos Humanos");
        depDao.insert(dep);
        System.out.println("OK");


        System.out.println();


        System.out.println("TESTE Department Update");
        dep.setName("RG -Recursos Humanos");
        dep.setId(5);
        depDao.update(dep);

    }

}
