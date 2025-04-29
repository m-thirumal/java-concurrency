/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 
 */
public class TCS_Interview_Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str = "Hello World";
		List<Character> characters = new ArrayList<Character>();
		for (char c : str.toCharArray()) {
			characters.add(c);
		}
		var duplicate = characters.stream().collect(Collectors.groupingBy(c->c, Collectors.counting()));
		System.out.println(duplicate);
		characters.forEach(System.out::println);
		
		
		

	}

}
