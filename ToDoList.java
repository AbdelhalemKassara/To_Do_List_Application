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
	public String toString() {
		String s = "";
		for(String list: subList.keySet()) {
			s += String.format("%-26.26s | %-56.56s | %1$-26.26s\n", "SubList", list);
		}	
		//adds the tasks to the string	
		//change from object to Task
	       	Task[] taskArray = tasksList.toArray(new Task[tasksList.size()]);
		for(int i = 0; i < taskArray.length; i++) {
			s += taskArray[i].toString();
		}

		return s;
	}	
}

