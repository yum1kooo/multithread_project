package org.example.User;


import java.util.Random;

public class User {
    private int id;
    private String name;
    private double balance;
    String[] names = {
            "Alex", "Nikita", "Kirill", "Maksim", "Ivan",
            "Dmitry", "Sergey", "Oleg", "Vladimir", "Pavel",
            "Andrey", "Egor", "Timur", "Roman", "Artem",
            "Denis", "Ilya", "Yaroslav", "Gleb", "Konstantin",
            "Mikhail", "Fedor", "Matvey", "Bogdan", "Stanislav",
            "Vladislav", "Yuri", "Leonid", "Victor", "German",
            "Mark", "Philip", "Anton", "Stepan", "Arseniy",
            "Valery", "Grigory", "Savely", "Yan", "Platon",
            "Adam", "Radmir", "Robert", "Emil", "David",
            "Damir", "Rodion", "Semyon", "Eldar", "Miron"
    };
    Random rand = new Random();

    public User() {
        this.id = rand.nextInt(50);
        this.name =  names[rand.nextInt(names.length)];
        this.balance = rand.nextInt(1_000_000);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
