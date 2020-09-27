/**
 * 
 */
package in.thirumal.t1.threadsafedatastructure;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Thirumal
 *
 */
public class ConcurrentHashMapBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		HashMap<Integer, Integer> hashMap = new HashMap<>();
		ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();

		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 100000; i++) {
			int j = i;
			executorService.submit(() -> hashMap.put(j, 9));
		}	
		executorService.shutdown();
		System.out.println("Waiting");
		while (!executorService.isTerminated()) {
			try {
				executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
	
		System.out.println(hashMap.size());
	}


}
