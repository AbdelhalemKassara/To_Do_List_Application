package Application.SaveAndLoad;

import Application.DataStructures.Format;
import Application.DataStructures.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

//add escape character '\' to the load and save methods for certain character patterns
public class Save {


    //saves tables, user list, then all sub-lists
    //outputs: a userName.user file
     public static void saveUser(User user) throws FileNotFoundException {
        PrintWriter outputFile = new PrintWriter("UserFiles/" + user.getListName() + ".user");

        StringBuilder str = new StringBuilder();

        //adding the tables
        str.append("(TablesList){");
        for(Map.Entry<String,Tables> table : user.getTables().entrySet()) {
            str.append(saveTable(table.getValue(),table.getKey()));
            str.append('|');
        }
        if(str.charAt(str.length()-1) != '{') {
            str.deleteCharAt(str.length()-1);
        }
        str.append("}\n");

        //adding the user todolist
        str.append(saveToDoList(user,"User"));
        str.append('\n');

        //getting the paths to all todolist in this user
        String pathsStr = user.getAllSubLists();
        ArrayList<String> paths = new ArrayList<>();

        int st = 2;
        for(int i = 0; i < pathsStr.length(); i++) {
            if(pathsStr.charAt(i) == '\n') {
                paths.add(pathsStr.substring(st,i));
                st = i + 3;
            }

        }

        //adding the subtodolists
        for(int i = 0; i < paths.size(); i++) {
            ToDoList list = user.getList(paths.get(i));
            str.append(saveToDoList(list, list.getPathExCur()));
            str.append('\n');
        }
        if(str.charAt(str.length()-1) == '\n') {
            str.deleteCharAt(str.length()-1);//removes the last \n

        }
        outputFile.print(str.toString());
        outputFile.close();

     }


    //output (Tables){list1|list2|list3|listn}
    private static String saveTable(Tables table, String tableName) {
        StringBuilder str = new StringBuilder();
        ArrayList<ToDoList> lists = table.getToDoList();

        str.append("{");
        str.append(tableName);
        str.append('|');
        for(int i = 0; i < lists.size(); i++) {
            str.append(lists.get(i).getPath());
            str.append('|');
        }
        if(str.charAt(str.length()-1) != '{') {
            str.deleteCharAt(str.length()-1);
        }
        str.append('}');

        return str.toString();
    }

    //replace the [] with {}, the [] are here just to make it easier to see here and using {} simplifies the code
    //format of output: (path){{spacingOuter|spacingMid}{[task1|task2|taskn]|listName}}
    private static String saveToDoList(ToDoList list, String path) {

        StringBuilder str = new StringBuilder();
        str.append("(");
        str.append(path);
        str.append("){");
        str.append(saveFormat(list));
        str.append("{{");
        for(Task task : list.getTaskList()) {
            str.append(saveTask(task));
            str.append('|');
        }
        if(str.charAt(str.length()-1) != '{') {
            str.deleteCharAt(str.length()-1);

        }
        str.append("}|");
        str.append(list.getListName());
        str.append("}}");
        return str.toString();
    }
    //format of output: {spacingOuter|spacingMid}
    private static String saveFormat(Format format) {
        StringBuilder str = new StringBuilder();
        str.append('{');
        str.append(format.getSpacingOuter());
        str.append('|');
        str.append(format.getSpacingMid());
        str.append('}');
        return str.toString();
    }
    //format of output: (Task){{spacingOuter|spacingMid}{(Date){year|month|day|hour|min}|task|(Date){year|month|day|hour|min}}
    private static String saveTask(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("(Task){");
        str.append(saveFormat(task));
        str.append('{');
        str.append(saveLocalDateTime(task.getStartDate()));
        str.append('|');
        str.append(task.getTask());
        str.append('|');
        str.append(saveLocalDateTime(task.getEndDate()));
        str.append("}}");
        return str.toString();
    }
    private static String saveLocalDateTime(LocalDateTime date) {
        if(date == null) {
            return "(Date){||||}";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("(Date){");
            str.append(date.getYear());
            str.append('|');
            str.append(date.getMonthValue());
            str.append('|');
            str.append(date.getDayOfMonth());
            str.append('|');
            str.append(date.getHour());
            str.append('|');
            str.append(date.getMinute());
            str.append('}');
            return str.toString();
        }
    }
}