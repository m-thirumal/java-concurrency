package in.thirumal.t1completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ExceptionResilient {

	public static void main(String[] args) {
		 CompletableFuture<String> serviceCall = CompletableFuture.supplyAsync(() -> {
	            try {
	            	Thread.sleep(3000);
	            } catch (InterruptedException e) {
	            		e.printStackTrace(); 
	            }
	            return "Service Response";
	        });
		 //Fallback
//		 CompletableFuture<String> result = serviceCall
//				 .completeOnTimeout("Default Fallback", 100, TimeUnit.MILLISECONDS); //Increase/decrease time out to check the output
//		 System.out.println(result.join());
		 //Don't use same `serviceCall` - CompletableFuture is single-use
		 // Service
		 CompletableFuture<String> result1 = serviceCall
				 .completeOnTimeout("Default Fallback", 10000, TimeUnit.MILLISECONDS); //Increase/decrease time out to check the output
		 System.out.println(result1.join());
	}

}
