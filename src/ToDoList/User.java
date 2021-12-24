package ToDoList;

import ToDoList.ToDoList;
import ToDoList.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class User extends ToDoList{
    HashMap<String,Tables> tables = new HashMap<>();

    public void addTable(String name) {
        tables.put(name, new Tables());
    }
    public void addToTable(String name, ListItem list) {
        if(tables.containsKey(name)) {
            tables.get(name).addList(list);
        }
    }

    public String getTable(String name) {
        if(tables.containsKey(name)) {
            return name + "\n\n" + tables.get(name).toString();
        }
        return "";
    }

    public void deleteTable(String name) {
        tables.remove(name);
    }
    public void removeFromTable(String name, ListItem list) {
        tables.remove(name).removeList(list);
    }
}
