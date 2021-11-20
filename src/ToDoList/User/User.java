package ToDoList.User;

import ToDoList.ToDoList;
import ToDoList.Task;

import java.util.HashMap;
import java.util.LinkedList;

public class User extends ToDoList{

    public User(){
    }
    public User(LinkedList<Task> taskList){
        super(taskList);

    }
    public User(HashMap<String, ToDoList> subList){
        super(subList);
    }
    public User(HashMap<String, ToDoList> subList, LinkedList<Task> taskList){
            super(subList, taskList);
    }
}
