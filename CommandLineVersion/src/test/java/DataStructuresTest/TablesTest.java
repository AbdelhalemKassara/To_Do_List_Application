package DataStructuresTest;

import Application.DataStructures.Tables;
import Application.DataStructures.ToDoList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TablesTest {
    @Test
    public void addListTest() {
        Tables table = new Tables();
        ToDoList t1 = new ToDoList("list 1");
        ToDoList t2 = new ToDoList("list 2");
        ToDoList t3 = new ToDoList("list 3");
        ToDoList t4 = new ToDoList("list 4");

        table.addList(t1);
        table.addList(t2);
        table.addList(t3);
        table.addList(t4);

        String output = "list 1, list 2, list 3, list 4, ";

        Assertions.assertEquals(output,table.getLists());
    }

    @Test
    public void removeListTest() {
        Tables table = new Tables();
        ToDoList t1 = new ToDoList("list 1");
        ToDoList t2 = new ToDoList("list 2");
        ToDoList t3 = new ToDoList("list 3");
        ToDoList t4 = new ToDoList("list 4");

        table.addList(t1);
        table.addList(t2);
        table.addList(t3);
        table.addList(t4);

        table.removeList("list 3");
        String output = "list 1, list 2, list 4, ";

        Assertions.assertEquals(output, table.getLists());
    }
}
