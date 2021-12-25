package ToDoList;
/* store the current "directory"

create new list in some list
create new task in some list
create a new table

add a list to a table

move a list into another list
move a task into another list

print all list names at the current location
print list at the current location
print all table names
print some table
print all lists (from root)
print all the lists from the current location (including sublists)

delete a task
delete a table
delete a list

change the "task" of a task
change the date of a task
change the name of a list
change the name of a table

completed


 */

import java.util.ArrayList;

//probably will need a lot of try catch block
public class Parser {
    private String dir;
    private User user;
    private ToDoList curList;

    public Parser(User user) {
        this.user = user;
    }
    //I think the user input should be split in blocks
    //the first block is what we want to do in one work
    //the next are it's inputs with commas in between without any spaces
    //
    public void parseUserInput(String input) {
        String operation;
        ArrayList<String> values = new ArrayList<>();

        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                operation = input.substring(0, i);
                input = input.substring(i);
            }
        }

        //removes the whitespace if there are inputs
        if(input.length() > 1) {
            input = input.substring(1);
        }
        //check if there are commands that have no arguments here add a continue or return after if it meets a condition
        int stIndex = 0;

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ',') {
                values.add(input.substring(stIndex, i);
                stIndex = i+1;
            }
        }

        /*
        if (input.length() > 3) {
            //check if the user wants to add something
            if(input.substring(0,3).equals("add")) {
                input = input.substring(5);
                String addWhat = input.substring(0,4);
                //checking if the user wants to add a new list
                if(addWhat.equals("list") || addWhat.equals("List")) {
                    input = input.substring(6);
                    addList(input);
                } else if(addWhat.equals("task") || addWhat.equals("Task")) {
                    //skip whitespace and start quote
                    input = input.substring(7);
                    addTask(input);
                } else if (input.substring(0,5).equals("table") || input.substring(0,5).equals("Table")) {
                    input = input.substring(6);
                    addTable(input);
                }

            }
        }

         */
    }
    public void addList(String name) {
        curList.addSubList(name);
    }

    public void addTask(String input) {
        String task = "";
        int[] values = new int[10];
        //should check for start quote but i'm not doing it here

        //this is getting the task
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '"') {
                task = input.substring(0, i);
                input = input.substring(i+2);//skips end quote and white space
            }
        }

        int index = 0;
        int startIndex = 0;
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                values[index] = Integer.parseInt(input.substring(startIndex, i));
                startIndex = i+1;
                index++;
            } else if (i+1 == input.length()) {
                values[index] = Integer.parseInt(input.substring(startIndex, i + 1));
            }
        }
        if(index == 9) {
            curList.addTask(values[0], values[1], values[2], values[3], values[4], task, values[5], values[6], values[7], values[8], values[9]);
        } else if(index == 4) {
            curList.addTask(task, values[0], values[1], values[2], values[3], values[4]);
        }
    }
    public void addTable(String name) {
        user.addTable(name);
    }
    public void addListToTable(String nameOfTable, String path) {
        user.addToTable(nameOfTable,user.getListWithName(path));
    }
    public void moveList(String pathMove,String nameOfList, String pathNewLoc) {
        ToDoList temp = user.getList(pathMove);
        user.getList(pathNewLoc).addSubList(nameOfList, temp.getList(nameOfList));
        temp.removeSubList(nameOfList);
    }
    public void moveTask(String pathToList, int index, String pathToNewList) {
        user.getList(pathToNewList).addTask(user.getList(pathToList).getTask(index));
        user.getList(pathToList).removeTask(index);
    }
    public void listNames() {
        String[] str = curList.getSubListKeys();

        for(int i = 0; i < str.length; i++) {
            System.out.print(str[i] + ", ");
        }
        System.out.println("\b\b\n");
    }

    public void printList() {
        System.out.println(curList.toString() + '\n');
    }
    public void listTableNames() {
        String[] str = user.getTableNames();

        for(int i = 0; i < str.length; i++) {
            System.out.print(str[i] + ", ");
        }
        System.out.println("\b\b\n");
    }
    public void printTable(String name) {
        System.out.println(user.getTable(name));
    }

    //not the best way to do it but I want to see if it will be noticibly slow
    public String printSubListsFromCur(ToDoList temp, String path, String cur) {
        StringBuilder str = new StringBuilder();
        String[] subLists = temp.getSubListKeys();
        path += '/' + cur;

        for(int i = 0; i < subLists.length; i++) {
            str.append(path);
            str.append('/');
            str.append(subLists[i]);
            str.append('\n');
            str.append(printSubListsFromCur(temp.getList(subLists[i]), path, subLists[i]));
        }
        return str.toString();
    }
    public String printSubListsBase(ToDoList temp) {
        return printSubListsFromCur(temp, "", "");
    }
    public String printSubListsFromRoot() {
        return printSubListsFromCur(user,"","");
    }

    //delete this method, this was just for testing the method above
    public void tewt(ToDoList list) {
        user.addSubList("temp",list);
    }

    public void deleteTable(String name) {
        user.deleteTable(name);
    }
    public void deleteTask(int index) {
        curList.removeTask(index);
    }
    public void deleteList(String name) {
            curList.removeSubList(name);
    }
    public void changeStDateTask(int index, int year, int month, int dayOfMonth){
        curList.changeStartDate(index ,year, month, dayOfMonth);
    }
    public void changeStDateTask(int index, int hour, int min){
        curList.changeStartDate(index, hour, min);
    }
    public void changeStDateTask(int index,int year, int month, int dayOfMonth, int hour, int min){
        curList.changeStartDate(index, year, month, dayOfMonth, hour, min);
    }
    public void changeEndDateTask(int index, int year, int month, int dayOfMonth) {
        curList.changeEndDate(index, year, month, dayOfMonth);
    }
    public void changeEndDateTask(int index, int hour, int min) {
        curList.changeEndDate(index, hour, min);
    }
    public void changeEndDateTask(int index, int year, int month, int dayOfMonth, int hour, int min) {
        curList.changeEndDate(index, year, month, dayOfMonth, hour, min);
    }

    public void changeTask(int index, String task) {
        curList.changeTask(index, task);
    }
    public void renameList(String oldName, String newName) {
        curList.renameSubList(oldName, newName);
    }
    public void renameTable(String oldName, String newName) {
        user.renameTable(oldName, newName);
    }

}

