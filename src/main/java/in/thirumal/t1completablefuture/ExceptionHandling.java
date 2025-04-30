/**
 * 
 */
package in.thirumal.t1completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * 
 */
public class ExceptionHandling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
			if (new Random().nextBoolean()) {
				throw new RuntimeException("Throwing error");
			}
			return "Success Task";
		});
		
		task1.exceptionally(e -> "" + e.getMessage())
		.thenAccept(a -> System.out.println(a))
		.join();
	}

}
