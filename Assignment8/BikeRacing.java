import java.util.*;

class Race extends Thread {
    private String name;
    private int startTime;
    private int endTime;
    private int totalTime;

    static final Object lock = new Object();

    Race(String name) {
        this.name = name;
    }

    public String getNameRacer() {
        return this.name;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void run() {
        try {
            System.out.println(name + " is ready to race...");

            synchronized (lock) {
                lock.wait(); 
            }

            startTime = (int) System.currentTimeMillis();

            for (int i = 1; i <= 10; i++) {
                try {
                    int sleepTime = (int) (Math.random() * 100);
                    Thread.sleep(sleepTime);
                    if (i == 10) {
                        endTime = (int) System.currentTimeMillis();
                        totalTime = endTime - startTime;
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void WinnerList(Race[] Racer) {
        for (int i = 0; i < Racer.length - 1; i++) {
            for (int j = i + 1; j < Racer.length; j++) {
                int val1 = Racer[i].getTotalTime();
                int val2 = Racer[j].getTotalTime();
                if (val1 > val2) {
                    Race temp = Racer[i];
                    Racer[i] = Racer[j];
                    Racer[j] = temp;
                }
            }
        }
    }

    public static void display(Race[] Racer) {
        for (int i = 0; i < Racer.length; i++) {
            System.out.print("Rank :" + (i + 1) + " Name : " + Racer[i].getNameRacer());
            System.out.println(" Time taken : " + Racer[i].getTotalTime());
        }
    }
}

public class BikeRacing {
    public static void main(String args[]) {
        Race Racer[] = new Race[10];

        for (int i = 0; i < Racer.length; i++) {
            Racer[i] = new Race("Racer " + (i + 1));
        }

        try {
            System.out.println("Count Down Started....");
            for (int i = 10; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }

            System.out.println("GO");

            for (Race r : Racer) {
                r.start();
            }

            synchronized (Race.lock) {
                Race.lock.notifyAll(); 
            }

            for (Race r : Racer) {
                try {
                    r.join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            Race.WinnerList(Racer);
            Race.display(Racer);

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
