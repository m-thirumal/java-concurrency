/**
 * 
 */
package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * 
 */
public class Basic {

	/**
	 * 
	 */
	public Basic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return "Hi Thirumal";
			});
			
			future.thenAccept(r -> System.out.println(r));
			
			future.join();
			
			//
			CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10)
			        .thenApplyAsync(result -> result * 2)
			        .thenApplyAsync(result -> result + 5);

			future1.thenAccept(result -> System.out.println(result));
	}

}
