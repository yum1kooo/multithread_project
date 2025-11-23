package org.example.Service;

public class Delivery {
    private static Delivery INSTANCE;

    private Delivery() {}

    public static Delivery getInstance(){
        if(INSTANCE == null){
            synchronized (Delivery.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Delivery();
                }
            }
        }
        return INSTANCE;
    }


    public void generateNewOrders(int id){
        System.out.println("User " + id + " want pay order");

    }

    public void processingOrders(){

    }
}

