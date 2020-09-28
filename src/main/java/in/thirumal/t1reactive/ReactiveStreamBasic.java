/**
 * 
 */
package in.thirumal.t1reactive;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author Thirumal
 *
 */
public class ReactiveStreamBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SubmissionPublisher<WeatherForecast> weatherForecastPublisher = new WeatherForcastPublisher();
		
		Subscriber<WeatherForecast> twitterSubscriber = new Flow.Subscriber<WeatherForecast>() {
			
			String name = "Twitter Subscriber";
			Subscription subscription;
			 
			@Override
			public void onSubscribe(Subscription subscription) {
				System.out.println(name + " Subscribed!!");
				this.subscription = subscription;
				subscription.request(5);
			}

			@Override
			public void onNext(WeatherForecast weatherForecast) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Twitting " + weatherForecast + " " + Thread.currentThread().getName());
			}

			@Override
			public void onError(Throwable throwable) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}
		};
		
		Subscriber<WeatherForecast> secondSubscriber = new Flow.Subscriber<WeatherForecast>() {
			private String name = "Second subscriber ";
			Subscription subscription;
			@Override
			public void onSubscribe(Subscription subscription) {
				System.out.println(name + "Subscribed #");
				this.subscription = subscription;
				subscription.request(5);
				
			}

			@Override
			public void onNext(WeatherForecast weatherForecast) {
				System.out.println("Second " + weatherForecast + " " + Thread.currentThread().getName());
				
			}

			@Override
			public void onError(Throwable throwable) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}
		};
		
		weatherForecastPublisher.subscribe(twitterSubscriber);
		weatherForecastPublisher.subscribe(secondSubscriber);
	}

}

class WeatherForcastPublisher extends SubmissionPublisher<WeatherForecast> {
	final ScheduledFuture<?> periodicTask;
	final ScheduledExecutorService scheduler;

	WeatherForcastPublisher() {
		super(Executors.newFixedThreadPool(2), Flow.defaultBufferSize());
		scheduler = new ScheduledThreadPoolExecutor(1);
		periodicTask = scheduler.scheduleAtFixedRate( //
				// runs submit()
				() -> submit(WeatherForecast.nextRandomWeatherForecast()), //
				500, 500, TimeUnit.MILLISECONDS);
	}

	public void close() {
		periodicTask.cancel(false);
		scheduler.shutdown();
		super.close();
	}
}

class WeatherForecast {
	
	private final int temperatureInF;
	private final int windSpeedInMPH;
	private final String weatherCondition;
	
	private static final Random random = new Random();
	private static final String[] allWeatherConditions = new String[] { "☁️", "☀️", "⛅", "🌧", "⛈️" };
	
	public WeatherForecast(int temperatureInF, int windSpeedInMPH, String weatherCondition) {
		super();
		this.temperatureInF = temperatureInF;
		this.windSpeedInMPH = windSpeedInMPH;
		this.weatherCondition = weatherCondition;
	}
	
	public static WeatherForecast nextRandomWeatherForecast() {
		String weatherCondition = allWeatherConditions[random.nextInt(allWeatherConditions.length)];
		int temperatureInF = random.nextInt(95);
		int windSpeedInMPH = 5 + random.nextInt(30);
		return new WeatherForecast(temperatureInF, windSpeedInMPH, weatherCondition);
	}

	@Override
	public String toString() {
		return "WeatherForecast [temperatureInF=" + temperatureInF + ", windSpeedInMPH=" + windSpeedInMPH
				+ ", weatherCondition=" + weatherCondition + "]";
	}
	
}