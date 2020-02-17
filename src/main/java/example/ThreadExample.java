package example;

public class ThreadExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <999 ; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i );
                }
            }
        });

        //after java8 lambda
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i <999 ; i++) {                                     //task in thread
                System.out.println(Thread.currentThread().getName() + " " + i );
            }
        });

        thread1.setName("T1");
        thread2.setName("T2");

        thread1.setPriority(1);     //lowest priority
        thread2.setPriority(10);    //highest priority

        thread1.start();
        thread2.start();
    }
}
