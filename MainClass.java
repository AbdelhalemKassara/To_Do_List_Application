import java.util.*;
import java.time.LocalDateTime;
public class MainClass {
	public static void main(String[] args) {
		Task test = new Task(LocalDateTime.of(2021, 2, 3, 13, 1),"test", LocalDateTime.now());
		System.out.println(test.getEndDate().getYear());	
		System.out.println(test.getEndDate().getMonth());	
		System.out.println(test.getEndDate().getDayOfMonth());	
	
		System.out.println(test);
		System.out.println(test.getEndDate().getYear());		
		//System.out.printf("%ta",test.getEndDate().getDayOfWeek());
	}
}
