package org.example.Service;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Delivery {
    private static Delivery INSTANCE;
    BlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
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
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("USER - Пользователь " + id + " хочет оплатить заказ");
            queue.offer(id);
        }
    }

    public void processingOrders(){
        while (true) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ADMIN - Пришел новый заказ! ID "  + queue.peek());
            try {
                System.out.println("ADMIN - Обрабатываю заказ ID " +  queue.remove());
                System.out.println("ADMIN - Заказ обработан текущая очередь " + queue.size());
            } catch (Exception e) {
                System.out.println("ADMIN - Заказ не найден");
            }
        }
    }
}

