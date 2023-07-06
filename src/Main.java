import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import javax.xml.soap.Detail;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("TESTE Seller FindById: ");
        Seller sellerFindOutById = sellerDao.findById(3);
        System.out.println(sellerFindOutById);


        System.out.println();


        System.out.println("TESTE Seller FindByDepartment");
        Department dep = new Department(2, null);
        List<Seller> lista = sellerDao.findByDepartment(dep);
        for (Seller obj : lista) {
            System.out.println(obj);
        }

        System.out.println();


        System.out.println("TESTE Seller FindAll");
        lista = sellerDao.findAll();
        for (Seller obj : lista) {
            System.out.println(obj);
        }
    }
}
