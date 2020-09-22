/**
 * 
 */
package in.thirumal.t1threadlocal;

/**
 * @author thirumal
 *
 */
public class ThreadLocalWithInitalValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocal<Object> threadLocal1 = new ThreadLocal<>() {
			@Override
			protected Object initialValue() {
				return new Object();
			}
		};
		
		//OR we can use factory method  to intitialize the value
		ThreadLocal<Object> threadLocal2 = ThreadLocal.withInitial(Object::new);
		
		//Solution to make same initial value/object to all thread
		ThreadLocal<Object> threadLocal3 = new ThreadLocal<>();
		
		if (threadLocal3.get() == null) { //Lazy set, but it will not work again because of main thread: Main thread is setting the value
			System.out.println(threadLocal3.get());
			threadLocal3.set(new Object());
			System.out.println(threadLocal3.get());
		}
		
		System.out.println(threadLocal3.get());
		
		Thread thread1 = new Thread(()-> {
			System.out.println("Thread Local Value 1 in Thread 1: " + threadLocal1.get());
			
			System.out.println("Thread Local Value 2 in Thread 1: " + threadLocal2.get());
			
			System.out.println("Thread Local Value 3 in Thread 1: " + threadLocal3.get());
		});
		
		Thread thread2 = new Thread(()-> {
			System.out.println("Thread Local Value 1 in Thread 2: " + threadLocal1.get());
			
			System.out.println("Thread Local Value 2 in Thread 2: " + threadLocal2.get());
			
			System.out.println("Thread Local Value 3 in Thread 2: " + threadLocal3.get()); //Expecting same inital object as in Thread 1
		});
		
		thread1.start();
		thread2.start();
		
		//Expected output: Each thread will have it's  own object
	}

}
