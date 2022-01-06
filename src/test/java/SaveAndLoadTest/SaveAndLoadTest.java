package SaveAndLoadTest;

import Application.DataStructures.Tables;
import Application.DataStructures.User;
import Application.SaveAndLoad.Save;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Application.SaveAndLoad.Load.loadUser;
import static Application.SaveAndLoad.Save.saveUser;

public class SaveAndLoadTest {

    @Test
    public void loadUserTest() throws FileNotFoundException {
        User l1 = new User();

        l1.addSubList("2");
        l1.addSubList("2.1");

        l1.getList("2").addSubList("3");
        l1.getList("2").addSubList("3.1");

        l1.getList("2/3").addSubList("4");

        l1.addTable("table1");

        l1.addToTable("table1",l1.getList("2.1"));
        l1.addToTable("table1", l1.getList("2/3.1"));
        l1.addToTable("table1", l1.getList("2/3/4"));

        l1.addTable("table2");
        l1.addToTable("table2", l1.getList("2.1"));
        l1.addToTable("table2", l1.getList("2/3/4"));
        l1.addToTable("table2", l1.getList("2/3.1"));

        l1.addTask("user task", 2020,1,1,1,1);
        l1.getList("2").addTask("2 task", 2020,1,1,1,1);
        l1.getList("2/3").addTask("3 task", 2020,1,1,1,1);


        User user = loadUser("testLoading");

        Assertions.assertEquals(l1.getListName(), user.getListName());
        Assertions.assertEquals(l1.getTask(0).getTask(), user.getTask(0).getTask());
        Assertions.assertEquals(l1.getList("2/3/4").getListName(), user.getList("2/3/4").getListName());
    }

    @Test
    public void saveUserTest() throws FileNotFoundException {
        User l1 = new User();
        l1.setListName("testSaving");
        l1.addSubList("2");
        l1.addSubList("2.1");

        l1.getList("2").addSubList("3");
        l1.getList("2").addSubList("3.1");

        l1.getList("2/3").addSubList("4");

        l1.addTable("table1");

        l1.addToTable("table1",l1.getList("2.1"));
        l1.addToTable("table1", l1.getList("2/3.1"));
        l1.addToTable("table1", l1.getList("2/3/4"));

        l1.addTable("table2");
        l1.addToTable("table2", l1.getList("2.1"));
        l1.addToTable("table2", l1.getList("2/3/4"));
        l1.addToTable("table2", l1.getList("2/3.1"));

        l1.addTask("user task", 2020,1,1,1,1);
        l1.getList("2").addTask("2 task", 2020,1,1,1,1);
        l1.getList("2/3").addTask("3 task", 2020,1,1,1,1);

        saveUser(l1);

        File userFile = new File("UserFiles/"+ l1.getListName() + ".user");
        Scanner in = new Scanner(userFile);

        //expected output
        ArrayList<String> values = new ArrayList<>();
        values.add("(TablesList){{table2|2.1/|2/3/4/|2/3.1/}|{table1|2.1/|2/3.1/|2/3/4/}}");
        values.add("(User){{28|27}{{(Task){{28|27}{(Date){||||}|user task|(Date){2020|1|1|1|1}}}}|testSaving}}");
        values.add("(){{28|27}{{(Task){{28|27}{(Date){||||}|2 task|(Date){2020|1|1|1|1}}}}|2}}");
        values.add("(2/){{28|27}{{(Task){{28|27}{(Date){||||}|3 task|(Date){2020|1|1|1|1}}}}|3}}");
        values.add("(2/3/){{28|27}{{}|4}}");
        values.add("(2/){{28|27}{{}|3.1}}");
        values.add("(){{28|27}{{}|2.1}}");

        for(int i = 0; i < values.size(); i++) {
            Assertions.assertEquals(values.get(i), in.nextLine());
            Assertions.assertFalse((values.size()-1 == i) && in.hasNext());
        }
    }
}
