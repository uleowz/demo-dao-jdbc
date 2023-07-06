import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import javax.xml.soap.Detail;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("TESTE Seller FindById: ");
        Seller sellerFindOutById = sellerDao.findById(3);
        System.out.println(sellerFindOutById);

    }
}
