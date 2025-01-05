package in.thirumal.parallelism;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.stream.IntStream;

public class BasicParallelism {
    
    public static void main(String[] args) {
        OffsetDateTime starTime = OffsetDateTime.now();
       IntStream.range(0, 100).parallel().forEach(i -> {
        System.out.println(Thread.currentThread().getName() + " processing number: " + i);
       });
       Duration duration = Duration.between(starTime, OffsetDateTime.now());
       System.out.println("Completed in " + duration.toMillis() + " ms");
    }
}
