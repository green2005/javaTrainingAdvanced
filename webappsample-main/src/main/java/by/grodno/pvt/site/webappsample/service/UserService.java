package by.grodno.pvt.site.webappsample.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<User>();

    private static UserService userService;

    private UserService() {
        users.add(addUser(1, "Max", "Naumovich", fromString("1985-05-05"), true));
        users.add(addUser(2, "Sasha", "Alexandrov", fromString("1976-05-05"), true));
        users.add(addUser(3, "Masha", "Popova", fromString("2001-05-05"), false));
        users.add(addUser(4, "Siarozha", "Sergienko", fromString("1995-05-05"), true));
    }

    public static UserService getService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getUsers() {
        return users;
    }

    ;

    public void deleteUser(Integer number) {
        users.remove(number.intValue());
    }

    ;

    public void addUser(User user) {
        user.setId(users.size());
        users.add(user);
    }

    public void updateUser(User user, int userNo) {
        if ((userNo >= 0) && (userNo < users.size())) {
            users.set(userNo, user);
        }
    }

    public User getUserByNom(Integer number) {
        if ((number.intValue() >= 0) && (number < users.size())) {
            return users.get(number.intValue());
        } else {
            return null;
        }
    }

    private User addUser(int i, String string, String string2, Date fromString, boolean male) {
        return new User(i, string, string2, fromString, male);
    }

    private Date fromString(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
