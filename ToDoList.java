import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;

public class ToDoList {

	private LinkedList<Task> tasksList;
	private HashMap<String, ToDoList> subLists;	
	
	//constructor methods
	public ToDoList(){
		this.tasksList = new LinkedList<>();
		this.subLists = new HashMap<>();
	}
	public ToDoList(LinkedList<Task> tasksList){
		this.tasksList = tasksList;
		this.subLists = new HashMap<>();
	}
	public ToDoList(HashMap<String, ToDoList> subLists){
		this.tasksList = new LinkedList<>();
		this.subLists = subLists;
	}
	public ToDoList(HashMap<String, ToDoList> subLists, LinkedList<Task> tasksList){
		this.tasksList = tasksList;
		this.subLists = subLists;
	}

	//setters and getters (for importing)	
	public LinkedList<Task> getTasksList() {
		return tasksList;
	}
	public void setTasksList(LinkedList<Task> tasksList) {
		this.tasksList = tasksList;
	}

	public HashMap<String, ToDoList> getSubLists() {
		return subLists;
	}
	public void setSubLists(HashMap<String, ToDoList> subLists) {
		this.subLists = subLists;
	}
	
	public void addTask(Task task) {
		tasksList.add(task);
		Collections.sort(tasksList, Collections.reverseOrder());
	}
}

