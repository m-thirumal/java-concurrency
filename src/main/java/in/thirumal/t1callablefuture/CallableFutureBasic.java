/**
 * 
 */
package in.thirumal.t1callablefuture;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Thirumal
 *
 */
public class CallableFutureBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Future<Integer> futureReturn = executorService.submit(new Callable<Integer>() { //Use "Void" in Callable and "?" in Future, if it doesn't return anything

			@Override
			public Integer call() throws Exception {
				int count = 0;
				for (int i = 0; i < 100; i++) {
					count++;
				}
				if (count < 100) { //Change the number to see execption
					throw new IOException("IO");
				}
				return count;
				
			}
		});
		
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println(futureReturn.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("==========" + e.getMessage());
		}

	}

}
