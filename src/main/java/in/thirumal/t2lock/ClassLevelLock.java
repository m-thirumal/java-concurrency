package in.thirumal.t2lock;

public class ClassLevelLock {

	public static void main(String[] str) {
        Thread t = new Thread(() -> classLevelLockMethod());
        t.start();
        //
        ClassLevelLock classLevelLock = new ClassLevelLock();
        Thread t2 = new Thread(() -> classLevelLock.instanceLevelMethod());
        t2.start();
    }
    
	
	synchronized static void classLevelLockMethod() {
		System.out.println(Thread.currentThread().threadId() + " has acquired Class-Level Lock");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().threadId() + " has released Class-Level Lock");
	}
	
	synchronized void instanceLevelMethod() {
		System.out.println(Thread.currentThread().threadId() + " has acquired object-Level Lock");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().threadId() + " has released object-Level Lock");
	}
}