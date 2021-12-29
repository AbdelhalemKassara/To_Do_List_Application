package DataStructuresTest;

import Application.DataStructures.ToDoList;
import Application.DataStructures.User;
import Application.Operations.Operations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ToDoListTest {
    @Test
    @DisplayName("basic rename")
    public void renameSubListTest1() {
        ToDoList t1 = new ToDoList("root");
        t1.addSubList("list1");
        t1.addSubList("list2");
        t1.addSubList("list3");

        t1.renameSubList("list1", "newList1");
        String[] keys = t1.getSubListKeys();
        boolean contains = true;

        for(int i = 0; i < keys.length; i++) {
            if (!(keys[i].equals("newList1") || keys[i].equals("list2") || keys[i].equals("list3"))) {
                contains = false;
                break;
            }
        }
        Assertions.assertTrue(contains);

    }

    @Test
    @DisplayName("attempting to rename a list with it's existing name")
    public void renameSubListTest2() {
        ToDoList t1 = new ToDoList("root");
        t1.addSubList("list1");
        t1.addSubList("list2");
        t1.addSubList("list3");

        Assertions.assertFalse(t1.renameSubList("list1", "list1"));
    }

    @Test
    @DisplayName("attempting to rename using an existing key")
    public void renameSubListTest3() {
        ToDoList t1 = new ToDoList("root");
        t1.addSubList("list1");
        t1.addSubList("list2");
        t1.addSubList("list3");

        Assertions.assertFalse(t1.renameSubList("list1", "list2"));
    }

    @Test
    @DisplayName("attempting to rename a list that doesn't exist")
    public void renameSubListTest4() {
        ToDoList t1 = new ToDoList("root");
        t1.addSubList("list1");
        t1.addSubList("list2");
        t1.addSubList("list3");

        Assertions.assertFalse(t1.renameSubList("list0","anykey"));
    }

    @Test
    public void getListTest() {
        ToDoList t1 = new ToDoList("root");
        ToDoList t12 = new ToDoList("subList11");
        ToDoList t120 = new ToDoList("subList of subList11");
        ToDoList t13 = new ToDoList("subList12");
        ToDoList t14 = new ToDoList("subList13");

        t13.addSubList(t14.getListName(), t14);
        t12.addSubList(t13.getListName(), t13);
        t12.addSubList(t120.getListName(), t120);
        t1.addSubList(t12.getListName(), t12);

        ToDoList t22 = new ToDoList("subList21");
        ToDoList t23 = new ToDoList("subList23");
        ToDoList t24 = new ToDoList("subList24");

        t23.addSubList(t24.getListName(), t24);
        t22.addSubList(t23.getListName(), t23);

        t1.addSubList(t22.getListName(), t22);

        Assertions.assertEquals(t14,t1.getList("subList11/subList12/subList13"));
    }

    @Test
    public void getListTest1() {
        ToDoList t1 = new ToDoList("root");
        ToDoList t12 = new ToDoList("subList11");
        ToDoList t120 = new ToDoList("subList of subList11");
        ToDoList t13 = new ToDoList("subList12");
        ToDoList t14 = new ToDoList("subList13");

        t13.addSubList(t14.getListName(), t14);
        t12.addSubList(t13.getListName(), t13);
        t12.addSubList(t120.getListName(), t120);
        t1.addSubList(t12.getListName(), t12);

        ToDoList t22 = new ToDoList("subList21");
        ToDoList t23 = new ToDoList("subList23");
        ToDoList t24 = new ToDoList("subList24");

        t23.addSubList(t24.getListName(), t24);
        t22.addSubList(t23.getListName(), t23);

        t1.addSubList(t22.getListName(), t22);

        Assertions.assertEquals(t120, t1.getList("subList11/subList of subList11"));
    }

    @Test
    public void getListTest2() {
        ToDoList t1 = new ToDoList("root");
        ToDoList t12 = new ToDoList("subList11");
        ToDoList t120 = new ToDoList("subList of subList11");
        ToDoList t13 = new ToDoList("subList12");
        ToDoList t14 = new ToDoList("subList13");

        t13.addSubList(t14.getListName(), t14);
        t12.addSubList(t13.getListName(), t13);
        t12.addSubList(t120.getListName(), t120);
        t1.addSubList(t12.getListName(), t12);

        ToDoList t22 = new ToDoList("subList21");
        ToDoList t23 = new ToDoList("subList23");
        ToDoList t24 = new ToDoList("subList24");

        t23.addSubList(t24.getListName(), t24);
        t22.addSubList(t23.getListName(), t23);

        t1.addSubList(t22.getListName(), t22);

        Assertions.assertEquals(t24, t1.getList("subList21/subList23/subList24"));
    }
    @Test
    @DisplayName("test searching using '..'")
    public void getListTest3() {
        ToDoList t1 = new ToDoList("root");
        ToDoList t12 = new ToDoList("subList11");
        ToDoList t120 = new ToDoList("subList of subList11");
        ToDoList t13 = new ToDoList("subList12");
        ToDoList t14 = new ToDoList("subList13");

        t13.addSubList(t14.getListName(), t14);
        t12.addSubList(t13.getListName(), t13);
        t12.addSubList(t120.getListName(), t120);
        t1.addSubList(t12.getListName(), t12);

        ToDoList t22 = new ToDoList("subList21");
        ToDoList t23 = new ToDoList("subList23");
        ToDoList t24 = new ToDoList("subList24");

        t23.addSubList(t24.getListName(), t24);
        t22.addSubList(t23.getListName(), t23);

        t1.addSubList(t22.getListName(), t22);

        Assertions.assertEquals(t24, t1.getList("subList11/subList12/subList13/../../subList of subList11/../../subList21/subList23/subList24"));
    }

    @Test
    public void getAllSubListsTest() {
        ToDoList t1 = new ToDoList("root");
        ToDoList t12 = new ToDoList("subList11");
        ToDoList t120 = new ToDoList("subList of subList11");
        ToDoList t13 = new ToDoList("subList12");
        ToDoList t14 = new ToDoList("subList13");

        t13.addSubList(t14.getListName(), t14);
        t12.addSubList(t13.getListName(), t13);
        t12.addSubList(t120.getListName(), t120);
        t1.addSubList(t12.getListName(), t12);

        ToDoList t22 = new ToDoList("subList21");
        ToDoList t23 = new ToDoList("subList23");
        ToDoList t24 = new ToDoList("subList24");

        t23.addSubList(t24.getListName(), t24);
        t22.addSubList(t23.getListName(), t23);

        t1.addSubList(t22.getListName(), t22);
        String output = """
                //subList21
                //subList21/subList23
                //subList21/subList23/subList24
                //subList11
                //subList11/subList12
                //subList11/subList12/subList13
                //subList11/subList of subList11
                """;
        Assertions.assertEquals(output,t1.getAllSubLists());
    }
    @Test
    public void addSubListTest() {
        ToDoList t1 = new ToDoList("root");
        ToDoList t12 = new ToDoList("subList11");

        t1.addSubList(t12.getListName(), t12);
        Assertions.assertEquals(t1, t12.getParentList());
    }
}
