import java.util.*;
import java.time.LocalDateTime;
public class TestTaskClass {
	public static void main(String[] args) {
		//test task's tostring moethod	
		Task test = new Task(LocalDateTime.of(2021, 2, 3, 13, 1),"testsafsafdhdsafasfdjkhaslkdfjhaslkdjfhalskdjfhaksjdhflkasdjhfkalsjhfdlkdasjhflklksjfdhlaksdhfalkshdjflkdlkajfdsjlkfdsalkj;dsaflkjdsafj;lkdsaflkasdfdfasdfasdfasdfasdfjw;lefj", LocalDateTime.now());
		System.out.println(test.getTask() + '\n');	
		System.out.println(test);	
		
		System.out.println("test 1st constructor");
		Task constructor1 = new Task("constructor1", LocalDateTime.now());
		System.out.println(constructor1);
		System.out.println();

		System.out.println("test setting start date");	
		System.out.println(constructor1.getStartDate());
		constructor1.changeStartDate(2021, 2, 1, 12, 1);
		System.out.println(constructor1);
		System.out.println(constructor1.getStartDate());
		System.out.println();

		System.out.println("teset setting start date method 2(2021, 2, 1)");
		constructor1.changeStartDate(null);
		System.out.println(constructor1.getStartDate());
		constructor1.changeStartDate(2021, 2, 1);
		System.out.println(constructor1);
		System.out.println(constructor1.getStartDate());
		System.out.println();

		System.out.println("test setting start date method 3 (12,3)");
		constructor1.changeStartDate(null);
		constructor1.changeStartDate(12,3);
		System.out.println(constructor1);
		System.out.println();
		
		System.out.println("test setting start date method 3 (2,3)");
		constructor1.changeStartDate(2,3);
		System.out.println(constructor1.toString() +'\n');

		System.out.println("test changing task");
		System.out.println(constructor1);
		constructor1.setTask("sdfasdfads");
		System.out.println(constructor1);
		System.out.println();
		
		System.out.println("test startDateString method");
		System.out.println(constructor1.startDateString());
		System.out.println();

		System.out.println("test printing null startDate");
		constructor1.changeStartDate(null);
		System.out.println(constructor1.startDateString());
		System.out.println();
	
		System.out.println("test constructor 3 for task");
		Task constructor3 = new Task("testing constructor 3", 2021, 1, 1, 1, 1);
		System.out.println(constructor3);
		System.out.println();
		
		System.out.println("test constructor 4 for task");
		Task constructor4 = new Task(2020, 2, 2, 2, 2,"testing constructor 4", 2021, 1, 1, 1, 1);
		System.out.println(constructor4);
		System.out.println(constructor4);

	}
}