package Random;

import org.example.App;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTestRandom {

    @Test
    public void testAppTest() {
        App app = new App("John Doe", 1234);
        assertEquals(app.getName(), "John Doe");
        assertEquals(app.getId(), 1234);
    }
}
