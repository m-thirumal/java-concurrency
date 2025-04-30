/**
 * 
 */
package in.thirumal.t1completablefuture;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 */
public class CombineResultFromMultipleTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] num = {1, 3, 4, 5, 6};
		//Sum
		CompletableFuture<Integer>   sum = CompletableFuture.supplyAsync(() -> Arrays.stream(num).sum());
		//Multiply
		CompletableFuture<Integer> multiply = CompletableFuture.supplyAsync(() -> Arrays.stream(num).reduce((a, b) -> a * b).getAsInt());
		CompletableFuture<String> result = sum.thenCombine(multiply, (s, m) -> {
			return "Sum " + s + "\n Multiply" + m;
		});
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
