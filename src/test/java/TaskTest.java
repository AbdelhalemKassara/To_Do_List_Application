import Application.DataStructures.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TaskTest {

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
}
