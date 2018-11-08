package hw3_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class Main {
    private static final int CARS_COUNT = 4;
    static CountDownLatch start = new CountDownLatch(CARS_COUNT);
    static CountDownLatch finish = new CountDownLatch(CARS_COUNT);
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];


        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            new Thread(cars[i]).start();
        }

        try {
            start.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            finish.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}