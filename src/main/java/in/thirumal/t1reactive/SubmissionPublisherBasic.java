/**
 * 
 */
package in.thirumal.t1reactive;

import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author Thirumal
 *
 */
public class SubmissionPublisherBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SubmissionPublisher<WeatherForecast> submissionPublisher = new WeatherForecastSubmissionPublisher();
		
		submissionPublisher.subscribe(new TwitterSubscriber());
		
		submissionPublisher.subscribe(new DbSubscriber());
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		submissionPublisher.close();
	}

}

class WeatherForecastSubmissionPublisher extends SubmissionPublisher<WeatherForecast> {
	
	ScheduledFuture<?> scheduledFuture;
	ScheduledExecutorService scheduledExecutorService;
	
	WeatherForecastSubmissionPublisher() {
		//super(Executors.newFixedThreadPool(2), Flow.defaultBufferSize());
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		
		scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(()->submit(WeatherForecast.nextRandomWeatherForecast()),
				50, 50, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public void close() {
		scheduledFuture.cancel(false);
		scheduledExecutorService.shutdown();
		super.close();
	}
}

class TwitterSubscriber implements Flow.Subscriber<WeatherForecast> {

	Subscription subscription;
	String name ="Twitter";
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.out.println(name + " subscribed!");
		subscription.request(5);
	}

	@Override
	public void onNext(WeatherForecast weatherForecast) {
		System.out.println("Twitter: " + weatherForecast + " : " + Thread.currentThread().getName());
		
	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}
	
}


class DbSubscriber implements Flow.Subscriber<WeatherForecast> {

	Subscription subscription;
	String name ="Db";
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.out.println(name + " subscribed!");
		subscription.request(5);
	}

	@Override
	public void onNext(WeatherForecast weatherForecast) {
		System.out.println("Db: " + weatherForecast + " : " + Thread.currentThread().getName());
		
	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}
	
}
