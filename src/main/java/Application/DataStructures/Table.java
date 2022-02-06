package Application.DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;


public class Table {
    private LinkedList<Column> columns;
    private String tableName;

    public Table(String tableName) {
        this.columns = new LinkedList<>();
        this.tableName = tableName;
    }
    public void addColumn() {
        columns.add(new Column());
    }
    public void removeColumn(int index) {
        if(columns.size() > index) {
            columns.remove(index);
        }
    }
    public void moveColumn(int columnIndex, int  newIndex) {
        if(columns.size() > columnIndex && columns.size() > newIndex && columnIndex != newIndex && columnIndex > 0 && newIndex > 0) {
            columns.add(newIndex, columns.remove(columnIndex));
        }
    }
    public void addList(int columnIndex, ToDoList list) {
        if(columnIndex >= 0 && columns.size() > columnIndex) {
            columns.get(columnIndex).addList(list);
        }
    }
    public void removeList(int columnIndex, String listName) {
        if(columnIndex > 0 && columns.size() > columnIndex) {
            columns.get(columnIndex).removeList(listName);
        }
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public LinkedList<String> printColumn(int index, boolean descSortOrder) {
        return columns.get(index).toStringArrList(descSortOrder);
    }

    private void addColumnHeader(StringBuilder str, ArrayList<LinkedList<String>> listsNamesCol) {

    }
    private void addColumns(StringBuilder str,ArrayList<LinkedList<String>> columnStr ) {

    }
    //genaric class that has both string builder and arraylist and will automatically detect which one is required

    //split this pile of shit into different methods for different parts
    public String toString(boolean descSortOrder) {
        StringBuilder str = new StringBuilder();
        str.append(tableName);
        str.append("\n\n");

        //gets each of the columns as an array of Strings of tasks
        ArrayList<LinkedList<String>> columnStr = new ArrayList<>();
        ArrayList<LinkedList<String>> listsNamesCol = new ArrayList<>();//this will store the lists names for each column

        for(Column col : columns) {
            LinkedList<String> colArrStr = col.toStringArrList(descSortOrder);
            columnStr.add(colArrStr);
            listsNamesCol.add(col.getListsArrStr());
        }

        //this will print the list names
        boolean allEmpty = false;

        while(!allEmpty) {
            allEmpty = true;
            //iterate or each column
            for (int i = 0; i < columns.size(); i++) {
                int maxSpacing = columns.get(i).getTotalSpace();

                //iterate over each list name within the column
                LinkedList<String> listNames = listsNamesCol.get(i);
                while(!listNames.isEmpty()) {
                    if(maxSpacing > listNames.getFirst().length()) {//while there is space in the current column row
                        String name = listNames.removeFirst();
                        str.append(name);
                        str.append(", ");
                        maxSpacing -= name.length()+2;
                        allEmpty = false;
                    } else {

                        break;
                    }
                }
                str.append("\b\b");//hides the last ", "
                maxSpacing += 6; //4 characters spacing between lists and 2 for the removed ", "
                addWhiteSpace(str, maxSpacing);//adds white space so that the next column lines up
            }
            str.append('\n');
       }

        //this will print the columns
        allEmpty = false;
        while(!allEmpty) {
            allEmpty = true;
            for(int i = 0; i < columnStr.size(); i++) {
                //if there is a task to print, print it, if there isn't then fill the space with white space
                if(!columnStr.get(i).isEmpty()) {
                    str.append(columnStr.get(i).removeFirst());
                    str.deleteCharAt(str.length()-1);//removes the last character from the task ---(temp fix)----
                    allEmpty = false;
                } else {
                    addWhiteSpace(str, columns.get(i).getTotalSpace());
                }
                str.append(" || ");
            }
            str.append("\b\b\b\b");
            str.append('\n');
        }

        return str.toString();
    }

    private void addWhiteSpace(StringBuilder str, int numOfWhiteSpace) {
        for(int i = 0; i < numOfWhiteSpace; i++) {
            str.append(' ');
        }
    }
    //method for printing the table (arrayList and String)

}
