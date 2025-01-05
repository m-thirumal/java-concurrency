/**
 * 
 */
package in.thirumal.Synchronize;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Thirumal
 * Check the duration it takes to Complete
 * 
 * Disadvantage:: Once, the thread enters any synchronized method on an instance, 
 * no other thread can enter any other synchronized method on the same instance.
 * 
 * However, non-synchronized methods on that instance will continue to be callable.
 *
 */
public class SynchronizedLimitaion {

	
	public static void main(String[] args) {
		WorkerClass workerClass = new WorkerClass();
		workerClass.main();
	}
	
}

class WorkerClass {
	Random random = new Random();
	List<Integer> list1 = new ArrayList<>();
	List<Integer> list2 = new ArrayList<>();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	/**
	 * @param args
	 */
	public void main() {
		
		System.out.println("Starting......");
		LocalTime startTime = LocalTime.now();
		
		Thread t1 = new Thread(() -> proccess()); 
		
		Thread t2 = new Thread(() -> proccess());
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println("Duration: " + Duration.between(startTime, LocalTime.now())); 
		System.out.println("List 1 size: " + list1.size());
		System.out.println("List 2 size: " + list2.size());
	}
	
	private void proccess() {		
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}		
	}
	/*
	private void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
	}
	
	private void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
	}*/
	/**
	 * Check the duration it takes to Complete
	 * Disadvantage: Once, the thread enters any synchronized method on an instance, 
	 * no other thread can enter any other synchronized method on the same instance.
	 * 
	 * However, non-synchronized methods on that instance will continue to be callable.
	 */
	/*
	private synchronized void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
	}
	
	private synchronized void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
	}*/
	

	private void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	
	private void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	} 
	
}