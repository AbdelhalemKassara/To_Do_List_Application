package Application.Operations;

import Application.DataStructures.ToDoList;
import Application.DataStructures.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
 */
public class Operations {
    private String dir;
    private User user;
    private ToDoList curList;
    private HashMap<String, String> helpMessages = new HashMap<>();


    public Operations(User user) {
        this.user = user;
        curList = user;
        dir = "//";
        helpMessages.put("addTask", "addTask: adds a new task to the current list. (task|year|month|dayOfMonth|hour|minute or stYear|stMonth|stDayOfMonth|stHour|stMin|task|year|month|dayOfMonth|hour|minute)");
        helpMessages.put("addTable", "addTable: adds a new table. (nameOfNewTable)");
        helpMessages.put("addList", "addList: add a new list. (nameOfNewList)");
        helpMessages.put("addListToTable", "addListToTable: adds a desired list to a desired table. (nameOfTable|path)");
        helpMessages.put("moveList", "moveList: moves a list to another list. (pathToMove|PathNewLoc)");
        helpMessages.put("moveTask", "moveTask: moves a task from the current list to a desired list. (index|pathToNewList)");
        helpMessages.put("deleteTable", "deleteTable: deletes a table. (tableName)");
        helpMessages.put("deleteTask", "deleteTask: deletes a task from the current list. (taskNumber)");
        helpMessages.put("deleteList", "deleteList: deletes a list from the current list. (listName)");
        helpMessages.put("changeList", "changeList: changes the current list to the desired list. (path)");
        helpMessages.put("changeStDateTask", "changeStDateTask: changes the start date of a task. (index|year|month|dayOfMonth| or index|hour|min| or index|year|month|dayOfMonth|hour|min)");
        helpMessages.put("changeTask", "changeTask: changes the task of a task. (index|task|)");
        helpMessages.put("changeEndDateTask","changeEndDateTask: changes the end date of a task. (index|year|month|dayOfMonth| or index|hour|min| or index|year|month|dayOfMonth|hour|min)");
        helpMessages.put("renameList", "renameList: changes the name of a sublist from the current list. (oldName|newName|)");
        helpMessages.put("renameTable", "renameTable: renames a table. (oldName|newName|)");
        helpMessages.put("listNames", "listNames: prints the names of the sub lists in the current list. ()");
        helpMessages.put("printList", "printList: prints the contents of the current list, sub-lists and tasks. ()");
        helpMessages.put("listTableNames", "listTableNames: prints all the table names for the current user. ()");
        helpMessages.put("printTable", "printTable: prints the desired table. (tableName)");
        helpMessages.put("printSubLists", "printSubLists: prints the names of all the sub lists from the current list. ()");
        helpMessages.put("printSubListsFromRoot", "printSubListsFromRoot: prints the names of all the sub lists from the root list. ()");
        helpMessages.put("printCurDir", "printCurDir: prints the current directory. ()");
    }
    public void help() {
        for(Map.Entry<String, String> i : helpMessages.entrySet()) {
            System.out.println(i.getValue());
        }
    }

    public void addTask(ArrayList<String> values) {
        try {
            if (values.size() == 11) {
                curList.addTask(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)), Integer.parseInt(values.get(2)),
                        Integer.parseInt(values.get(3)), Integer.parseInt(values.get(4)), values.get(5), Integer.parseInt(values.get(6)),
                        Integer.parseInt(values.get(7)), Integer.parseInt(values.get(8)), Integer.parseInt(values.get(9)),
                        Integer.parseInt(values.get(10)));
            } else if (values.size() == 6) {
                curList.addTask(values.get(0), Integer.parseInt(values.get(1)), Integer.parseInt(values.get(2)), Integer.parseInt(values.get(3)),
                        Integer.parseInt(values.get(4)), Integer.parseInt(values.get(5)));
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("Invalid input values for addtask operation, format: task|year|month|dayOfMonth|hour|minute or stYear|stMonth|stDayOfMonth|stHour|stMin|task|year|month|dayOfMonth|hour|minute");
        }
    }
    public void addTable(ArrayList<String> values) {
        try {
            user.addTable(values.get(0));
        } catch (Exception e) {
            System.out.println("please enter a name, format: nameOfNewTable");
        }
    }
    public void addList(ArrayList<String> values) {
        try {
            curList.addSubList(values.get(0));
        } catch (Exception e) {
            System.out.println("please enter a name, format: nameOfNewList");
        }
    }
    public void addListToTable(ArrayList<String> values) {
        try {
            user.addToTable(values.get(0), user.getList(values.get(1)));
        } catch (Exception e) {
            System.out.println("please enter a valid table name or path to list, format: nameOfTable|path");
        }
    }
    public void moveList(ArrayList<String> values) {
        try {
            ToDoList temp = user.getList(values.get(0));
            user.getList(values.get(1)).addSubList(temp.getListName(), temp);
            temp.removeSubList(values.get(0));
        } catch (Exception e) {
            System.out.println("invalid inputs format: pathToMove|PathNewLoc");
        }
    }
    public void moveTask(ArrayList<String> values) {
        try {//this will need to use an operation similar to changeList operation
            user.getList(values.get(1)).addTask(curList.getTask(Integer.parseInt(values.get(0))));
            curList.removeTask(Integer.parseInt(values.get(0)));
        } catch(Exception e) {
            System.out.println("invalid inputs for moveTask format: index|pathToNewList");
        }
    }
    public void deleteTable(ArrayList<String> values) {
        try {
            user.deleteTable(values.get(0));
        } catch (Exception e) {
            System.out.println("can't delete table, format: tableName");
        }
    }
    public void deleteTask(ArrayList<String> values) {
        try {
            curList.removeTask(Integer.parseInt(values.get(0)));
        } catch (Exception e) {
            System.out.println("can't delete task, format: taskNumber");
        }
    }
    public void deleteList(ArrayList<String> values) {
        try {
            curList.removeSubList(values.get(0));
        }  catch (Exception e) {
            System.out.println("can't delete list, format: listName");
        }
    }
    public void changeList(ArrayList<String> values) {
        try {
            if(values.get(0).charAt(0) == '/') {
                curList = user.getList(values.get(0).substring(1));
            } else {
                curList = curList.getList(values.get(0));
            }
        } catch(Exception e) {
            System.out.println(e + "\n\n invalid list, format: path");
        }
    }
    public void changeStDateTask(ArrayList<String> values) {
        try {
            if (values.size() == 4) {
                curList.changeStartDate(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)),
                        Integer.parseInt(values.get(2)), Integer.parseInt(values.get(3)));
            } else if (values.size() == 3) {
                curList.changeStartDate(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)),
                        Integer.parseInt(values.get(2)));
            } else if (values.size() == 6) {
                curList.changeStartDate(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)),
                        Integer.parseInt(values.get(2)), Integer.parseInt(values.get(3)),
                        Integer.parseInt(values.get(4)), Integer.parseInt(values.get(5)));
            }
        } catch(Exception e) {
            System.out.println(e + "\n\nformat: index|year|month|dayOfMonth| or index|hour|min| or index|year|month|dayOfMonth|hour|min");
        }
    }
    public void changeTask(ArrayList<String> values) {
        try {
            curList.changeTask(Integer.parseInt(values.get(0)), values.get(1));
        } catch(Exception e) {
            System.out.println(e + "\n\n format: index|task|");
        }
    }
    public void changeEndDateTask(ArrayList<String> values) {
        try {
            if (values.size() == 4) {
                curList.changeEndDate(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)),
                        Integer.parseInt(values.get(2)), Integer.parseInt(values.get(3)));
            } else if (values.size() == 3) {
                curList.changeEndDate(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)),
                        Integer.parseInt(values.get(2)));
            } else if (values.size() == 6) {
                curList.changeEndDate(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)),
                        Integer.parseInt(values.get(2)), Integer.parseInt(values.get(3)),
                        Integer.parseInt(values.get(4)), Integer.parseInt(values.get(5)));
            }
        } catch(Exception e) {
            System.out.println(e + "\n\nformat: index|year|month|dayOfMonth| or index|hour|min| or index|year|month|dayOfMonth|hour|min");
        }
    }
    public void renameList(ArrayList<String> values) {
        try {
            curList.renameSubList(values.get(0), values.get(1));
        } catch(Exception e) {
            System.out.println(e + "\n\nformat: oldName|newName|");
        }
    }
    public void renameTable(ArrayList<String> values) {
        try {
            user.renameTable(values.get(0), values.get(1));
        } catch(Exception e) {
            System.out.println(e + "\n\nformat: oldName|newName|");
        }
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
    public void printTable(ArrayList<String> values) {
        try {
            System.out.println(user.getTable(values.get(0)));
        } catch(Exception e) {
            System.out.println(e + "\n\n invalid name for table, format: tableName");
        }
    }

    public void printSubLists() {
        System.out.println(curList.getAllSubLists());
    }
    public void printSubListsFromRoot() {
        System.out.println(user.getAllSubLists());
    }
    public void printCurDir() {
        System.out.println(dir);
    }


}
