import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;

public class ToDoList {

	private LinkedList<Task> taskList;
	private HashMap<String, ToDoList> subList;	
	
	//constructor methods
	public ToDoList(){
		this.taskList = new LinkedList<>();
		this.subList = new HashMap<>();
	}
	public ToDoList(LinkedList<Task> taskList){
		this.taskList = taskList;
		this.subList = new HashMap<>();
	}
	public ToDoList(HashMap<String, ToDoList> subList){
		this.taskList = new LinkedList<>();
		this.subList = subList;
	}
	public ToDoList(HashMap<String, ToDoList> subList, LinkedList<Task> taskList){
		this.taskList = taskList;
		this.subList = subList;
	}

	//setters and getters	
	public LinkedList<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(LinkedList<Task> taskList) {
		this.taskList = taskList;
	}

	public HashMap<String, ToDoList> getSubList() {
		return subList;
	}
	public void setSubList(HashMap<String, ToDoList> subList) {
		this.subList = subList;
	}
	
	public void addTask(Task task) {
		taskList.add(task);
		Collections.sort(taskList);
	}
	public void addTask(String task, int year, int month, int dayOfMonth, int hour, int minute) {
		taskList.add(new Task(task, year, month, dayOfMonth, hour, minute));
		Collections.sort(taskList);
	}
	public void addTask(int stYear, int stMonth, int stDayOfMonth, int stHour, int stMinute, String task,
		int year, int month, int dayOfMonth, int hour, int minute){
		taskList.add(new Task(stYear, stMonth, stDayOfMonth, stHour, stMinute, task, year, month, dayOfMonth, hour, minute));
		Collections.sort(taskList);
	}
	public boolean removeTask(int index) {
		if(index < taskList.size()) {
			taskList.remove(index);
			return true;	
		}
		
		return false;
	}
	//if there is no exisiting key return true if there is return false
	public boolean addSubList(String title, ToDoList list) {
		return subList.putIfAbsent(title, list) == null;
		
	}
	//format ex. "sublist0/sublist1/sublist2"	
	public ToDoList getList(String path) {
		String key = "";
		//gets the key for the sublist and the new path	
		for(int i = 0; i < path.length(); i++) {
			if(path.charAt(i) == '/') {
				key = path.substring(0, i);	
				path = path.substring(i+1, path.length());	
				break;
			} else if (i == path.length()-1) {
				key = path;
				path = "";
			}
		}
		
		ToDoList temp =	subList.get(key); //gets sublist

		if(temp != null && path != "") {
			return temp.getList(path);
		}
		return temp;//returns null if there is no list associated to the key
	}
	public String subListString() {
		StringBuilder str = new StringBuilder();	

		for(String list: subList.keySet()) {
			str.append(String.format("%-26.26s | %-56.56s | %1$-26.26s\n", "SubList", list));
		}
		
		return str.toString();
	}	
	public String taskListString() {
		StringBuilder str = new StringBuilder();	
	       	Task[] taskarray = taskList.toArray(new Task[taskList.size()]);
	
		for(int i = 0; i < taskarray.length; i++) {
			str.append(taskarray[i].toString());
		}
		
		return str.toString();
	}	
	public String toString() {
		return subListString() + taskListString();
	}	
}

