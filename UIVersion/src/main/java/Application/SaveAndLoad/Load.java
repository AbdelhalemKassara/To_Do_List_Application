package Application.SaveAndLoad;


import Application.DataStructures.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {

    //takes in {{spacingOuter|spacingMid}{[task1|task2]|listName}}
    public static ToDoList loadToDoList(String listString) {
        ArrayList<String> splitToDoListObject = splitSubObjects(listString);

        ArrayList<String> toDoListValues = splitObjects(splitToDoListObject.get(1));

        //creates the ToDoList and adds it's name
        ToDoList list = new ToDoList(toDoListValues.get(1));

        //sets the format
        loadFormat(list, splitToDoListObject.get(0));


        //loads the tasks
        ArrayList<String> tasks = new ArrayList<>();
        if(toDoListValues.get(0).length() > 2) {
            tasks = splitObjects(toDoListValues.get(0));

        }
        for(int i = 0; i < tasks.size(); i++) {
            list.addTask(loadTask(tasks.get(i).substring(6)));
        }
        return list;
    }
    //takes in {year|month|dayOfMonth|hour|minute} assume everything is valid
    public static LocalDateTime loadLocalDateTime(String date) {
        if(date.length() == 6) {
            return null;
        }

        ArrayList<String> values = splitObjects(date);
        return LocalDateTime.of(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)),
                Integer.parseInt(values.get(2)), Integer.parseInt(values.get(3)), Integer.parseInt(values.get(4)));
    }

    //format in {spacingOuter|spacingMid}
    public static void loadFormat(Format format, String formatString) {
        ArrayList<String> values = splitObjects(formatString);
        format.changeToStringWidth(Integer.parseInt(values.get(1)), Integer.parseInt(values.get(0)));
    }
    //takes in {{spacingOuter|spacingMid}{startDate|task|endDate}}
    public static Task loadTask(String taskString) {
        //this contains the format and task values
        ArrayList<String> SplitTaskObject = splitSubObjects(taskString);

        //this contains the start date task and end date in that order
        ArrayList<String> TaskValues = splitObjects(SplitTaskObject.get(1));

        // the .substring(6) removes the date label
        Task task = new Task(loadLocalDateTime(TaskValues.get(0).substring(6)), TaskValues.get(1),
                loadLocalDateTime(TaskValues.get(2).substring(6)));
        loadFormat(task, SplitTaskObject.get(0));

        return task;
    }
    //takes in {tableName|path1|path2|pathn}
    public static void loadTable(String tableString, User user) {
        if(tableString.equals("")) {
            return;
        }

        ArrayList<String> values = splitObjects(tableString);
        Tables table = new Tables();

        for(int i = 1; i < values.size(); i++) {
            table.addList(user.getList(values.get(i)));
        }
        user.addTable(values.get(0), table);
    }
    public static User loadUser(String userName) throws FileNotFoundException {

        File userFile = new File("UserFiles/" + userName + ".user");
        if(!userFile.isFile()) {
            return null;
        }

        Scanner in = new Scanner(userFile);
        String tablesRaw = in.nextLine();


        //load user
        User user = new User(loadToDoList(in.nextLine().substring(6)));

        //load sublists
        while(in.hasNext()) {
            String line = in.nextLine();

            for(int i = 1; i < line.length(); i++) {
                if(line.charAt(i) == '{' && line.charAt(i-1) == ')') {
                    ToDoList list = loadToDoList(line.substring(i));
                    user.getList(line.substring(1,i-1)).addSubList(list.getListName(), list);
                    break;
                }
            }
        }
        //load the array of tables
        ArrayList<String> tables = splitObjects(tablesRaw.substring(12));
        for(int i = 0; i < tables.size(); i++) {
            loadTable(tables.get(i), user);
        }
        return user;
    }
    //takes in {{something}{something else}{something else 1}{something else n}}
    private static ArrayList<String> splitSubObjects(String value) {
        int st = 1;
        int bracketNum = 1;
        ArrayList<String> splitVals = new ArrayList<>();

        for(int i = 1; i < value.length(); i++) {
            if(value.charAt(i) == '{') {
                bracketNum++;
            } else if(value.charAt(i) == '}') {
                bracketNum--;
            }

            if(bracketNum == 1) {
                splitVals.add(value.substring(st, i+1));
                st = i+1;
            }
        }

        return splitVals;
    }
    //takes in {something1|something2|something3|somethingN}
    private static ArrayList<String> splitObjects(String value) {
        int st = 1;
        int bracketNum = 0;
        ArrayList<String> splitVals = new ArrayList<>();

        for(int i = 0; i < value.length(); i++) {
            if(value.charAt(i) == '{') {
                bracketNum++;
            } else if(value.charAt(i) == '}') {
                bracketNum--;
            }

            if(bracketNum == 1 && value.charAt(i) == '|' || value.length()-1 == i) {
                splitVals.add(value.substring(st, i));
                st = i+1;
            }
        }
        return splitVals;
    }
}
