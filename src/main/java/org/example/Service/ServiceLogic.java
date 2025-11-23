package org.example.Service;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ServiceLogic {
    CountDownLatch lacth = new CountDownLatch(2);
    Random rand = new Random();

    public void firtsInit(Bank bank, Delivery delivery) {
        System.out.println("Bank initialized");
        //try {
        //    Thread.sleep(rand.nextInt(3000));
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
        bank = Bank.getInstance();
        lacth.countDown();
        System.out.println("Bank ready");

        System.out.println("Delivery initialized");
        //try {
        //    Thread.sleep(rand.nextInt(3000));
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
        delivery = Delivery.getInstance();
        lacth.countDown();
        System.out.println("Delivery ready");
    }
}
