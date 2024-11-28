/**
 * 
 */
package in.thirumal.forkjoin;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Thirumal
 *
 */
public class ForkJoinbasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppleTree[] trees = AppleTree.newTreeGranden(6);
		Callable<Void> applePicker1 = pickApplesFromTree(0, 2, trees, "Thirumal");
		Callable<Void> applePicker2 = pickApplesFromTree(2, 4, trees, "Tamil Vendhan");
		Callable<Void> applePicker3 = pickApplesFromTree(4, 6, trees, "K");
		
		ForkJoinPool.commonPool().invokeAll(Arrays.asList(applePicker1, applePicker2, applePicker3));
		System.out.println("All apples are picked...");
	}
	
	public static Callable<Void> pickApplesFromTree(int startInclusive, int endExclusive, AppleTree[] trees, String worker) {
		return () -> {
		for (int i = startInclusive ; i < endExclusive; i++) {
			trees[i].pickApples(worker);
		}
		return null;	
		};
	}
}
