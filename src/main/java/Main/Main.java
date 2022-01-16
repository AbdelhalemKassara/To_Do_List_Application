package Main;

import Application.CommandLine.UserInput.CommandsMap;
import Application.CommandLine.UserInput.Parser;
import Application.DataStructures.Column;
import Application.DataStructures.ToDoList;
import Application.DataStructures.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandsMap c1 = new CommandsMap(new User());
        Scanner in = new Scanner(System.in);

        c1.load();

        System.out.println("Enter help to list all commands\n");
        while(true) {
            c1.operationsSearch(Parser.parseUserInput(in.nextLine()));
        }

    }
}
