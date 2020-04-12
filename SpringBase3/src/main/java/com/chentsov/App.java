package com.chentsov;

import com.chentsov.entity.Client;
import com.chentsov.entity.Order;
import com.chentsov.entity.Product;
import com.chentsov.repository.ClientRepository;
import com.chentsov.repository.OrderRepository;
import com.chentsov.repository.ProductRepository;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;


public class App {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private App(ClientRepository clientRepository,
                OrderRepository orderRepository,
                ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        final EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();


        App app = new App(new ClientRepository(factory),
                new OrderRepository(factory),
                new ProductRepository(factory));
        app.printConsoleInfo();

        try {
            app.performDBAction();
        } finally {
            factory.close();
            app.clientRepository.shutdown();
            app.productRepository.shutdown();
            app.orderRepository.shutdown();
        }
    }

    private void printConsoleInfo() {
        System.out.println("Please enter command number and ID, where:");
        System.out.println("1 = get Client orders");
        System.out.println("2 = get orders with product");
        System.out.println("3 = delete Client");
        System.out.println("4 = delete Product");
        System.out.println("0 = exit");
        System.out.println("Sample query: \n2 3 \nThis means: get purchasers for the product with id = 3\n");
    }

    private void performDBAction() {
        try {
            Scanner sc = new Scanner(System.in);
            int command_id;
            while ((command_id = sc.nextInt()) != 0) {
                long id = sc.nextLong();
                switch (command_id) {
                    case 1:
                        getClientOrders(id);
                        break;
                    case 2:
                        getOrderedProducts(id);
                        break;
                    case 3:
                        removeClient(id);
                        break;
                    case 4:
                        removeProduct(id);
                        break;
                    default:
                        System.out.println("Wrong command id!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong format!");
        }
    }

    private void getClientOrders(Long clientId) {
        List<Order> orders = orderRepository.getOrdersByClient(clientId);
        System.out.println("Found " + orders.size() + " orders:\n" + formatListContents(orders));
    }

    private void getOrderedProducts(Long productId) {
        List<Order> orders = orderRepository.getOrdersByProducts(productId);
        System.out.println("Found " + orders.size() + " orders:\n" + formatListContents(orders));
    }

    private void removeClient(Long clientId) {
        final Client client = clientRepository.removeClient(clientId);
        System.out.println(client == null
                ? "client not found"
                : "client " + client + " successfully deleted"
        );
    }

    private void removeProduct(Long productId) {
        final Product product = productRepository.removeProduct(productId);
        System.out.println(product == null
                ? "product not found"
                : "product " + product + " successfully deleted"
        );
    }

    private <T> String formatListContents(List<T> list) {
        final StringBuilder builder = new StringBuilder();
        list.forEach(item -> builder.append(item).append('\n'));
        return builder.toString();
    }

}
