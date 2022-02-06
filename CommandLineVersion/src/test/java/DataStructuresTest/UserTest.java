package DataStructuresTest;

import Application.DataStructures.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void addTableTest() {
        User user = new User();
        user.addTable("table 1");
        user.addTable("table 2");
        user.addTable("table 3");

        String[] names = user.getTableNames();

        boolean valid = true;
        for(int i = 0; i < names.length; i++) {
            if(!(names[i].equals("table 1") || names[i].equals("table 2") || names[i].equals("table 3"))) {
                valid = false;
            }
        }

        Assertions.assertTrue(valid);
    }

    @Test
    @DisplayName("delete a table with a name that exists")
    public void deleteTableTest() {
        User user = new User();
        user.addTable("table 1");
        user.addTable("table 2");
        user.addTable("table 3");

        user.deleteTable("any name");

        String[] names = user.getTableNames();

        boolean valid = true;

        for(int i = 0; i < names.length; i++) {
            if (!names[i].equals("table 1") && !names[i].equals("table 3") && !names[i].equals("table 2")) {
                valid = false;
            }
        }

        Assertions.assertTrue(valid);
    }

    @Test
    @DisplayName("delete a table with a name that doesn't exist")
    public void deleteTableTest1() {

    }

    @Test
    @DisplayName("rename an existing table with a new name")
    public void renameTableTest() {
        User user = new User();
        user.addTable("table 1");
        user.addTable("table 2");
        user.addTable("table 3");

        user.renameTable("table 3", "table 4");

        boolean valid = true;
        String[] names = user.getTableNames();

        for(int i = 0; i < names.length; i++) {
            if(!names[i].equals("table 1") && !names[i].equals("table 2") && !names[i].equals("table 4") || names[i].equals("table 3")) {
                valid = false;
            }
        }

        Assertions.assertTrue(valid);
    }

    @Test
    @DisplayName("rename a table with the same name")
    public void renameTableTest1() {
        User user = new User();
        user.addTable("table 1");
        user.addTable("table 2");
        user.addTable("table 3");

        user.renameTable("table 3", "table 3");

        boolean valid = true;
        String[] names = user.getTableNames();

        for(int i = 0; i < names.length; i++) {
            if (!names[i].equals("table 1") && !names[i].equals("table 2") && !names[i].equals("table 3")) {
                valid = false;
            }
        }

        Assertions.assertTrue(valid);
    }

    @Test
    @DisplayName("rename a table with a name that already exists")
    public void renameTableTest2() {
        User user = new User();
        user.addTable("table 1");
        user.addTable("table 2");
        user.addTable("table 3");

        user.renameTable("table 3", "table 2");

        boolean valid = true;
        String[] names = user.getTableNames();

        for(int i = 0; i < names.length; i++) {
            if (!names[i].equals("table 1") && !names[i].equals("table 2") && !names[i].equals("table 3")) {
                valid = false;
            }
        }
    }

    @Test
    @DisplayName("rename a list that doesn't exist")
    public void renameTableTest3() {
        User user = new User();
        user.addTable("table 1");
        user.addTable("table 2");
        user.addTable("table 3");

        user.renameTable("table 5", "any name");

        boolean valid = true;
        String[] names = user.getTableNames();

        for(int i = 0; i < names.length; i++) {
            if (!names[i].equals("table 1") && !names[i].equals("table 2") && !names[i].equals("table 3")) {
                valid = false;
            }
        }
    }
}
