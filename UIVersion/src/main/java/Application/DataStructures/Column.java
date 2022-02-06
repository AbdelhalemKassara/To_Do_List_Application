package Application.DataStructures;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Column extends Format {
    private LinkedList<ToDoList> lists;
    private int maxMinDays = 0;
    private int dayOfWeek = 0;//1 (Monday) to 7 (Sunday)

    public Column() {
        lists = new LinkedList<>();
    }
    public void addList(ToDoList list) {
        lists.add(list);
    }

    public void removeList(String listName) {
        Iterator<ToDoList> listsIt = lists.iterator();

        while(listsIt.hasNext()) {
            ToDoList list = listsIt.next();
            if(listName.equals(list.getListName())) {
                lists.remove(list);
            }
        }
    }

    public LinkedList<ToDoList> getLists() {
        return lists;
    }

    public LinkedList<String> getListsArrStr() {
        LinkedList<String> str = new LinkedList<>();

        for(ToDoList list : lists) {
            str.add(list.getListName());
        }

        return str;
    }

    public void setMaxMinDays(int days) {
        maxMinDays = Math.abs(days);
    }
    public void setDayOfWeek(int days, int dayOfWeek) {
        this.maxMinDays = days;
        this.dayOfWeek = dayOfWeek;
    }

    private Task getMaxMinDays(boolean descSortOrder) {
        return new Task(null,"", setDateOffset(LocalDateTime.now(), descSortOrder));
    }
    private LocalDateTime setDateOffset(LocalDateTime value, boolean descSortOrder) {
        if(descSortOrder) {
            value = LocalDateTime.of(value.getYear(), value.getMonth(), value.getDayOfMonth(),23, 59, 59,99);
            value = value.plusDays(maxMinDays);
        } else {
            value = LocalDateTime.of(value.getYear(), value.getMonth(), value.getDayOfMonth(),0, 0, 0,0);
            value = value.minusDays(maxMinDays);
        }
        return value;
    }
    private Task getMaxMinFixWeeks(boolean descSortOrder) {
        LocalDateTime temp = LocalDateTime.now();
        temp = temp.minusDays(temp.getDayOfWeek().getValue() - dayOfWeek);
        return new Task(null, "", setDateOffset(temp, descSortOrder));
    }

    private ArrayList<LinkedList<Task>> getListsOfTasks() {
        ArrayList<LinkedList<Task>> listsOfTasks = new ArrayList<>();
        //adds the list of tasks from each todolist
        for(int i = 0; i < lists.size(); i++) {
            listsOfTasks.add(new LinkedList<>(lists.get(i).getTaskList()));
        }
        return listsOfTasks;
    }
}
