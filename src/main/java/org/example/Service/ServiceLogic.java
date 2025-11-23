package org.example.Service;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class ServiceLogic {
    CountDownLatch lacth = new CountDownLatch(2);
    Random rand = new Random();
    int countOrder = 0;
    int orderCoast = 0;
    public void firtsInit(Bank bank, Delivery delivery) {
        System.out.println("Bank initialized");

        try {
            Thread.sleep(rand.nextInt(3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bank = Bank.getInstance();
        lacth.countDown();
        System.out.println("Bank ready");

        System.out.println("Delivery initialized");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter count order");
        try {
            Thread.sleep(rand.nextInt(3000));
        } catch (InterruptedException e) {
            System.out.println("error");
        }

        while (true) {
            try {
                countOrder = scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input");
                scan.nextLine();

            }
        }

        System.out.println("Please enter order coast");
        while (true) {
            try {
                orderCoast = scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input");
                scan.nextLine();
            }
        }
        delivery = Delivery.getInstance();
        lacth.countDown();
        System.out.println("Delivery ready");
    }

    public Integer getCountOrder() {
        return countOrder;
    }

    public Integer getOrderCoast() {
        return orderCoast;
    }
}
