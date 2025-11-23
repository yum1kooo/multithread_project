package org.example.User;

import org.example.Service.Bank;
import org.example.Service.Delivery;
import org.example.Service.ServiceLogic;

import java.util.Random;
import java.util.concurrent.*;

public class Shop {
    public static void main(String[] args) {
        CountDownLatch lacth = new CountDownLatch(2);
        Bank bank = Bank.getInstance();
        Delivery delivery = Delivery.getInstance();
        ServiceLogic serviceLogic = new ServiceLogic();
        serviceLogic.firtsInit(bank, delivery);

        ExecutorService executor = Executors.newFixedThreadPool(serviceLogic.getCountOrder());
        Semaphore semaphore = new Semaphore(serviceLogic.getCountOrder());

        Random random = new Random();


        for (int i = 0; i < serviceLogic.getCountOrder(); i++) {
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


        for (int i = 0; i < serviceLogic.getCountOrder(); i++) {
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        delivery.processingOrders(serviceLogic.getOrderCoast());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                }
            });

        }

        executor.shutdown();
        try {
            executor.awaitTermination(25, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("LOG - queue size is " + delivery.getSizeQueue());
    }
}
