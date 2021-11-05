import java.util.LinkedList;
import java.util.HashMap;

public class ToDoList {

	private LinkedList<Task> tasks;
	private HashMap<String, ToDoList> subLists;	
	private String nameOfList;
	
	//constructor methods
	public ToDoList(String nameOfList){
		this.nameOfList = nameOfList;
		this.tasks = new LinkedList<>();
		this.subLists = new HashMap<>();
	}
	public ToDoList(String nameOfList, LinkedList<Task> tasks){
		this.nameOfList = nameOfList;
		this.tasks = tasks;
		this.subLists = new HashMap<>();
	}
	public ToDoList(HashMap<String, ToDoList> subLists, String nameOfList){
		this.nameOfList = nameOfList;
		this.tasks = new LinkedList<>();
		this.subLists = subLists;
	}
	public ToDoList(HashMap<String, ToDoList> subLists, String nameOfList, LinkedList<Task> tasks){
		this.nameOfList = nameOfList;
		this.tasks = tasks;
		this.subLists = subLists;
	}

	//setters and getters	
	public String getNameOfList(){
		return nameOfList;
	}
	public void setNameOfList(String nameOfList) {
		this.nameOfList = nameOfList;
	}

	public LinkedList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(LinkedList<Task> tasks) {
		this.tasks = tasks;
	}

	public HashMap<String, ToDoList> getSubLists() {
		return subLists;
	}
	public void setSubLists(HashMap<String, ToDoList> subLists) {
		this.subLists = subLists;
	}

}

