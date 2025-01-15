package in.thirumal.Synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizeWithLock {
    public static void main(String[] args) {
      
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Counter counter = new Counter();

   for (int i = 0; i < 100000; i++) {  
            executorService.submit(() -> {
                counter.increment();
            });
        }
        try {
            executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Result: " + counter.getValue());
}
}

class Counter {
    
    private final Object lock = new Object();
    
    private int count = 0;

    public void increment() {
        count++;
    }
    public void decrement() {
        count--;
    }
    public int getValue() {
        return count;
    }
}