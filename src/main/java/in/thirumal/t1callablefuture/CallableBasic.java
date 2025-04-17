package in.thirumal.t1callablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableBasic {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> result = executorService.submit(() -> 4);
		executorService.shutdown();
		try {
			System.out.println(result.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
