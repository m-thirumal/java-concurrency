/**
 * 
 */
package in.thirumal.forkjoin;

import java.io.Serializable;
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
			TimeUnit.SECONDS.sleep(3);
			System.out.printf("%s picked %d 🍏s from %s \n", fruitPicker, fruitCount, treeName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return fruitCount;
	}
	
	
	
}
