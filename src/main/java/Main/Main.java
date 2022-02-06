package Main;

import Application.CommandLine.UserInput.CommandsMap;
import Application.CommandLine.UserInput.Parser;
import Application.DataStructures.Table;
import Application.DataStructures.ToDoList;
import Application.DataStructures.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandsMap c1 = new CommandsMap(new User());
        Scanner in = new Scanner(System.in);

        c1.load();
        Table t1 = new Table("table 1");
        ToDoList l1 = new ToDoList("list 1");
        l1.addTask("list 1 task 1", 2021, 2,2,2,2);
        l1.addTask("list 1 task 2", 2022, 2,2,2,2);
        l1.addTask("list 1 task 3", 2023, 2,2,2,2);
        l1.addTask("list 1 task 4", 2024, 2,2,2,2);
        l1.addTask("list 1 task 5", 2019, 2,2,2,2);
        ToDoList l2 = new ToDoList("list 2");
        l2.addTask("list 2 task 1", 2021, 2,2,2,2);
        l2.addTask("list 2 task 2", 2022, 2,2,2,2);
        l2.addTask("list 2 task 3", 2023, 2,2,2,2);
        l2.addTask("list 2 task 4", 2024, 2,2,2,2);
        l2.addTask("list 2 task 5", 2019, 2,2,2,2);
        ToDoList l3 = new ToDoList("list 3");
        l3.addTask("list 3 task 1", 2021, 2,2,2,2);
        l3.addTask("list 3 task 2", 2022, 2,2,2,2);
        l3.addTask("list 3 task 3", 2023, 2,2,2,2);
        l3.addTask("list 3 task 4", 2024, 2,2,2,2);
        l3.addTask("list 3 task 5", 2019, 2,2,2,2);
        ToDoList l4 = new ToDoList("list 4");
        l4.addTask("list 4 task 1", 2021, 2,2,2,2);
        l4.addTask("list 4 task 2", 2022, 2,2,2,2);
        l4.addTask("list 4 task 3", 2023, 2,2,2,2);
        l4.addTask("list 4 task 4asdffsadafsdasfdsfdafsdasfdasfdasdfdfasdf", 2024, 2,2,2,2);
        l4.addTask("list 4 task 5", 2019, 2,2,2,2);
        t1.addColumn();
        t1.addColumn();
        t1.addList(0, l1);
        t1.addList(0, l2);
        t1.addList(1, l3);
        t1.addList(1, l4);

        for(String val : t1.printColumn(0,true)) {
            System.out.print(val);
        }
        System.out.println();
        System.out.println();

        System.out.println(t1.toString(true));
        System.out.println();
        System.out.println("Enter help to list all commands\n");
        while(true) {
            c1.operationsSearch(Parser.parseUserInput(in.nextLine()));
        }

    }
}
