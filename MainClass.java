import java.util.*;
import java.time.LocalDateTime;
public class MainClass {
	public static void main(String[] args) {
		Task test = new Task(LocalDateTime.of(2021, 2, 3, 13, 1),"testsafsafdhdsafasfdjkhaslkdfjhaslkdjfhalskdjfhaksjdhflkasdjhfkalsjhfdlkdasjhflklksjfdhlaksdhfalkshdjflkdlkajfdsjlkfdsalkj;dsaflkjdsafj;lkdsaflkasdfdfasdfasdfasdfasdfjw;lefj", LocalDateTime.now());
	//	System.out.println(test.getEndDate().getYear());	
	//	System.out.println(test.getEndDate().getMonth());	
	//	System.out.println(test.getEndDate().getDayOfMonth());	
		
		//String s = "sadfasdfasd";
		//String t = s;
		//t = "as";
		//System.out.println("asfsfsfdafd: " + t.substring(t.length(),t.length()).length());
		//System.out.printf("%5.5s\n","1234567890");
		//System.out.printf("%5.5s\n","12");

	//	System.out.println(test);
	///	System.out.println(test.getEndDate().getYear());		
		//System.out.printf("%ta",test.getEndDate().getDayOfWeek());
		//ArrayList<String> temp = test.splitString("sadfsaffasafsdfaafffdsafdsafadsfdafdsafdsfdsafdsaafsffdsafsdafsdasafdsafdsafdfsadsafdfsadfadsafdsfsd");
		//System.out.println(temp);
		//System.out.println(temp.size());
		System.out.println(test.getTask() + '\n');	
		System.out.println(test);	
	}
}
