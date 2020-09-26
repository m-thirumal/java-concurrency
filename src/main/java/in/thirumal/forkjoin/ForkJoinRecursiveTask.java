/**
 * 
 */
package in.thirumal.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Thirumal
 *
 */
public class ForkJoinRecursiveTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree[] trees = Tree.newTree(12);
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		
		PickFruitTask pickFruitTask = new PickFruitTask(trees, 0, trees.length - 1);
		long startTime  = System.currentTimeMillis();
		
//		int result = forkJoinPool.invoke(pickFruitTask);
//		System.out.println(result + " => Fruites are collected successfully");
//		
		
//		forkJoinPool.execute(pickFruitTask);
//	
//		
//		try {
//			forkJoinPool.awaitTermination(2, TimeUnit.MINUTES);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		//==============With Future and submit
		Future<Integer> result = forkJoinPool.submit(pickFruitTask);
		try {
			forkJoinPool.awaitTermination(2, TimeUnit.MINUTES);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(result.get() + " => Fruites are collected successfully");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("Total Time taken: " + (System.currentTimeMillis() - startTime));
		
	}

}

class PickFruitTask extends RecursiveTask<Integer> { //Use "RecursiveAction" if don't want to return the result or RecursiveTas<Void>
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5422064475206899697L;
	private Tree[] trees;
	private int startInclusive;
	private int endInclusive;
	
	private int threashold = 2;

	public PickFruitTask(Tree[] trees, int startInclusive, int endInclusive) {
		this.trees = trees;
		this.startInclusive = startInclusive;
		this.endInclusive = endInclusive;
	}
	
	@Override
	protected Integer compute() {
		if (endInclusive - startInclusive < threashold) {
			return doCompute();
		}
//		if (startInclusive > trees.length/2) {
//			int i = 4 / 0;
//			completeExceptionally(new CustomException());
//		}
		
		int midpoint = startInclusive + (endInclusive - startInclusive) / 2;
		PickFruitTask leftPickFruitTask = new PickFruitTask(trees, startInclusive, midpoint);
		PickFruitTask rightPickFruitTask = new PickFruitTask(trees, midpoint + 1, endInclusive);
		
		rightPickFruitTask.fork(); //Compute: is synchronous and Fork: is asynchronous. Order must be fork and compute, otherwise it will execute in synchronous
		
		//rightPickFruitTask.cancel(true);
		
		return leftPickFruitTask.compute() + rightPickFruitTask.join();
	}
	
	protected Integer doCompute() {
		return IntStream.rangeClosed(startInclusive, endInclusive)
				.map(i->trees[i].pickFruit())
				.sum();
	}
}

class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538473976148737307L;
}
