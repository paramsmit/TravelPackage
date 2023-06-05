package activity;

import data.Activity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ActivityStorage;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActivityStorageTest {

    Activity activity;

    @BeforeEach
    public void setup(){
        activity = new Activity("id", "name", "description", 1, 5, "destID");
        ActivityStorage.save(activity);
    }

    @Test
    public void success(){
        Assertions.assertEquals(activity, ActivityStorage.get("id"));
    }

    @Test
    public void failure(){
        assertThrows(RuntimeException.class, () -> {
            ActivityStorage.get("id1");
        });
    }
}
