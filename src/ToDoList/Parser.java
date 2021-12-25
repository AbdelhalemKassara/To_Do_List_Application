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
change list "cl"(enter the entire path from base so (something1/something2/something3))

completed

format method takes in an operation and returns the format of the input

 */

import java.util.ArrayList;

//probably will need a lot of try catch block
public class Parser {
    private String dir;
    private User user;
    private ToDoList curList;

    public Parser(User user) {
        this.user = user;
        curList = user;
        dir = "//";
    }

    public void parseUserInput(String input) {
        String operation = "";
        ArrayList<String> values = new ArrayList<>();

        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                operation = input.substring(0, i);
                input = input.substring(i);
                break;
            }
        }
        //if there is no operation return
        if(operation.equals("")) {
            return;
        }

        //removes the whitespace if there are inputs
        if(input.length() > 1) {
            input = input.substring(1);
        }
        System.out.println(operation);
        //check if there are commands that have no arguments here add a continue or return after if it meets a condition
        if(operationsWithoutValues(operation)) {
            return;
        }

        int stIndex = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '|') {
                values.add(input.substring(stIndex, i));
                stIndex = i+1;
            }
        }
        operationsWithValues(operation, values);
    }
    public boolean operationsWithoutValues(String operation) {
        switch(operation) {
            case "listNames":
                listNames();
                return true;
            case "printList":
                printList();
                return true;
            case "listTableNames":
                listTableNames();
                return true;
            case "printSubLists":
                printSubLists();
                return true;
            case "printSubListsFromRoot":
                printSubListsFromRoot();
                return true;
            case "printCurDir":
                printCurDir();
                return true;
        }
        return false;
    }

    public void operationsWithValues(String operation, ArrayList<String> values) {
        switch(operation) {
            case "addTask":
                try {
                    System.out.println(values);
                    if (values.size() == 11) {
                        curList.addTask(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)), Integer.parseInt(values.get(2)),
                                Integer.parseInt(values.get(3)), Integer.parseInt(values.get(4)), values.get(5), Integer.parseInt(values.get(6)),
                                Integer.parseInt(values.get(7)), Integer.parseInt(values.get(8)), Integer.parseInt(values.get(9)),
                                Integer.parseInt(values.get(10)));
                    } else if (values.size() == 6) {
                        curList.addTask(values.get(0), Integer.parseInt(values.get(1)), Integer.parseInt(values.get(2)), Integer.parseInt(values.get(3)),
                                Integer.parseInt(values.get(4)), Integer.parseInt(values.get(5)));
                        System.out.println("asdfasd");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input values for addtask operation");
                }
                return;
            case "addTable":
                try {
                    user.addTable(values.get(0));
                } catch (Exception e) {
                    System.out.println("please enter a name");
                }
                return;
            case "addList":
                try {
                    curList.addSubList(values.get(0));
                } catch (Exception e) {
                    System.out.println("please enter a name");
                }
                return;
            case "addListToTable":
                try {
                    user.addToTable(values.get(0), user.getListWithName(values.get(1)));
                } catch (Exception e) {
                    System.out.println("please enter a valid table name or path to list");
                }
                return;
           // case "moveList":

        }
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
    public String printSubLists() {
        return printSubListsFromCur(curList, "", "");
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

    public void changeList(String path) {
        ListItem temp = user.betterGetListWithName(path);
        curList = temp.getList();
        dir = temp.getName();

    }
    public void printCurDir() {
        System.out.println(dir);
    }

}

