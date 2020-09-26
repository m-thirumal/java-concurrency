/**
 * 
 */
package in.thirumal.forkjoin;

import java.util.List;
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
		Tree[] trees = Tree.newTree(6);
		
		Callable<Void> fruitPicker1 = createFruitPicker(trees, 0, 2, "Thirumal");
		Callable<Void> fruitPicker2 = createFruitPicker(trees, 2, 4, "Carol");
		Callable<Void> fruitPicker3 = createFruitPicker(trees, 4, 6, "Jasmine");
		
		//Fork
		ForkJoinPool.commonPool().invokeAll(List.of(fruitPicker1, fruitPicker2, fruitPicker3));
		//
		System.out.println("All fruites are picked");
	}
	
	public static Callable<Void> createFruitPicker(Tree[] trees, int fromIndexInclusive, int toIndexExclusive, String picker ) {
		return () -> {
			for (int i = fromIndexInclusive; i <toIndexExclusive; i++) {
				trees[i].pickFruit(picker);
			}
			return null;
		};		
	}

}
