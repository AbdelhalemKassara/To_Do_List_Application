package Application.DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;

public class Column {
    ArrayList<ToDoList> lists;

    public Column() {
        lists = new ArrayList<>();
    }
    public void addList(ToDoList list) {
        lists.add(list);
    }
    //remove list by name

    public ArrayList<String> toStringArrList() {
        ArrayList<LinkedList<Task>> listsOfTasks = new ArrayList<>();
        for(int i = 0; i < lists.size(); i++) {
            listsOfTasks.add(new LinkedList<>(lists.get(i).getTaskList()));
        }

        ArrayList<String> columnStr = new ArrayList<>();

        while(true) {
            int index = 0;
            Task top = null;
            for(int i = 0; i < listsOfTasks.size(); i++) {
                if(listsOfTasks.get(i).isEmpty()) {
                    continue;
                }
                if(top == null || top.compareTo(listsOfTasks.get(i).getLast()) == -1) {
                    top = listsOfTasks.get(i).getLast();
                    index = i;
                }
            }
            if(top == null) {
                break;
            } else {
                columnStr.add(top.toString());
                listsOfTasks.get(index).removeLast();
            }
        }

        return columnStr;
    }
    public String toString() {
        ArrayList<LinkedList<Task>> listsOfTasks = new ArrayList<>();
        for(int i = 0; i < lists.size(); i++) {
            listsOfTasks.add(new LinkedList<>(lists.get(i).getTaskList()));
        }

        StringBuilder columnStr = new StringBuilder();

        while(true) {
            int index = 0;
            Task top = null;
            for(int i = 0; i < listsOfTasks.size(); i++) {
                if(listsOfTasks.get(i).isEmpty()) {
                    continue;
                }
                if(top == null || top.compareTo(listsOfTasks.get(i).getLast()) == -1) {
                    top = listsOfTasks.get(i).getLast();
                    index = i;
                }
            }
            if(top == null) {
                break;
            } else {
                columnStr.append(top.toString());
                listsOfTasks.get(index).removeLast();
            }
        }

        return columnStr.toString();
    }
}
