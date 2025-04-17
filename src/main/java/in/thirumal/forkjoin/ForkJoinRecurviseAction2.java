/**
 * 
 */
package in.thirumal.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class ForkJoinRecurviseAction2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		forkJoinPool.submit(new CustomRecurviseAction("Hello, this is the new world, welcome all"));
		
		try {
			forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		forkJoinPool.shutdown();

	}

}


class CustomRecurviseAction extends RecursiveAction {

	private static final long serialVersionUID = 6479851343142247341L;
	
	static final int threshold = 10;
	String workload = "";
	
	public CustomRecurviseAction(String workload) {
		super();
		this.workload = workload;
	}

	@Override
	protected void compute() {
		
		if (workload.length() > threshold) {
			ForkJoinTask.invokeAll(createSubTask(workload));
		} else {
			processSubTask(workload);
		}
		
	}

	private List<CustomRecurviseAction> createSubTask(String workload2) {
		List<CustomRecurviseAction> customRecusriveAction = new ArrayList<>();
		customRecusriveAction.add(new CustomRecurviseAction(workload2.substring(0, workload2.length()/2)));
		customRecusriveAction.add(new CustomRecurviseAction(workload2.substring(workload2.length()/2, workload2.length())));
		return customRecusriveAction;
	}

	private void processSubTask(String workload2) {
		System.out.println(workload2.toUpperCase() + " Chaned in Thread => " + Thread.currentThread().getName());
	}
	
}