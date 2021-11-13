import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;

public class ToDoList {

	private LinkedList<Task> tasksList;
	private HashMap<String, ToDoList> subList;	
	
	//constructor methods
	public ToDoList(){
		this.tasksList = new LinkedList<>();
		this.subList = new HashMap<>();
	}
	public ToDoList(LinkedList<Task> tasksList){
		this.tasksList = tasksList;
		this.subList = new HashMap<>();
	}
	public ToDoList(HashMap<String, ToDoList> subList){
		this.tasksList = new LinkedList<>();
		this.subList = subList;
	}
	public ToDoList(HashMap<String, ToDoList> subList, LinkedList<Task> tasksList){
		this.tasksList = tasksList;
		this.subList = subList;
	}

	//setters and getters (for importing)	
	public LinkedList<Task> getTasksList() {
		return tasksList;
	}
	public void setTasksList(LinkedList<Task> tasksList) {
		this.tasksList = tasksList;
	}

	public HashMap<String, ToDoList> getSubList() {
		return subList;
	}
	public void setSubList(HashMap<String, ToDoList> subList) {
		this.subList = subList;
	}
	
	public void addTask(Task task) {
		tasksList.add(task);
		Collections.sort(tasksList, Collections.reverseOrder());
	}

	//if there is no exisiting key reutrn true if there is return false
	public boolean addSubList(String title, ToDoList list) {
		return subList.putIfAbsent(title, list) == null;
		
	}	
}

