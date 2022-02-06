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

}
