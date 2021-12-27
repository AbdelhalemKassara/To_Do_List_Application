package Application.DataStructures;

import Application.DataStructures.ListItem;
import Application.DataStructures.ToDoList;

import java.util.ArrayList;

public class Tables {
    protected ArrayList<ListItem> toDoList;
    public Tables() {
        toDoList = new ArrayList<>();
    }
    public void addList(ToDoList list, String name) {

        toDoList.add(new ListItem(list, name));
    }
    public void addList(ListItem list) {
        toDoList.add(list);
    }
    public void removeList(ListItem list) {
        toDoList.remove(list);
    }

    public String toString() {
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        int space = 0;
        int max = -1;
        for(int i = 0; i < toDoList.size(); i++) {
            test.add(toDoList.get(i).getList().toStringArrListStr());

            String name = toDoList.get(i).getName();
            //+10 because of whitespace and | in the format and the || for spacing between the lists
            space = toDoList.get(i).getList().getSpacingMid() + 2 * toDoList.get(i).getList().getSpacingOuter() + 10;
            int len = space - toDoList.get(i).getName().length();
            if(len < 0) {
                name = toDoList.get(i).getName().substring(0, space-4);
                len = 4;//since we are using 4 characters for the border between the lists " || "
            }
            str.append(name);
            for(int d = 0; d < len; d++) {
                str.append(" ");
            }
            max = Math.max(test.get(i).size(), max);
        }
        str.append("\n");

        for(int d = 0; d < max; d++) {
            for(int i = 0; i < test.size(); i++) {
                if(d < test.get(i).size()) {
                    str.append(test.get(i).get(d));
                    str.append(" || ");
                } else {
                    for(int s = 0; s < space; s++) {
                        str.append(" ");
                    }
                }
            }
            str.append("\b\b\b\n");

        }

        return str.toString();
    }
}
