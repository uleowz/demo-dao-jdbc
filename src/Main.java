import model.entities.Department;
import model.entities.Seller;

import javax.xml.soap.Detail;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Department obj = new Department(1, "nome qualquer");
        System.out.println(obj);

        Seller seller = new Seller(1, "fulano", "fulano@gmail.com", new Date(), 2000.0, obj);
        System.out.println(seller);
        
    }
}
