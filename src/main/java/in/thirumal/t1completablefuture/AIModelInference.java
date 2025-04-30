package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;

public class AIModelInference {

	public static void main(String[] args) {
		CompletableFuture<String> recommendationFuture = CompletableFuture.supplyAsync(() -> {
			simulateDelay(3000);
			return "Recommended Movies: Inception, Interstellar";
		});
		recommendationFuture.thenAccept(System.out::println);
		System.out.println("Fetching recommendations...");
		recommendationFuture.join();
	}

	private static void simulateDelay(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
