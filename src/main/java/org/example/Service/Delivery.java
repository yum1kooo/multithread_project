package org.example.Service;

import org.example.User.User;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Delivery {
    private static Delivery INSTANCE;
    BlockingDeque<User> queue = new LinkedBlockingDeque<>(15);
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


    public synchronized void generateNewOrders() throws InterruptedException {

            while (queue.size() == 15){
                wait();
            }

            User newUserForOrder = new User();
            boolean added = queue.offer(newUserForOrder);
            if (!added) {
                System.out.println("Достигнуто МАКСИМАЛЬНОЕ КОЛ-ВО ЗАКАЗОВ. Предзаказ закрыт");
            } else {
                System.out.println("USER - Пользователь " + newUserForOrder.getId() + " хочет оплатить заказ");
            }

    }

    public synchronized void processingOrders() throws InterruptedException {

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            User user = queue.poll();


            try {
                System.out.println("ADMIN - Пришел новый заказ! ID "  + user.getId());
            } catch (Exception e) {
                System.out.println("ADMIN - Нету новых заказов");
            }
                System.out.println("ADMIN - Обрабатываю заказ ID " +  user.getId());
                System.out.println("ADMIN - Заказ обработан " + user.getId());

                notify();

    }

    public Integer getSizeQueue(){
        return queue.size();
    }
}

