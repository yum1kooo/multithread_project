package org.example.Service;

import org.example.User.User;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Delivery {
    private static Delivery INSTANCE;
    BlockingDeque<User> queue = new LinkedBlockingDeque<>();
    private Delivery() {}
    User user = new User();

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


    public void generateNewOrders(){
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            User newUserForOrder = new User();
            System.out.println("USER - Пользователь " + newUserForOrder.getId() + " хочет оплатить заказ");
            queue.offer(newUserForOrder);
        }
    }

    public void processingOrders(){
        while (true) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println("ADMIN - Пришел новый заказ! ID "  + queue.element().getId());
            } catch (Exception e) {
                System.out.println("Нету новых заказов");
            }
            try {
                System.out.println("ADMIN - Обрабатываю заказ ID " +  queue.remove().getId());
                System.out.println("ADMIN - Заказ обработан текущая очередь " + queue.size());
            } catch (Exception e) {
                System.out.println("ADMIN - Заказ не найден");
            }
        }
    }



}

