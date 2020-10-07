
package by.epamTraining.testMultiModule.repository;

import by.epamTraining.testMultiModule.repository.User;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();

        User u = new User();
        u.setName("Nick");
        u.setAge(20);
        users.add(u);

        u = new User();
        u.setName("Mike");
        u.setAge(25);
        users.add(u);

        return users;
    }
}
