package hw3_5;

public class Car implements Runnable {
    private static int CARS_COUNT = 0;
    private Race race;
    private int speed;
    private String name;

    String getName() {
        return name;
    }

    int getSpeed() {
        return speed;
    }

    Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            Main.start.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Main.start.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        // участник закончил гонку
        try {
            Main.finish.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}