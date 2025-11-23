package org.example.User;

import org.example.Service.Bank;
import org.example.Service.Delivery;
import org.example.Service.ServiceLogic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Shop {
    public static void main(String[] args) {
        CountDownLatch lacth = new CountDownLatch(2);
        Bank bank = Bank.getInstance();
        Delivery delivery = Delivery.getInstance();
        ServiceLogic serviceLogic = new ServiceLogic();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        Semaphore semaphore = new Semaphore(15);
        serviceLogic.firtsInit(bank, delivery);

        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                    delivery.generateNewOrders();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                }
            });
        }


        for (int i = 0; i < delivery.getSizeQueue(); i++) {
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        delivery.processingOrders();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                }
            });

        }

        executor.shutdown();
    }
}
