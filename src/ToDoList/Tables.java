package ToDoList;

import java.util.ArrayList;
import java.util.List;

public class Tables {
    private ArrayList<ListItem> toDoList;
    public Tables() {
        toDoList = new ArrayList<>();
    }
    public void addList(ToDoList list, String name) {

        toDoList.add(new ListItem(list, name));
    }

    public String toString() {
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        int min = -1;
        for(int i = 0; i < toDoList.size(); i++) {
            test.add(toDoList.get(i).getList().toStringArrListStr());

            String name = toDoList.get(i).getName();
            //+10 because of whitespace and | in the format and the || for spacing between the lists
            int space = toDoList.get(i).getList().getSpacingMid() + 2 * toDoList.get(i).getList().getSpacingOuter() + 10;
            int len = space - toDoList.get(i).getName().length();
            if(len < 0) {
                name = toDoList.get(i).getName().substring(0, space-4);
                len = 4;//since we are using 4 characters for the border between the lists " || "
            }
            str.append(name);
            for(int d = 0; d < len; d++) {
                str.append(" ");
            }

            if(min == -1) {
                min = test.get(i).size();
            } else {
                min = Math.min(test.get(i).size(), min);
            }
        }
            str.append("\n");

        for(int d = 0; d < min; d++) {
            for(int i = 0; i < test.size(); i++) {
                str.append(test.get(i).get(d));
                str.append(" || ");
            }
            str.append("\b\b\b\n");

        }

        return str.toString();
    }
    class ListItem {
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
}
