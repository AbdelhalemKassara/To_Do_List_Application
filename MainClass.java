import java.util.*;
import java.time.LocalDateTime;
public class MainClass {
	public static void main(String[] args) {
		Task test = new Task("test", LocalDateTime.now());
		System.out.println(test.getEndDate().getYear());	
		System.out.println(test.getEndDate().getMonth());	
		System.out.println(test.getEndDate().getDayOfMonth());	
	
		System.out.println(test.endDateString());	
	}
}
