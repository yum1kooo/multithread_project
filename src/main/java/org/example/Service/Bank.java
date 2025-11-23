package org.example.Service;

import java.util.Random;

public class Bank {
    private static Bank INSTANCE_BANK;

    private Bank() {}

    public static Bank getInstance(){
        if(INSTANCE_BANK == null){
            synchronized (Delivery.class) {
                if(INSTANCE_BANK == null) {
                    INSTANCE_BANK = new Bank();
                }
            }
        }
        return INSTANCE_BANK;
    }


    public boolean getPaymentById(int id){
        Random rand = new Random();
        return rand.nextBoolean();
    }
}
