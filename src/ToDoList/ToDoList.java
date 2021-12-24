package ToDoList;

import java.util.*;

public class ToDoList extends Format{

	private LinkedList<Task> taskList;
	private HashMap<String, ToDoList> subList;	
	
	//constructor methods
	public ToDoList(){
		this.taskList = new LinkedList<>();
		this.subList = new HashMap<>();
	}
	//these constructor methods are for testing
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
	public Task getTask(int index) {
		return taskList.get(index);
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

	public void changeTask(int index, String task) {
		if(index < taskList.size()) {
			taskList.get(index).setTask(task);	
		}
	}
	public String[] getSubListKeys(){
		return subList.keySet().toArray(new String[0]);
	}
	public void changeStartDate(int index, int year, int month, int dayOfMonth, int hour, int minute) {
		taskList.get(index).changeStartDate(year, month, dayOfMonth, hour, minute);
	}
	public void changeStartDate(int index, int year, int month, int dayOfMonth) {
		taskList.get(index).changeStartDate(year, month, dayOfMonth);
	}
	public void changeStartDate(int index, int hour, int minute) {
		taskList.get(index).changeStartDate(hour, minute);
	}

	public void changeEndDate(int index, int year, int month, int dayOfMonth, int hour, int minute) {
		taskList.get(index).changeEndDate(year, month, dayOfMonth, hour, minute);
	}
	public void changeEndDate(int index, int year, int month, int dayOfMonth) {
		taskList.get(index).changeEndDate(year, month, dayOfMonth);
	}
	public void changeEndDate(int index, int hour, int minute) {
		taskList.get(index).changeEndDate(hour, minute);
	}

	public void removeTask(int index) {
		if(index < taskList.size()) {
			taskList.remove(index);
		}
		
	}
	public boolean removeTask(Task task) {
		return taskList.remove(task);	
	}

	//if there is no exisiting key return true if there is return false	
	public boolean addSubList(String title, ToDoList list) {
		return subList.putIfAbsent(title, list) == null;
		
	}
	public boolean addSubList(String title) {
		return subList.putIfAbsent(title, new ToDoList()) == null;
	}

	public void removeSubList(String key) {
		subList.remove(key);		
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
	public ToDoList betterGetList(String path) {
		if(path.equals("")) {
			return this;
		} else {
			return getList(path);
		}
	}
	public ListItem getListWithName(String path) {
		String key = "";
		//gets the key for the sublist and the new path
		for(int i = path.length()-1; i >= 0; i--) {
			if(path.charAt(i) == '/') {
				key = path.substring(i+1);
				break;
			}
		}
		ToDoList holder = getList(path);
		if(holder == null) {
			return new ListItem(holder, "");
		}
		return new ListItem(holder, key);//returns null if there is no list associated to the key
	}

	//"tostring" methods
	public String subListString() {
		StringBuilder str = new StringBuilder();
		for(String list: subList.keySet()) {
			str.append(String.format(super.getFormat() + '\n', "SubList", list, ""));
		}
		return str.toString();
	}
	public ArrayList<String> subListArrListStr() {
		ArrayList<String> str = new ArrayList<>();
		for(String list: subList.keySet()) {
			str.add(String.format(super.getFormat(), "SubList", list, ""));
		}
		return str;
	}

	public String taskListString() {
		StringBuilder str = new StringBuilder();	
	       	Task[] taskarray = taskList.toArray(new Task[taskList.size()]);
	
		for(int i = 0; i < taskarray.length; i++) {
			str.append(taskarray[i].toString());
		}
		
		return str.toString();
	}
	public ArrayList<String> taskListArrListStr() {
		ArrayList<String> str = new ArrayList<>();
		Task[] taskarray = taskList.toArray(new Task[taskList.size()]);

		for(int i = 0; i < taskarray.length; i++) {
				str.addAll(taskarray[i].toStringArrList());
		}
		return str;
	}

	public ArrayList<String> toStringArrListStr() {
		ArrayList<String> temp = subListArrListStr();
		temp.addAll(taskListArrListStr());
		return temp;
	}

	public String toString() {
		return subListString() + taskListString();
	}	
}

