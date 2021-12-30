package Application.CommandLine.UserInput;

import Application.DataStructures.User;
import Application.CommandLine.Operations;

import java.util.ArrayList;

//this class should use hashing in the future
public class CommandsMap extends Operations{
    public CommandsMap(User user) {
        super(user);
    }

    public void operationsSearch(ArrayList<String> values) {
        try {
            operationsWithoutValues(values.get(0));
            operationsWithValues(values);
        } catch(Exception e) {
            System.out.println("no command");
        }
    }
    public void operationsWithoutValues(String operation) {
        switch(operation) {
            case "listNames":
                listNames();
                return;
            case "printList":
                printList();
                return;
            case "listTableNames":
                listTableNames();
                return;
            case "printSubLists":
                printSubLists();
                return;
            case "printSubListsFromRoot":
                printSubListsFromRoot();
                return;
            case "printCurDir":
                printCurDir();
                return;
            case "help":
                help();
                return;
            case "exit":
                System.exit(0);
                return;
        }
    }

    public void operationsWithValues(ArrayList<String> values) {
        String operation = values.get(0);
        values.remove(0);
        switch(operation) {
            case "addTask":
                addTask(values);
                return;
            case "addTable":
                addTable(values);
                return;
            case "addList":
                addList(values);
                return;
            case "addListToTable":
                addListToTable(values);
                return;
            case "moveList":
                moveList(values);
                return;
            case "moveTask":
                moveTask(values);
                return;
            case "deleteTable":
                deleteTable(values);
                return;
            case "deleteTask":
                deleteTask(values);
                return;
            case "deleteList":
                deleteList(values);
                return;
            case "cl":
                changeList(values);
                return;
            case "changeStDateTask":
                changeStDateTask(values);
                return;
            case "changeEndDateTask":
                changeEndDateTask(values);
                return;
            case "changeTask":
                changeTask(values);
                return;
            case "renameList":
                renameList(values);
                return;
            case "renameTable":
                renameTable(values);
                return;
            case "printTable":
                printTable(values);
                return;
            case "removeListFromTable":
                removeListFromTable(values);
                return;
            case "about":
                about(values);
                return;
        }
    }
}
