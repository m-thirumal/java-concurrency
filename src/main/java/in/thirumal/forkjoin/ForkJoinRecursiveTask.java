/**
 * 
 */
package in.thirumal.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
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
		
		int result = forkJoinPool.invoke(pickFruitTask);
		
		System.out.println(result + " => Fruites are collected successfully");
		
	}

}

class PickFruitTask extends RecursiveTask<Integer>{
	
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
		int midpoint = startInclusive + (endInclusive - startInclusive) / 2;
		PickFruitTask leftPickFruitTask = new PickFruitTask(trees, startInclusive, midpoint);
		PickFruitTask rightPickFruitTask = new PickFruitTask(trees, midpoint + 1, endInclusive);
		
		rightPickFruitTask.fork();
		
		return leftPickFruitTask.compute() + rightPickFruitTask.join();
	}
	
	protected Integer doCompute() {
		return IntStream.rangeClosed(startInclusive, endInclusive)
				.map(i->trees[i].pickFruit())
				.sum();
	}
}
