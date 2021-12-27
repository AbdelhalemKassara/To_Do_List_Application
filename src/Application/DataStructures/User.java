package Application.DataStructures;

import Application.DataStructures.ListItem;
import Application.DataStructures.Tables;
import Application.DataStructures.ToDoList;

import java.util.HashMap;

public class User extends ToDoList {
    HashMap<String, Tables> tables = new HashMap<>();

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
    public String[] getTableNames() {
        return tables.keySet().toArray(new String[0]);
    }

    public void deleteTable(String name) {
        tables.remove(name);
    }
    public void removeFromTable(String name, ListItem list) {
        tables.remove(name).removeList(list);
    }

    public void renameTable(String oldName, String newName) {
        if (tables.containsKey(oldName) && !oldName.equals(newName)) {
            tables.put(newName, tables.get(oldName));
            tables.remove(oldName);
        } else {
            System.out.println("the names are the same or the old name doesn't exist");
        }
    }
}
