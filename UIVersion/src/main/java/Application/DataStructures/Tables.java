package Application.DataStructures;

import java.util.ArrayList;

public class Tables {
    protected ArrayList<ArrayList<ToDoList>> toDoList;

    public Tables() {
        toDoList = new ArrayList<>();
    }
    public void addList(ToDoList list) {
        ArrayList<ToDoList> temp = new ArrayList<>();
        temp.add(list);
        toDoList.add(temp);
    }

    public boolean removeList(String list) {
        int index = -1;
        for(int i = 0; i < toDoList.size(); i++) {
            if(toDoList.get(i).get(0).getListName().equals(list)) {
                index = i;
            }
        }
        if(index == -1) {
            return false;
        } else {
            toDoList.remove(index);
            return true;
        }

    }
    public ArrayList<ToDoList> getToDoList() {
        ArrayList<ToDoList> temp = new ArrayList<>();
            for(ArrayList<ToDoList> list : toDoList) {
                temp.add(list.get(0));
            }
        return temp;
    }

}
