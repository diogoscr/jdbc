package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import java.util.*;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        List<Seller> list;
        Seller seller;

        System.out.println("==== TEST 1: Seller findById ====");
        seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n==== TEST 2: Seller findByDepartment ====");
        Department department = new Department(2, null);
        list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n==== TEST 3: Seller findAll ====");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }

        /*
        System.out.println("\n==== TEST 4: Seller insert ====");
        Seller newSeller = new Seller(null, "Diogo", "dio@gmail.com", new Date(), 4000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());


        System.out.println("\n==== TEST 5: Seller update ====");
        seller = sellerDao.findById(2);
        seller.setName("Malea Black");
        sellerDao.update(seller);
        System.out.println("Update completed");
        */

        System.out.println("\n==== TEST 5: Seller delete ====");
        System.out.println("Enter id for delete test (0 to exit): ");
        int id = sc.nextInt();
        while (id != 0) {
                boolean validId = false;
                for (Seller obj : list) {
                    if (obj.getId() == id) {
                        validId = true;
                        break;
                    }
                }
                if (validId) {
                    sellerDao.deleteById(id);
                    System.out.println("Id " + id + " deleted");
                }
                else {
                    System.out.println("Id invalid,");
                }
                System.out.println("Enter id");
                id = sc.nextInt();
        }
        System.out.println("Test completed");

        sc.close();
        DB.closeConnection();
    }
}