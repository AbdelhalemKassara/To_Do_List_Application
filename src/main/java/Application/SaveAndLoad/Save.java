package Application.SaveAndLoad;

import Application.CommandLine.Format;
import Application.DataStructures.Tables;
import Application.DataStructures.Task;
import Application.DataStructures.ToDoList;
import Application.DataStructures.User;

import java.time.LocalDateTime;
import java.util.LinkedList;

/*
format of: ToDoList
{
format {
spacingOuter : value
spacingMid : value
}
path : value
taskList: [value|value]
subList: [key:value|key:value]//don't need this I think
listName : value
}


format: Task
{
format {
spacingOuter : value
spacingMid : value
}
startDate : value
task : value
endDate : value
}

(ToDoList){(format){spacingOuter|spacingMid}{path|taskList|listName}}
(List){{spacingOuter|spacingMid}{path|taskList|listName}}

(Task){(format){spacingOuter|spacingMid}{startDate|task|endDate}}
(Task){{spacingOuter|spacingMid}{startDate|task|endDate}}

format: LocalDateTime
(LocalDateTime){year|month|dayOfMonth|hour|minute}
(Date){year|month|dayOfMonth|hour|minute}
 */

//add escape character '\' to the load and save methods for certain character patterns
public class Save {
    //format in (List){{spacingOuter|spacingMid}{path|[task1|task2]|listName}}
    public static String saveToDoList(ToDoList list, String path) {
        StringBuilder str = new StringBuilder();
        str.append("(List){");
        str.append(saveFormat(list));
        str.append('{');
        str.append(path);
        str.append("|[");
        for(Task task : list.getTaskList()) {
            str.append(saveTask(task));
            str.append('|');
        }
        str.append("\b]|");
        str.append(list.getListName());
        str.append("}}");
        return str.toString();
    }
    public static ToDoList loadToDoList(String listString, User user) {


        return null;
    }

    public static String saveFormat(Format format) {
        StringBuilder str = new StringBuilder();
        str.append('{');
        str.append(format.getSpacingOuter());
        str.append('|');
        str.append(format.getSpacingMid());
        str.append('}');
        return str.toString();
    }
    //format in {spacingOuter|spacingMid}
    public static Format loadFormat(Format format, String formatString) {
        int[] values = new int[2];

        int index = 0;
        int st = 1;
        for(int i = 1; i < formatString.length(); i++) {
            if(formatString.charAt(i) == '|' || formatString.charAt(i) == '}') {
                values[index] = Integer.parseInt(formatString.substring(st, i));
                st = i+1;
                index++;
            }
        }
        format.changeToStringWidth(values[1],values[0]);
        return format;
    }
    public static String saveTask(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("(Task){");
        str.append(saveFormat(task));
        str.append('{');
        LocalDateTime start = task.getStartDate();
        str.append(saveLocalDateTime(start));
        str.append('|');
        str.append(task.getTask());
        str.append('|');
        str.append(saveLocalDateTime(task.getEndDate()));
        str.append("}}");
        return str.toString();
    }

    //takes in {{spacingOuter|spacingMid}{startDate|task|endDate}}
    public static Task loadTask(String taskString) {
        //this part gets the format (spacingOuter and spacingMid)
        int spacingOuter = 28; //default values
        int spacingMid = 27;
        int st = 2;
        for(int i = 2; i < taskString.length(); i++) {
            if(taskString.charAt(i) == '|') {
                spacingOuter = Integer.parseInt(taskString.substring(st,i));
                st = i+1;
            } else if(taskString.charAt(i) == '}') {
                spacingMid = Integer.parseInt(taskString.substring(st, i));
                taskString = taskString.substring(i+1, taskString.length()-1);
                break;
            }
        }

        //this parts deals with the task(StartDate, task, EndDate)
        int openBracketNum = 0;
        String[] values = new String[3];
        int index = 0;
        st = 1;
        for(int i = 0; i < taskString.length(); i++) {
            if(taskString.charAt(i) == '{') {
                openBracketNum++;
            } else if(taskString.charAt(i) == '}') {
                openBracketNum--;
            }

            if((taskString.charAt(i) == '|' && openBracketNum == 1) || taskString.length()-1 == i) {
                values[index] = taskString.substring(st,i);
                st = i+1;
                index++;
            }
        }

        Task task = new Task(loadLocalDateTime(values[0].substring(6)), values[1], loadLocalDateTime(values[2].substring(6)));
        task.changeToStringWidth(spacingMid,spacingOuter);
        return task;
        //this part deals with the task
    }
    public String saveUser(User user) {
        return "";
    }
    public String saveTable(Tables tables) {
        return "";
    }

    public static String saveLocalDateTime(LocalDateTime date) {
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
    //takes in {year|month|dayOfMonth|hour|minute}
    public static LocalDateTime loadLocalDateTime(String date) {
    int[] values = new int[5];

    if(date.length() == 6) {
        return null;
    }

    int st = 1;
    int index = 0;
    for(int i = 1; i < date.length(); i++) {
        if(date.charAt(i) == '|' || date.charAt(i) == '}') {
            values[index] = Integer.parseInt(date.substring(st,i));
            st = i+1;
            index++;
        }
    }

    return LocalDateTime.of(values[0], values[1], values[2], values[3], values[4]);
    }

    public static void main(String[] args) {
    //test date load and save
        //LocalDateTime date = LocalDateTime.of(2002,1,2,1,1);
        //System.out.println(saveLocalDateTime(date));
        //System.out.println(loadLocalDateTime(saveLocalDateTime(date).substring(6)));
        //System.out.println(loadLocalDateTime(saveLocalDateTime(null).substring(6)));

    //test task load and save
        //Task task = new Task("this is a task", 2020,1,1,1,1);
        //System.out.println(saveTask(task));
        //System.out.println(loadTask(saveTask(task).substring(6)));

    //test ToDoList save
        ToDoList list = new ToDoList("list 1");
        list.addTask("task1",2020,1,1,1,1);
        list.addTask("task2",2012,2,2,2,2);
        System.out.println(saveToDoList(list,"\\"));
    }
}
