/**
 * 
 */
package in.thirumal.forkjoin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Thirumal
 *
 */
public class Tree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6786485032465445280L;

	public static Tree[] newTree(int size) {
		Tree[] trees = new Tree[size];
		for (int i = 0; i < size; i++) {
			trees[i] = new Tree("🌳#" + i);
		}
		return trees;
	}
	
	private String treeName;
	private int fruitCount;
	
	public Tree(String treeName) {
		super();
		this.treeName = treeName;
		this.fruitCount = 3;
	}
	
	public int pickFruit(String fruitPicker) {
		try {
			//System.out.printf("%s started picking apples from %s \n", workerName, treeLabel);
			TimeUnit.SECONDS.sleep(1);
			System.out.printf("%s picked %d 🍏s from %s \n", fruitPicker, fruitCount, treeName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return fruitCount;
	}

	public int pickFruit() {
		return pickFruit(toLabel(Thread.currentThread().getName()));
	}
	
	private String toLabel(String threadName) {
		HashMap<String, String> threadNameToLabel = new HashMap<>();
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-1", "Alice");
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-2", "Bob");
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-3", "Carol");
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-4", "Dan");
		
		return threadNameToLabel.getOrDefault(threadName, threadName);
	}
	
	
}
