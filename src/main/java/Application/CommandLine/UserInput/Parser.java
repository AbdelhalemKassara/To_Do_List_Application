package Application.CommandLine.UserInput;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> parseUserInput(String input) {
        ArrayList<String> values = new ArrayList<>();

        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                values.add(input.substring(0, i));
                input = input.substring(i);
                break;
            } else if(i == input.length()-1) {
                values.add(input);
            }
        }

        //removes the whitespace if there are inputs
        if(input.length() > 1) {
            input = input.substring(1);
        }


        int stIndex = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '|') {
                values.add(input.substring(stIndex, i));
                stIndex = i+1;
            } else if(i == input.length()-1) {
                values.add(input.substring(stIndex));
            }
        }
        return values;
    }

}

