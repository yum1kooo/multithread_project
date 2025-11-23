package org.example;

import org.example.Service.Bank;
import org.example.Service.Delivery;
import org.example.Service.ServiceLogic;

import java.util.concurrent.CountDownLatch;

public class Shop {
    public static void main(String[] args) {
        CountDownLatch lacth = new CountDownLatch(2);
        Bank bank = Bank.getInstance();
        Delivery delivery = Delivery.getInstance();
        ServiceLogic serviceLogic = new ServiceLogic();

        serviceLogic.firtsInit(bank, delivery);


    }
}
