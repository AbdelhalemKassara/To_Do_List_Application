import java.util.LinkedList;

public class ToDoList {

	//might be better to change sublistst to hashmap
	private LinkedList<Task> tasks;
	private LinkedList<ToDoList> subLists;	
	private String nameOfList;
	
	//constructor methods
	public ToDoList(String nameOfList){
		this.nameOfList = nameOfList;
		this.tasks = new LinkedList<>();
		this.subLists = new LinkedList<>();
	}
	public ToDoList(String nameOfList, LinkedList<Task> tasks){
		this.nameOfList = nameOfList;
		this.tasks = tasks;
		this.subLists = new LinkedList<>();
	}
	public ToDoList(LinkedList<ToDoList> subLists, String nameOfList){
		this.nameOfList = nameOfList;
		this.tasks = new LinkedList<>();
		this.subLists = subLists;
	}
	public ToDoList(LinkedList<ToDoList> subLists, String nameOfList, LinkedList<Task> tasks){
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

	public LinkedList<Task> getTasks(){
		return tasks;
	}
	public LinkedList<ToDoList> getSubLists() {
		return subLists;
	}

}

