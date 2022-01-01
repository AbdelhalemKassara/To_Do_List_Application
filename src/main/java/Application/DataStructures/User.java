package Application.DataStructures;

import java.util.HashMap;

public class User extends ToDoList {
    HashMap<String, Tables> tables = new HashMap<>();

    public User() {
        super("root");
    }

    public void addTable(String name) {
        if(!tables.containsKey(name)) {
            tables.put(name, new Tables());
        }
    }
    public void addToTable(String name, ToDoList list) {
            tables.get(name).addList(list);
    }

    public String getTable(String name) {
        if(tables.containsKey(name)) {
            return name + "\n\n" + tables.get(name).toString();
        }
        return "";
    }
    public String[] getTableNames() {
        return tables.keySet().toArray(new String[0]);
    }
    public void deleteTable(String name) {
        tables.remove(name);
    }
    public void removeFromTable(String name, String listName) {
        tables.remove(name).removeList(listName);
    }
    public void renameTable(String oldName, String newName) {
        if (tables.containsKey(oldName) &&  !tables.containsKey(newName)) {
            tables.put(newName, tables.get(oldName));
            tables.remove(oldName);
        } else {
            System.out.println("the old name doesn't exist or the new name is an existing table");
        }
    }
    public HashMap<String, Tables> getTables() {
        return tables;
    }
}
