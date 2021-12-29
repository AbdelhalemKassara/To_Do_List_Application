package Application.Operations;

import Application.DataStructures.ToDoList;
import Application.DataStructures.User;

import java.util.ArrayList;
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
public class Operations {
    private String dir;
    private User user;
    private ToDoList curList;

    public Operations(User user) {
        this.user = user;
        curList = user;
        dir = "//";
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
            System.out.println("Invalid input values for addtask operation");
        }
    }
    public void addTable(ArrayList<String> values) {
        try {
            user.addTable(values.get(0));
        } catch (Exception e) {
            System.out.println("please enter a name");
        }
    }
    public void addList(ArrayList<String> values) {
        try {
            curList.addSubList(values.get(0));
        } catch (Exception e) {
            System.out.println("please enter a name");
        }
    }
    public void addListToTable(ArrayList<String> values) {
        try {
            user.addToTable(values.get(0), user.getList(values.get(1)));
        } catch (Exception e) {
            System.out.println("please enter a valid table name or path to list");
        }
    }
    public void moveList(ArrayList<String> values) {
        try {
            ToDoList temp = user.getList(values.get(0));
            user.getList(values.get(2)).addSubList(values.get(1), temp.getList(values.get(1)));
            temp.removeSubList(values.get(1));
        } catch (Exception e) {
            System.out.println("invalid inputs format: pathToMove|nameOfList|PathNewLoc");
        }
    }
    public void moveTask(ArrayList<String> values) {
        try {
            user.getList(values.get(2)).addTask(user.getList(values.get(0)).getTask(Integer.parseInt(values.get(1))));
            user.getList(values.get(0)).removeTask(Integer.parseInt(values.get(1)));
        } catch(Exception e) {
            System.out.println("invalid inputs for moveTask format: pathToList|index|pathToNewList");
        }
    }
    public void deleteTable(ArrayList<String> values) {
        try {
            user.deleteTable(values.get(0));
        } catch (Exception e) {
            System.out.println("can't delete table");
        }
    }
    public void deleteTask(ArrayList<String> values) {
        try {
            curList.removeTask(Integer.parseInt(values.get(0)));
        } catch (Exception e) {
            System.out.println("can't delete task");
        }
    }
    public void deleteList(ArrayList<String> values) {
        try {
            curList.removeSubList(values.get(0));
        }  catch (Exception e) {
            System.out.println("can't delete list");
        }
    }
    public void changeList(ArrayList<String> values) {
        try {
            ToDoList temp = user.getList(values.get(0));
            curList = temp;
            dir = curList.getListName();
        } catch(Exception e) {
            System.out.println(e + "\n\n invalid list");
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
            System.out.println(e + "\n\n invalid name for table");
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

    //delete this method, this was just for testing the method above
    public void tewt(ToDoList list) {
        user.addSubList("temp",list);
    }
}
