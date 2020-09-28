/**
 * 
 */
package in.thirumal.t1reactive;

import java.util.Random;

/**
 * @author Thirumal
 *
 */
public class WeatherForecast {
	
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
