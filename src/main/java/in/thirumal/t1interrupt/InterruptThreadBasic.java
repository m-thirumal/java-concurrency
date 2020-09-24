package in.thirumal.t1interrupt;

public class InterruptThreadBasic {

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i< 1E23; i++) {
				
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("Thread got interrupt");
					break;
				}
			}
			
			
		});
		
		t1.start();
		try {
			System.out.println("Main Thread Start sleeping......");
			Thread.sleep(1000);
			System.out.println("Main Thread awake......");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		t1.interrupt();
	
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished");
	}

}
