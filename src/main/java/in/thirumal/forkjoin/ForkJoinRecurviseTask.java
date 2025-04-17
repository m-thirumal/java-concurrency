/**
 * 
 */
package in.thirumal.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class ForkJoinRecurviseTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		String output = forkJoinPool.invoke(new CustomRecurviseTask("Hello, this is the new world, welcome all"));
		
		try {
			forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(output);
		forkJoinPool.shutdown();

	}

}


class CustomRecurviseTask extends RecursiveTask<String> {

	private static final long serialVersionUID = 6479851343142247341L;
	
	static final int threshold = 10;
	String workload = "";
	
	public CustomRecurviseTask(String workload) {
		super();
		this.workload = workload;
	}

	@Override
	protected String compute() {
		
		if (workload.length() > threshold) {
			return ForkJoinTask.invokeAll(createSubTask(workload)).stream().map(ForkJoinTask::join).toString();
		} else {
			return processSubTask(workload);
		}
		
	}

	private List<CustomRecurviseTask> createSubTask(String workload2) {
		List<CustomRecurviseTask> customRecusriveAction = new ArrayList<CustomRecurviseTask>();
		customRecusriveAction.add(new CustomRecurviseTask(workload2.substring(0, workload2.length()/2)));
		customRecusriveAction.add(new CustomRecurviseTask(workload2.substring(workload2.length()/2, workload2.length())));
		return customRecusriveAction;
	}

	private String processSubTask(String workload2) {
		String upperCase = workload2.toUpperCase();
		System.out.println(upperCase + " Chaned in Thread => " + Thread.currentThread().getName());
		return upperCase;
	}
	
}