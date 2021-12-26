package TestClasses;

import Application.CommandLine.UserInput.CommandsMap;
import Application.DataStructures.*;
import Application.CommandLine.UserInput.Parser;
import Application.DataStructures.Tables.Tables;
import Application.DataStructures.Task.Task;
import Application.DataStructures.ToDoList.ToDoList;
import Application.DataStructures.User.User;

import java.time.LocalDateTime;

public class TestTaskClass {
	public static void main(String[] args) {

		//test task's tostring moethod	
		Task test = new Task(LocalDateTime.of(2021, 2, 3, 13, 1),"testsafsafdhdsafasfdjkhaslkdfjhaslkdjfhalskdjfhaksjdhflkasdjhfkalsjhfdlkdasjhflklksjfdhlaksdhfalkshdjflkdlkajfdsjlkfdsalkj;dsaflkjdsafj;lkdsaflkasdfdfasdfasdfasdfasdfjw;lefj", LocalDateTime.now());
		System.out.println(test.getTask() + '\n');	
		System.out.println(test);	
		test.changeToStringWidth(30, 50);
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
		/*	
		System.out.println("test compare to method");
		Task task1 = new Task("task 1", LocalDateTime.now());
		Task task2 = new Task("task 2", 2019, 3, 1, 1, 1);
		System.out.println(task1.compareTo(task2));
		System.out.println();

		System.out.println("test compare to method equal");
		task1 = new Task("task 1", 2019, 3, 1, 1, 1);
		task2 = new Task("task 2", 2019, 3, 1, 1, 1);
		System.out.println(task1.compareTo(task2));
		System.out.println();
		
		System.out.println("test compare to method less than");
		task1 = new Task("task 1", LocalDateTime.now());
		task2 = new Task("task 2", 2019, 3, 1, 1, 1);
		System.out.println(task2.compareTo(task1));
		System.out.println();
		*/

		System.out.println("test sorting to do list");
		
		ToDoList list1 = new ToDoList();
		
		System.out.println(list1.getTaskList());
		list1.addTask(new Task("task1", 2019, 2, 2, 2, 2));
		System.out.println(list1.getTaskList());	
		list1.addTask(new Task("task2", 2021, 2, 2, 2, 2));
		System.out.println(list1.getTaskList());
		list1.addTask(new Task("task3", 2021, 5, 2, 2, 2));
		System.out.println(list1.getTaskList());
		System.out.println();

		System.out.println("test adding sublists");
		ToDoList list2 = new ToDoList();
		System.out.println(list2.getSubList());
		list2.addSubList("list 1", new ToDoList());
		System.out.println(list2.getSubList());
		list2.addSubList("list 2", new ToDoList());
		System.out.println(list2.getSubList());
		System.out.println(list2.addSubList("list 0", new ToDoList()));
		System.out.println(list2.getSubList());
		System.out.println(list2.addSubList("list 0", new ToDoList()));
		System.out.println(list2.getSubList());
		System.out.println("asfasd");

		System.out.println("test getting a list");
		list1 = new ToDoList();
		System.out.println(list1.getSubList());
		list1.addSubList("sublist11", new ToDoList());
		System.out.println(list1.getSubList());
		list1.getSubList().get("sublist11").addSubList("sublist21", new ToDoList());
		System.out.println(list1.getSubList().get("sublist11").getSubList());
		
		System.out.println(list1.getList("sublist11/sublist21"));
		System.out.println(list1.getList("sublist11"));

		
		list1.getSubList().get("sublist11").getSubList().get("sublist21").addSubList("sublist31", new ToDoList());
		list1.getSubList().get("sublist11").getSubList().get("sublist21").addSubList("sublistsadf1", new ToDoList());

		System.out.println(list1.getSubList().get("sublist11").getSubList().get("sublist21").getSubList());
		list1.getSubList().get("sublist11").getSubList().get("sublist21").getSubList().get("sublist31").addTask("test 1", 2022, 12, 3, 1, 1);
		
		System.out.println(list1.getList("sublist11/sublist21/sublist31"));
		System.out.println("this one here");
		System.out.println(list1.getListWithName("sublist11/sublist21/sublist31").getName());
		System.out.println(list1.getListWithName("afdfd").getName());
		System.out.println("this one here");

		System.out.println(list1.getList("sublist11/sublist21/sublst31"));
		System.out.println(list1.getList("1"));
		System.out.println();
		
		System.out.println("test tostring method for ToDoList");
		list1.addTask(new Task("task 1", 2020, 1, 1, 1, 1));	
		list1.addTask(new Task("task 2", 2020, 2, 1, 1, 1));	
		list1.addTask(new Task("task 3", 2010, 2, 1, 1, 1));	
		System.out.println(list1);
		System.out.println();

		System.out.println("test string methods for todolist");
		System.out.println(list1);
		System.out.println("subliststring");	
		System.out.println(list1.subListString());
		System.out.println("tasksliststring");	
		System.out.println(list1.taskListString());
		System.out.println();
		
		System.out.println("test addtask methods");
		list1.addTask("test addtask 1", 2021, 2, 3, 3, 3);
		System.out.println(list1.taskListString());
		list1.addTask(2002, 2, 2, 2, 2, "test addtask 2", 2021, 2, 2, 2, 2);
		System.out.println(list1.taskListString());
		list1.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaadsffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);

		System.out.println("test removeTask methods");
		System.out.println(list1.taskListString());
		list1.removeTask(1);
		System.out.println(list1.taskListString());
		list1.removeTask(3);
		System.out.println(list1.taskListString());
		list1.removeTask(3);
		System.out.println(list1.taskListString());
		
		
		Task t1 = new Task("remove this", 2021, 1, 1, 1, 1);	
		list1.addTask(t1);
		System.out.println(list1.taskListString());
		list1.removeTask(t1);
		System.out.println(list1.taskListString());
		System.out.println();

		System.out.println("test getting the keys");
		System.out.println(list1.getSubListKeys()[0]);
		System.out.println();

		System.out.println("test the user class");
		User u1 = new User();
		u1.addSubList("sublist11", new ToDoList());
		System.out.println(u1.getSubList());
		u1.getSubList().get("sublist11").addSubList("sublist21", new ToDoList());
		System.out.println(u1.getSubList().get("sublist11").getSubList());
		System.out.println(u1.getList("sublist11/sublist21"));
		System.out.println(u1.getList("sublist11"));
		u1.addTask(new Task("task 1", 2020, 1, 1, 1, 1));
		u1.addTask(new Task("task 2", 2020, 2, 1, 1, 1));
		u1.addTask(new Task("task 3", 2010, 2, 1, 1, 1));
		System.out.println(u1);
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println(list1);
		System.out.println(list2);
		System.out.println();
		System.out.println();

		Tables table1 = new Tables();
		table1.addList(list1, "rojdafsjlkasdffa;ldjsl;;kl;;;;;;");
		table1.addList(list2, "root");
		System.out.println(table1);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		User user1 = new User();
		list1.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaadsffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2011, 2, 2, 2, 2);
		list1.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaadsffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);
		list2.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaadsffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);

		list2.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfssaadsffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);
		list2.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsadadsffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);
		list2.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaasdsffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);
		list2.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaadssffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);
		list2.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaadsdffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);
		list2.addTask(2002, 2, 2, 2, 2, "test adffsadfsddfsaadsfdffadsdfsaadfsdfsdfsajdfasjldf;sk;lkdjsf;jdsflksdf;sladkfasdfljkas;dfjklaljkadjks;ladfskljasdjkfadsfkjladfskjadfsklj;dfkasj;dkafjskd;jlafskljd;afskjlf;adskljdfsldfksjkjldfslkjsdflkj;sdadtask 2", 2021, 2, 2, 2, 2);
		user1.addTable("stuff");
		user1.addToTable("stuff", new ListItem(list1, "test value1"));
		user1.addToTable("stuff", new ListItem(list2, "test vaue1"));
		list1.addSubList("asfdaf",new ToDoList());
		System.out.println(user1.getTable("stuff"));

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();



		ToDoList list4 = new ToDoList();
		System.out.println(list1.getSubList());
		list4.addSubList("sublist11", new ToDoList());
		list4.addSubList("sublist10", new ToDoList());
		list4.getSubList().get("sublist11").addSubList("sublist21", new ToDoList());
		list4.getSubList().get("sublist11").getSubList().get("sublist21").addSubList("sublist31", new ToDoList());
		list4.getSubList().get("sublist11").getSubList().get("sublist21").addSubList("sublistsadf1", new ToDoList());
		list4.getSubList().get("sublist11").getSubList().get("sublist21").getSubList().get("sublist31").addTask("test 1", 2022, 12, 3, 1, 1);
		list4.getSubList().get("sublist11").addSubList("pp21", new ToDoList());

		/*
		sublist11/sublist21/sublist31
		sublist11/sublist21/sublistsadf1
		sublist11/sublists10
		 */
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();


		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		/*
		Parser p3 = new Parser(new User());

		p3.parseUserInput("addList list1|");
		p3.parseUserInput("addList list2|");
		//p3.printList();
		p3.parseUserInput("printList ");
		p3.parseUserInput("addTask asdfasfda|2020|2|3|12|12|");
		p3.parseUserInput("addTask 2020|2|3|12|12|asads;jjaf;dsl asd;lkfkj asdf adsfdfasfda|2020|2|3|12|12|");
		p3.parseUserInput("printList ");
		p3.parseUserInput("listNames ");
		 */
		CommandsMap c1 = new CommandsMap(new User());
		c1.operationsSearch(Parser.parseUserInput("addTask 2020|2|3|12|12|asads;jjaf;dsl asd;lkfkj asdf adsfdfasfda|2020|2|3|12|12|"));
		c1.operationsSearch(Parser.parseUserInput("addTask asdfasfda|2020|2|3|12|12|"));
		c1.operationsSearch(Parser.parseUserInput("addList list1|"));
		c1.operationsSearch(Parser.parseUserInput("addList list2|"));
		c1.operationsSearch(Parser.parseUserInput("addList listsdfasdf1|"));
		c1.operationsSearch(Parser.parseUserInput("listNames "));
		c1.operationsSearch(Parser.parseUserInput("printList "));
	}
}
