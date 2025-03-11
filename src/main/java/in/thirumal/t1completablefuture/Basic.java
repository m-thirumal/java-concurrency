/**
 * 
 */
package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * Key Features:

    Non-blocking: Doesn’t block the main thread.
    Chaining: Use thenApply(), thenAccept(), etc., for sequential processing.
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
//			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//				try {
//					Thread.sleep(5000);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				return "Hi Thirumal";
//			});
//			
//			future.thenAccept(r -> System.out.println(r));
//			
//			future.join();
			
			//
			CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() ->
				{
					System.out.println("Task running asynchronously");
					return 10;	
				})
			        .thenApplyAsync(result -> {
			        	System.out.println("Multiplication => " +result + " * " + 2);
			        	return result * 2;
			        })
			        .thenApplyAsync(result -> result + 5);

			future1.thenAccept(result -> System.out.println(result));
	}

}
