package Application.DataStructures;

import java.util.*;

public class ToDoList extends Format {

	private LinkedList<Task> taskList;
	private HashMap<String, ToDoList> subList;	
	private String listName;
	private ToDoList parentList;


	//constructor methods
	public ToDoList(String listName){
		this.taskList = new LinkedList<>();
		this.subList = new HashMap<>();
		this.listName = listName;
		this.parentList = null;
	}
	public ToDoList(String listName, ToDoList parentList){
		this.taskList = new LinkedList<>();
		this.subList = new HashMap<>();
		this.listName = listName;
		this.parentList = parentList;
	}
	public ToDoList(ToDoList list) {
		this.taskList = list.taskList;
		this.subList = list.subList;
		this.listName = list.listName;
		this.parentList = list.parentList;
	}


	//setters and getters
	public Task getTask(int index) {
		return taskList.get(index);
	}

	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}

	public ToDoList getParentList() {
		return parentList;
	}
	public void setParentList(ToDoList parentList) {
		this.parentList = parentList;
	}
	public LinkedList<Task> getTaskList() {
		return taskList;
	}

	public String[] getSubListKeys(){
		return subList.keySet().toArray(new String[0]);
	}

	//methods for modifying tasks
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
	public void removeTask(int index) {
		if(index < taskList.size()) {
			taskList.remove(index);
		}

	}
	public void changeTask(int index, String task) {
		if(index < taskList.size()) {
			taskList.get(index).setTask(task);
		}
	}

	//methods for changing the date
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


	//methods for modifying the sub-lists
	public void addSubList(String title, ToDoList list) {
		list.setParentList(this);
		subList.putIfAbsent(title, list);
	}
	//".." is used to go back a list and '/' is used for the path
	public void addSubList(String title) {
		if(!title.equals("..") && !title.equals("/")) {
			subList.putIfAbsent(title, new ToDoList(title, this));
		}
	}
	public void removeSubList(String key) {
		subList.remove(key);		
	}

	public boolean renameSubList(String oldKey, String newKey) {
		if(subList.containsKey(oldKey) && !oldKey.equals(newKey) && !subList.containsKey(newKey) && !newKey.equals("..")) {
			ToDoList temp = subList.get(oldKey);
			subList.put(newKey, temp);
			temp.setListName(newKey);
			subList.remove(oldKey);
			return true;
		} else {
			//move this over to the command line operations
			System.out.println("this key doesn't exist or you have entered the same name");
			return false;
		}
	}

	private String getAllSubListsFrom(ToDoList rootList, String path, String cur) {
		StringBuilder str = new StringBuilder();
		String[] subLists = rootList.getSubListKeys();
		path += '/' + cur;

		for(int i = 0; i < subLists.length; i++) {
			str.append(path);
			str.append('/');
			str.append(subLists[i]);
			str.append('\n');
			str.append(getAllSubListsFrom(rootList.getList(subLists[i]), path, subLists[i]));
		}
		return str.toString();
	}

	public String getAllSubLists() {
		return getAllSubListsFrom(this, "", "");
	}
	
	//format ex. "sublist0/sublist1/sublist2"	
	private ToDoList getListMethod(String path) {
		String key = "";
		//gets the key for the sublist and the new path	
		for(int i = 0; i < path.length(); i++) {
			if(path.charAt(i) == '/') {
				key = path.substring(0, i);	
				path = path.substring(i+1);
				break;
			} else if (i == path.length()-1) {
				key = path;
				path = "";
			}
		}

		ToDoList targetList;
		if(key.equals("..")) {
			targetList = this.parentList;
		} else {
			targetList = subList.get(key); //gets sublist
		}

		if(targetList != null && !path.equals("")) {
			return targetList.getList(path);
		}

		return targetList;//returns null if there is no list associated to the key
	}
	public ToDoList getList(String path) {
		if (path.equals("")) {
			return this;
		} else {
			return getListMethod(path);
		}
	}

	private ArrayList<String> getCurListPath() {
		ArrayList<String> val = new ArrayList<>();
		ToDoList temp = this;

		while(temp != null) {
			val.add(temp.getListName());
			temp = temp.getParentList();
		}

		return val;
	}
	public String getPath() {
		ArrayList<String> val = getCurListPath();

		StringBuilder str = new StringBuilder();
		for(int i = val.size()-2; i >= 0; i--) {
			str.append(val.get(i));
			str.append('/');
		}

		return str.toString();
	}

	public String getPathExCur() {
		ArrayList<String> val = getCurListPath();

		StringBuilder str = new StringBuilder();
		for(int i = val.size()-2; i > 0; i--) {
			str.append(val.get(i));
			str.append('/');
		}

		return str.toString();
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

	public String taskListString(boolean descSortOrder) {
		StringBuilder str = new StringBuilder();
		Iterator<Task> task = descSortOrder? taskList.descendingIterator() : taskList.iterator();

		while(task.hasNext()) {
			str.append(task.next().toString());
		}
		
		return str.toString();
	}
	public ArrayList<String> taskListArrListStr(boolean descSortOrder) {

		ArrayList<String> str = new ArrayList<>();
		Iterator<Task> task = descSortOrder? taskList.descendingIterator() : taskList.iterator();

		while(task.hasNext()) {
			str.add(task.next().toString());
		}
		return str;
	}

	public ArrayList<String> toStringArrListStr(boolean descSortOrder) {
		ArrayList<String> temp = subListArrListStr();
		temp.addAll(taskListArrListStr(descSortOrder));
		return temp;
	}

	public String toString() {
		return subListString() + taskListString(true);
	}
	public String toString(boolean descSortOrder) {
		return subListString() + taskListString(descSortOrder);
	}	
}