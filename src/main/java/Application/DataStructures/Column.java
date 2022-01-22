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
    public ArrayList<String> toStringArrListDesc() {
        ArrayList<LinkedList<Task>> listsOfTasks = getListsOfTasks();
        ArrayList<String> columnStr = new ArrayList<>();

        if(maxMinDays != 0) {
            //removes the tasks outside the minimum range
            Task max;
            if (dayOfWeek > 0 && dayOfWeek < 8) {
                max = getMaxMinFixWeeks(true);
            } else {
                max = getMaxMinDays(true);
            }
            //removes the tasks outside the maximum range
            for (int i = 0; i < listsOfTasks.size(); i++) {
                while (listsOfTasks.get(i).size() > 0 && listsOfTasks.get(i).getLast().compareTo(max) > 0) {
                    listsOfTasks.get(i).removeLast();
                }
            }
        }
        //this adds tasks
        while(true) {
            int index = 0;
            Task top = null;
            for(int i = 0; i < listsOfTasks.size(); i++) {
                if(listsOfTasks.get(i).isEmpty()) {
                    continue;
                }
                //the lists within listsOfTasks should already be sorted from least to greatest
                if(top == null || top.compareTo(listsOfTasks.get(i).getLast()) == -1) {
                    top = listsOfTasks.get(i).getLast();
                    index = i;
                }
            }
            if(top == null) {
                break;
            } else {
                columnStr.add(top.toString());
                listsOfTasks.get(index).removeLast();
            }
        }

        return columnStr;
    }
    public ArrayList<String> toStringArrList(boolean descSortOrder) {
        return descSortOrder? toStringArrListDesc() : toStringArrListAsc();
    }
    public ArrayList<String> toStringArrListAsc() {
        ArrayList<LinkedList<Task>> listsOfTasks = getListsOfTasks();
        ArrayList<String> columnStr = new ArrayList<>();

        if(maxMinDays != 0) {
            Task min;
            if(dayOfWeek > 0 && dayOfWeek < 8) {
                min = getMaxMinFixWeeks(false);
                System.out.println(min);
            } else {
                 min = getMaxMinDays(false);
            }
            //removes the tasks outside the minimum range
            for(int i = 0; i < listsOfTasks.size(); i++) {
                while(listsOfTasks.get(i).size() > 0 && listsOfTasks.get(i).getFirst().compareTo(min) < 0) {
                    listsOfTasks.get(i).removeFirst();
                }
            }
        }


        //this adds tasks
        while(true) {
            int index = 0;
            Task top = null;
            for(int i = 0; i < listsOfTasks.size(); i++) {
                if(listsOfTasks.get(i).isEmpty()) {
                    continue;
                }
                //the lists within listsOfTasks should already be sorted from least to greatest
                //so the elements at the bottom of each list is the greatest element in the list
                if(top == null || top.compareTo(listsOfTasks.get(i).getFirst()) == 1) {
                    top = listsOfTasks.get(i).getFirst();
                    index = i;
                }
            }
            //if all the lists are empty top will be null
            if(top == null) {
                break;
            } else {
                columnStr.add(top.toString());
                listsOfTasks.get(index).removeFirst();
            }
        }
        return columnStr;
    }
}
