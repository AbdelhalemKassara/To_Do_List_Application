package Application.CommandLine.UserInput;

import Application.DataStructures.ToDoList;
import Application.DataStructures.User;
import Application.CommandLine.Operations;

import java.io.FileNotFoundException;
import java.util.ArrayList;

//this class should use hashing in the future
public class CommandsMap extends Operations{
    public CommandsMap(User user) {
        super(user);
    }

    public void operationsSearch(ArrayList<String> values) {
        try {
            if(!operationsWithoutValues(values.get(0)) && !operationsWithValues(values)) {
                System.out.println("this command doesn't exist");
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public boolean operationsWithoutValues(String operation) throws FileNotFoundException {
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
            case "help":
                help();
                return true;
            case "save":
                save();
                return true;
            case "load":
                load();
                return true;
            case "exit":
                System.exit(0);
                return true;
            default:
                return false;
        }
    }

    public boolean operationsWithValues(ArrayList<String> values) {
        String operation = values.get(0);
        values.remove(0);
        switch(operation) {
            case "addTask":
                addTask(values);
                return true;
            case "addTable":
                addTable(values);
                return true;
            case "addList":
                addList(values);
                return true;
            case "addListToTable":
                addListToTable(values);
                return true;
            case "moveList":
                moveList(values);
                return true;
            case "moveTask":
                moveTask(values);
                return true;
            case "deleteTable":
                deleteTable(values);
                return true;
            case "deleteTask":
                deleteTask(values);
                return true;
            case "deleteList":
                deleteList(values);
                return true;
            case "cl":
                changeList(values);
                return true;
            case "changeStDateTask":
                changeStDateTask(values);
                return true;
            case "changeEndDateTask":
                changeEndDateTask(values);
                return true;
            case "changeTask":
                changeTask(values);
                return true;
            case "renameList":
                renameList(values);
                return true;
            case "renameTable":
                renameTable(values);
                return true;
            case "printTable":
                printTable(values);
                return true;
            case "removeListFromTable":
                removeListFromTable(values);
                return true;
            case "about":
                about(values);
                return true;
            default:
                return false;
        }
    }
}
