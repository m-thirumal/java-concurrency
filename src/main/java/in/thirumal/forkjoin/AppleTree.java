package in.thirumal.forkjoin;

import java.util.concurrent.TimeUnit;

public class AppleTree {
	
	private String treeLabel;
	private int numberOfApples;
	
	public AppleTree(String treeLabel) {
		this.treeLabel = treeLabel;
		this.numberOfApples = 3;
	}
	
	public int pickApples(String workerName) {
		System.out.printf("%s started picking apples from %s\n", workerName, treeLabel);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s picked %d 🍏s from %s \n", workerName, numberOfApples, treeLabel);
		return numberOfApples;
	}

	public static AppleTree[] newTreeGranden(int size) {
		AppleTree[] appleTrees = new AppleTree[size];
		for (int i = 0 ; i < size ; i++) {
			appleTrees[i] = new AppleTree("🌳#" + i);
		}
		return appleTrees;
	}
	
}