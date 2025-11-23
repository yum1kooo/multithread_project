package org.example.Service;

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
}
