package Application.DataStructures;

import java.util.ArrayList;

public class Tables {
    protected ArrayList<ToDoList> toDoList;

    public Tables() {
        toDoList = new ArrayList<>();
    }
    public void addList(ToDoList list) {

        toDoList.add(list);
    }


    //implement this in operations
    public boolean removeList(String list) {
        int index = -1;
        for(int i = 0; i < toDoList.size(); i++) {
            if(toDoList.get(i).getListName().equals(list)) {
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

    public String getLists() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < toDoList.size(); i++) {
            str.append(toDoList.get(i).getListName());
            str.append(", ");
        }
        return str.toString();
    }

    public String toString() {
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        int space = 0;
        int max = -1;
        for(int i = 0; i < toDoList.size(); i++) {
            test.add(toDoList.get(i).toStringArrListStr());

            String name = toDoList.get(i).getListName();
            //+10 because of whitespace and | in the format and the || for spacing between the lists
            space = toDoList.get(i).getSpacingMid() + 2 * toDoList.get(i).getSpacingOuter() + 10;
            int len = space - toDoList.get(i).getListName().length();
            if(len < 0) {
                name = toDoList.get(i).getListName().substring(0, space-4);
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
