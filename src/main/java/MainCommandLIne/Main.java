package MainCommandLIne;

import Application.CommandLine.UserInput.CommandsMap;
import Application.CommandLine.UserInput.Parser;
import Application.DataStructures.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandsMap c1 = new CommandsMap(new User());
        Scanner in = new Scanner(System.in);

        while(true) {
            c1.operationsSearch(Parser.parseUserInput(in.nextLine()));
        }

    }
}
