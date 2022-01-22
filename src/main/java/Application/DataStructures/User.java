package Application.DataStructures;

import java.util.HashMap;
import Application.DataStructures.Tables;

public class User extends ToDoList {
    HashMap<String, Tables> tables = new HashMap<>();
    private boolean greatestToLeast = true;

    public User() {
        super("root");
    }

    public User(ToDoList list) {
        super(list);
    }
    public boolean addTable(String name) {
        if(!tables.containsKey(name)) {
            tables.put(name, new Tables());
            return true;
        }
        return false;
    }
    public boolean addTable(String name, Tables table) {
        if(!tables.containsKey(name)) {
            tables.put(name, table);
            return true;
        }
        return false;
    }
    public boolean addToTable(String name, ToDoList list) {
            Tables temp = tables.get(name);
            if(temp != null) {
                temp.addList(list);
                return true;
            } else {
                return false;
            }
    }

    public String getTable(String name, boolean descSortOrder) {
        if(tables.containsKey(name)) {
            return name + "\n\n" + tables.get(name).toString(descSortOrder);
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

    public void setGreatestToLeast(boolean greatestToLeast) {
        this.greatestToLeast = greatestToLeast;
    }
    public boolean getGreatestToLeast() {
        return greatestToLeast;
    }
}
