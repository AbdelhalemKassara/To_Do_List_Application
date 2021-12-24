package ToDoList;
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

completed


change the date of a task
change the "task" of a task
change the name of a list
change the name of a table
 */

//probably will need a lot of try catch block
public class Parser {
    private String dir;
    private User user = new User();
    private ToDoList curList;

    public void userInput(String str) {
    }
    public void createList(String name) {
        curList.addSubList(name);
    }
    public void addTask(int stYear, int stMonth, int stDayOfMonth, int stHour, int stMinute, String task,
                        int year, int month, int dayOfMonth, int hour, int minute) {
        curList.addTask(stYear, stMonth, stDayOfMonth, stHour, stMinute, task, year, month, dayOfMonth, hour, minute);
    }
    public void addTask(String task, int year, int month, int dayOfMonth, int hour, int minute) {
        curList.addTask(task, year, month, dayOfMonth, hour, minute);
    }
    public void addTable(String name) {
        user.addTable(name);
    }
    public void addListToTable(String nameOfTable, String path) {
        user.addToTable(nameOfTable,user.getListWithName(path));
    }
    public void moveList(String pathMove,String nameOfList, String pathNewLoc) {
        ToDoList temp = user.getList(pathMove);
        user.getList(pathNewLoc).addSubList(nameOfList, temp.getList(nameOfList));
        temp.removeSubList(nameOfList);
    }
    public void moveTask(String pathToList, int index, String pathToNewList) {
        user.getList(pathToNewList).addTask(user.getList(pathToList).getTask(index));
        user.getList(pathToList).removeTask(index);
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
    public void printTable(String name) {
        System.out.println(user.getTable(name));
    }

    //not the best way to do it but I want to see if it will be noticibly slow
    public String printSubListsFromCur(ToDoList temp, String path, String cur) {
        StringBuilder str = new StringBuilder();
        String[] subLists = temp.getSubListKeys();
        path += '/' + cur;

        for(int i = 0; i < subLists.length; i++) {
            str.append(path);
            str.append('/');
            str.append(subLists[i]);
            str.append('\n');
            str.append(printSubListsFromCur(temp.getList(subLists[i]), path, subLists[i]));
        }
        return str.toString();
    }
    public String printSubListsBase(ToDoList temp) {
        return printSubListsFromCur(temp, "", "");
    }
    public String printSubListsFromRoot() {
        return printSubListsFromCur(user,"","");
    }

    //delete this method, this was just for testing the method above
    public void tewt(ToDoList list) {
        user.addSubList("temp",list);
    }

    public void deleteTable(String name) {
        user.deleteTable(name);
    }
    public void deleteTask(int index) {
        curList.removeTask(index);
    }
    public void deleteList(String name) {
            curList.removeSubList(name);
    }
    public void changeStDateTask(int index, int year, int month, int dayOfMonth){
        curList.changeStartDate(index ,year, month, dayOfMonth);
    }
    public void changeStDateTask(int index, int hour, int min){
        curList.changeStartDate(index, hour, min);
    }
    public void changeStDateTask(int index,int year, int month, int dayOfMonth, int hour, int min){
        curList.changeStartDate(index, year, month, dayOfMonth, hour, min);
    }
    public void changeEndDateTask(int index, int year, int month, int dayOfMonth) {
        curList.changeEndDate(index, year, month, dayOfMonth);
    }
    public void changeEndDateTask(int index, int hour, int min) {
        curList.changeEndDate(index, hour, min);
    }
    public void changeEndDateTask(int index, int year, int month, int dayOfMonth, int hour, int min) {
        curList.changeEndDate(index, year, month, dayOfMonth, hour, min);
    }
    public void changeDateTask(){

    }
}

