/**
 * 
 */
package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

/**
 * @author Thirumal
 *
 */
public class CompletableFutureBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		CompletableFuture<String> sliceTomato = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("====> Slicing Tomatoes ///////////");
			return " Sliced Tomatoes";
		}, executorService);
		
		CompletableFuture<String> chopOnion = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("====> Chopping Onions ///////////");
			return " Chopped Onions";
		}, executorService);
		
		
		CompletableFuture<String> chopMeat = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("====> Chopping Meat ///////////");
			String meat = null;
			if (meat == null) {
				throw new RuntimeException("No meat");
			}
			return " Chopped Meat";
		}, executorService).handle((result, execption) ->{
			if (result == null) {
				System.err.println("There is no meat");
				return "";
			}
			return result;
		});
		
		CompletableFuture<String> prepIngridents = sliceTomato.thenCombine(chopOnion, String::concat).thenCombine(chopMeat, String::concat);
		
		
		CompletableFuture<String> prepPizza = prepIngridents.thenApply(toppings -> {
			System.out.println("Preparing Pizza !!!!");
			return "Raw Pizza with => " + toppings;
		});
		
		CompletableFuture<String> bakePizza = prepPizza.thenApply(rawPizza -> {
			System.out.println("Baked Pizza !!!!");
			return "Baked => " + rawPizza;
		});
		
		
		bakePizza.thenAccept(pizza -> System.out.println("Eating " + pizza));
		
		executorService.shutdown();
		
	}

}
