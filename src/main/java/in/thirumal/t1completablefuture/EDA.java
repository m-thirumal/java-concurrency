/**
 * 
 */
package in.thirumal.t1completablefuture;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * Orchestrate multiple microservices asynchronously, improving latency and throughput.
 */
public class EDA {// Event driven Architecture

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocalDateTime startTime = LocalDateTime.now();
		CompletableFuture<String> menuFuture = fetchMenu();
		CompletableFuture<String> deliveryFuture = fetchDeliveryEstimate();
		CompletableFuture<String> preferencesFuture = fetchUserPreferences();
		
		CompletableFuture<Void> tasks = CompletableFuture.allOf(menuFuture, deliveryFuture, preferencesFuture);
		tasks.thenRun(() -> {
			try {
				System.out.println(menuFuture.get());
				System.out.println(deliveryFuture.get());
				System.out.println(preferencesFuture.get());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}).join();
		Duration duration = Duration.between(startTime, LocalDateTime.now());
		System.out.println("Completed in  : " + duration.toSeconds() + "'s instead of 3's");
	}
	
	public static CompletableFuture<String> fetchMenu() {
		return CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return "Menu from Restaurant Service";
		});
	}
	
	public static CompletableFuture<String> fetchDeliveryEstimate() {
		return CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return "Estimated Delivery Time: 30 mins";
		});
	}
	
	public static CompletableFuture<String> fetchUserPreferences() {
		return CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return "User Preferences: Non-Veg";
		});
	}
	
	public static void delay(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
