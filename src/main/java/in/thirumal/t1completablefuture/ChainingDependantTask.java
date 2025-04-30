/**
 * 
 */
package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * Scenario:
 * 	In an order processing system, you must first validate an order, then process the payment, and finally send a confirmation email. 
 * 	These tasks are dependent and should occur in sequence asynchronously.
 */
public class ChainingDependantTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String orderId = "Order 1";
		CompletableFuture
		.supplyAsync(() -> validateOrder(orderId))//The flow is non-blocking, improving system responsiveness.
		.thenApply(order -> processPayment(order))//Each task depends on the result of the previous one.
		.thenApply(payment -> confirm(payment))//thenApply() is used to transform the result and pass it to the next stage.
		.thenAccept(result -> System.out.println("Result " + result))
		.join();

	}
	
	private static String confirm(String payment) {
		System.out.println("Confirm order - " + payment);
		simulateDelay(1000);
		return payment + " payment processed ";
	}

	private static String processPayment(String order) {
		System.out.println("Processing Payment - " + order);
		simulateDelay(1000);
		return order + " payment processed ";
	}

	private static String validateOrder(String order) {
		System.out.println("Validating Order - " + order);
		simulateDelay(1000);
		return order + " validated ";
	}

	private static void simulateDelay(long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
