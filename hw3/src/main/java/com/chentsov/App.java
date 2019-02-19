package com.chentsov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        App app = new App();
        app.printConsoleInfo();
        app.performDBAction(factory);

        factory.close();
    }

    private void printConsoleInfo() {
        System.out.println("Please enter command number and ID, where:");
        System.out.println("1 = get Customer purchases");
        System.out.println("2 = get Product purchasers");
        System.out.println("3 = delete Customer");
        System.out.println("4 = delete Product");
        System.out.println("0 = exit");
        System.out.println("Sample query: \n2 3 \nThis means: get purchasers for the product with id = 3\n");
    }

    private void performDBAction(SessionFactory factory) {
        Session session = null;
        try {
            Scanner sc = new Scanner(System.in);
            int command_id;
            while ((command_id = sc.nextInt()) != 0) {
                session = factory.openSession();
                long id = sc.nextLong();
                switch (command_id) {
                    case 1:
                        getCustomerPurchases(session, id);
                        break;
                    case 2:
                        getProductPurchasers(session, id);
                        break;
                    case 3:
                        deleteCustomer(session, id);
                        break;
                    case 4:
                        deleteProduct(session, id);
                        break;
                    default:
                        System.out.println("Wrong command id!");
                }
                session.close();
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong format!");
        } finally {
            session.close();
            factory.close();
        }
    }

    private void getCustomerPurchases(Session session, Long customerId) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        System.out.println(customer.getProducts());
        session.getTransaction().commit();
    }

    private void getProductPurchasers(Session session, Long productId) {
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        System.out.println(product.getCustomers());
        session.getTransaction().commit();
    }

    private void deleteCustomer(Session session, Long customerId) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        session.delete(customerId);
        session.getTransaction().commit();
        System.out.println("customer has been deleted");
    }

    private void deleteProduct(Session session, Long productId) {
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        session.delete(product);
        session.getTransaction().commit();
        System.out.println("product has been deleted");
    }

}
