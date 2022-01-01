package Application.SaveAndLoad;

import Application.CommandLine.Format;
import Application.DataStructures.Tables;
import Application.DataStructures.Task;
import Application.DataStructures.ToDoList;
import Application.DataStructures.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

/*
save table
save user
 */
//add escape character '\' to the load and save methods for certain character patterns
public class Save {
    //takes in {{something}{something else}{something else 1}}
    public static ArrayList<String> splitSubObjects(String value) {
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
    //takes in {something|something|something} or {[task1|task2]|listName}
    public static ArrayList<String> splitObjects(String value) {
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

    //save methods
    public String saveUser(User user) {

        return "";
    }
    public String saveTable(Tables tables) {

        return "";
    }
    //replace the [] with {}, the [] are here just to make it easier to see here and using {} simplifies the code
    //format out (path){{spacingOuter|spacingMid}{[task1|task2]|listName}}
    public static String saveToDoList(ToDoList list, String path) {
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
        str.deleteCharAt(str.length()-1);
        str.append("}|");
        str.append(list.getListName());
        str.append("}}");
        return str.toString();
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

    //load methods
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

    //takes in {{spacingOuter|spacingMid}{[task1|task2]|listName}}
    public static ToDoList loadToDoList(String listString) {
        ArrayList<String> splitToDoListObject = splitSubObjects(listString);

        ArrayList<String> toDoListValues = splitObjects(splitToDoListObject.get(1));

        //creates the ToDoList and adds it's name
        ToDoList list = new ToDoList(toDoListValues.get(1));

        //sets the format
        loadFormat(list, splitToDoListObject.get(0));
       // System.out.println(toDoListValues.get(0));

        //loads the tasks
        ArrayList<String> tasks = splitObjects(toDoListValues.get(0));
        for(int i = 0; i < tasks.size(); i++) {
            list.addTask(loadTask(tasks.get(i).substring(6)));
        }
        return list;
    }


    public static void main(String[] args) {
    //test date load and save
        //LocalDateTime date = LocalDateTime.of(2002,1,2,1,1);
        //System.out.println(saveLocalDateTime(date));
        //System.out.println(loadLocalDateTime(saveLocalDateTime(date).substring(6)));
        //System.out.println(loadLocalDateTime(saveLocalDateTime(null).substring(6)));

    //test task load and save
        Task task = new Task("this is a task", 2020,1,1,1,1);
        //System.out.println(saveTask(task).substring(6));
        //  System.out.println(loadTaskUpdated(saveTask(task).substring(6)));

    //test ToDoList save
        ToDoList list = new ToDoList("list 1");
        list.addTask("task1",2020,1,1,1,1);
        list.addTask("task2",2012,2,2,2,2);
        list.addTask(2000,1,4,3,1,"something else", 2023,2,2,2,2);
        System.out.println(loadToDoList(saveToDoList(list,"\\").substring(3)));

    //test splitObjects and splitSubObjects
        //splitObjects("{(Date){||||}|task2|(Date){2012|2|2|2|2}}");
        //ArrayList<String> val = splitObjects("{(Task){{28|27}{(Date){||||}|task2|(Date){2012|2|2|2|2}}}|(Task){{28|27}{(Date){||||}|task1|(Date){2020|1|1|1|1}}}}");
        //ArrayList<String> val = splitObjects("{something else|something else 2|asdfsda|;ldfkas;lkdas}");
        //System.out.println(val.size());
        //for(int i = 0; i < val.size(); i++) {
        //    System.out.println(val.get(i));
        //}
        //splitSubObjects("{{something}{something else}{something else 1}}");


    }
    /*
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

    //format in {{spacingOuter|spacingMid}{[task1|task2]|listName}}
    public static ToDoList loadToDoList(String listString) {
        //String[] split = listString.split("}{");


        return null;
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
    */


}

