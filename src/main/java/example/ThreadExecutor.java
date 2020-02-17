package example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> steps());
        executorService.submit(() -> steps());

        executorService.shutdown();     //close of thread pool

    }

    private static void steps(){
        for (int i = 0; i <999 ; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i );
        }
    }
}
