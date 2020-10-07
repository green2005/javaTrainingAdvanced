package by.epamTraining.testMultiModule.dataController;

import by.epamTraining.testMultiModule.repository.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataControllerTest {
    private DataController dataController;

    @Before
    public void init() {
        dataController = new DataController();
    }

    @Test
    public void getUsers() {
        List<User> users = dataController.getUsers();
        assertTrue(users.stream().anyMatch(user->"Mike".equals(user.getName())));
        assertTrue(users.stream().anyMatch(user->"Nick".equals(user.getName())));
    }
}