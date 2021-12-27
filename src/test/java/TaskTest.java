import Application.DataStructures.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TaskTest {

    @Test
    public void checkComparable() {
        Task t1 = new Task("task1", 2020, 2,12,1,1);
        Task t2 = new Task("task2", 2021, 2,12,1,1);
        Assertions.assertNotEquals(null, t1);
        Assertions.assertNotEquals(null, t2);
        Assertions.assertEquals(-1, t1.compareTo(t2));
    }

    @Test
    @DisplayName("definitely not testSetTask()")//this changes the name that displayes when running the test (usually displays the method name)
    public void testSetTask() {
        Task t1 = new Task("task1", 2020, 2, 12, 1, 1);
        t1.setTask("task not 1");
        Assertions.assertEquals("task not 1", t1.getTask());
    }
    @Test
    @DisplayName("test change date with all paremters")
    public void testChangeDate() {
        Task t1 = new Task(2020, 2, 12, 1, 1, "task1", 2000, 12, 7, 5, 11);
        LocalDateTime d1 = LocalDateTime.of(2021, 3, 1, 12, 4);
        LocalDateTime d2 = LocalDateTime.of(2022, 9, 9, 9, 9);

        t1.changeEndDate(2021, 3, 1, 12, 4);
        t1.changeStartDate(2022, 9, 9, 9, 9);
        Assertions.assertEquals(0, d1.compareTo(t1.getEndDate()));
        Assertions.assertEquals(0, d2.compareTo(t1.getStartDate()));
    }

    @Test
    @DisplayName("test changing the date but only the hour and min")
    public void testChangeDate2() {

        Task t1 = new Task(2012,3,4,4,4,"asdfadsf",2000,2,5,5,5);
        LocalDateTime d1 = LocalDateTime.of(2012,3,4,1,1);
        LocalDateTime d2 = LocalDateTime.of(2000,2,5,1,1);
        t1.changeStartDate(1,1);
        t1.changeEndDate(1,1);

        Assertions.assertEquals(0,d1.compareTo(t1.getStartDate()));
        Assertions.assertEquals(0,d2.compareTo(t1.getEndDate()));
    }

    @Test
    @DisplayName("test changing the date but only the day month year")
    public void testChangeDate3() {
        Task t1 = new Task(2012,3,4,4,4,"asdfadsf",2000,2,5,5,5);
        LocalDateTime d1 = LocalDateTime.of(2011,2,3,4,4);
        LocalDateTime d2 = LocalDateTime.of(1999, 4,20,5,5);

        t1.changeEndDate(1999,4,20);
        t1.changeStartDate(2011,2,3);

        Assertions.assertEquals(0,d1.compareTo(t1.getStartDate()));
        Assertions.assertEquals(0,d2.compareTo(t1.getEndDate()));
    }

    @Test
    @DisplayName("test changing start date(hour,min) without initially setting it")
    public void testChangeDate4() {
        Task t1 = new Task("asdfadsf",2000,2,5,5,5);
        LocalDateTime d1 = LocalDateTime.now();
        d1 = LocalDateTime.of(d1.getYear(),d1.getMonth(),d1.getDayOfMonth(),11,3);

        t1.changeStartDate(11,3);

        Assertions.assertEquals(0,d1.compareTo(t1.getStartDate()));
    }
    @Test
    @DisplayName("test changing start date(month,day,year) without initially setting it")
    public void testChangeDate5() {
        Task t1 = new Task("asdfadsf",2000,2,5,5,5);
        LocalDateTime d1 = LocalDateTime.of(1999,1,7,0,0);

        t1.changeStartDate(1999,1,7);

        Assertions.assertEquals(0,d1.compareTo(t1.getStartDate()));
    }


}
