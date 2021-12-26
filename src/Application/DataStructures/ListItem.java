package Application.DataStructures;

import Application.DataStructures.ToDoList.ToDoList;

public class ListItem {
    private ToDoList list;
    private String name;

    public ListItem(ToDoList list, String name) {
        this.list = list;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public ToDoList getList() {
        return list;
    }

    public void setList(ToDoList list) {
        this.list = list;
    }
    public void setName(String name) {
        this.name = name;
    }
}