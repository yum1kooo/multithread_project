package org.example;

import org.example.Service.Bank;
import org.example.Service.Delivery;
import org.example.Service.ServiceLogic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Shop {
    public static void main(String[] args) {
        CountDownLatch lacth = new CountDownLatch(2);
        Bank bank = Bank.getInstance();
        Delivery delivery = Delivery.getInstance();
        ServiceLogic serviceLogic = new ServiceLogic();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        serviceLogic.firtsInit(bank, delivery);

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    delivery.generateNewOrders();
                }
            });
        }

        executor.submit(new Runnable() {
            public void run() {
            delivery.processingOrders();
            }
        });
    }
}
