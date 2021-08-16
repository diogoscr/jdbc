package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import java.util.*;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao depDao = DaoFactory.createDepartmentDao();
        List<Department> list;
        Department department;

        SellerDao sellerDao = DaoFactory.createSellerDao();
        List<Seller> sellerList;
        Seller seller;

        System.out.println("==== TEST 1: Seller findById ====");
        seller = sellerDao.findById(2);
        System.out.println(seller);

        System.out.println("\n==== TEST 2: Seller findByDepartment ====");
        department = new Department(4, null);
        sellerList = sellerDao.findByDepartment(department);
        for (Seller obj : sellerList) {
            System.out.println(obj);
        }

        System.out.println("\n==== TEST 3: Seller findAll ====");
        sellerList = sellerDao.findAll();
        for (Seller obj : sellerList) {
            System.out.println(obj);
        }

        System.out.println("\n==== TEST 4: Seller insert ====");
        Seller newSeller = new Seller(null, "Diogo", "dio@gmail.com", new Date(), 4000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("\n==== TEST 5: Seller update ====");
        seller = sellerDao.findById(2);
        seller.setName("Mary Black");
        sellerDao.update(seller);
        System.out.println("Update completed");

        System.out.println("\n==== TEST 6: Seller delete ====");
        System.out.println("Enter id for delete test (0 to exit): ");
        int id = sc.nextInt();
        while (id != 0) {
                boolean validId = false;
                for (Seller obj : sellerList) {
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


        System.out.println("\n==== TEST 7: Department findAll ====");
        list = depDao.findAll();
        for (Department obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n==== TEST 8: Department update ====");
        System.out.println("Enter id for update test (0 to exit): ");
        id = sc.nextInt();
        while (id != 0) {
                boolean validId = false;
                for (Department obj : list) {
                    if (obj.getId() == id) {
                        validId = true;
                        break;
                    }
                }
                if (validId) {
                    department = depDao.findById(id);
                    department.setName("xml");
                    depDao.update(department);
                    System.out.println("Update completed");
                }
                else {
                    System.out.println("Id invalid,");
                }
                System.out.println("Enter id");
                id = sc.nextInt();
        }
        System.out.println("update test completed");

        System.out.println("\n=== TEST 3: insert =======");
        Department newDepartment = new Department(null, "apex");
        depDao.insert(newDepartment);
        System.out.println("Inserted! New id: " + newDepartment.getId());

        System.out.println("\n=== TEST 5: delete =======");
        System.out.print("Enter id for delete test: ");
        id = sc.nextInt();
        depDao.deleteById(id);
        System.out.println("Delete completed");

        sc.close();
        DB.closeConnection();
    }
}