package by.grodno.pvt.site.webappsample.service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static by.grodno.pvt.site.webappsample.filter.LoggingFilter.LOGGER;

public class UserService {

    private static UserService sUserService;

    private UserService() {
    }

    public static UserService getService() {
        if (sUserService == null) {
            sUserService = new UserService();
        }
        return sUserService;
    }

    public List<User> getUsers() throws Exception {
        //Connection connection = DBUtils.
        List<User> users = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             Statement st = connection.createStatement();
             ResultSet set = st.executeQuery(SQL.SQL_GET_ALL_USERS);
        ) {
            //set.first();
            while (set.next()) {
                users.add(getUserByResultSet(set));
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }

        return users;
    }

    public void deleteUser(Integer number) throws SQLException {
        try (Connection connection = DBUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL.SQL_DELETE_USER);) {
            statement.setInt(1, number);
            statement.execute();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void addUser(User user, String dept) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.SQL_INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            /*
            "INSERT INTO public.testTable "
            + "(empl_name, empl_last_name, salary, birthdate,  male) VALUES (?,?,?,?,?)";
             */
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDouble(3, user.getSalary());
            statement.setTimestamp(4,
                    Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(user.getBirthdate())));
            statement.setBoolean(5,
                    user.isMale()
            );
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));

            }
            rs.close();
            doUpdateUserDept(user, dept);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public User getUserById(int id) throws Exception {
        //Connection connection = DBUtils.
        List<User> users = new ArrayList<>();
        ResultSet set = null;
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.SQL_GET_USER_BY_ID);
        ) {

            statement.setInt(1, id);
            set = statement.executeQuery();
            User user = null;
            if (set.next()) {
                user = getUserByResultSet(set);
            }

            return user;
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        if (set != null) {
            set.close();
        }
        return null;
    }

    private void doUpdateUserDept(User user, String deptName) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement stDeptByName = connection.prepareStatement(SQL.SQL_GET_DEPT_BY_NAME);
             PreparedStatement stInsertDept = connection.prepareStatement(SQL.SQL_INSERT_DEPT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stUpdateDept = connection.prepareStatement(SQL.SQL_UPDATE_USER_DEPT)
        ) {
            stDeptByName.setString(1, deptName);
            ResultSet dept = stDeptByName.executeQuery();
            int depId = 0;
            if (dept.next()) {
                depId = dept.getInt("id");
            }
            dept.close();
            if (depId == 0) {
                stInsertDept.setString(1, deptName);
                stInsertDept.executeUpdate();

                ResultSet rs = stInsertDept.getGeneratedKeys();
                if (rs.next()) {
                    depId = rs.getInt(1);
                }
                rs.close();
            }
            if (depId != 0) {
                stUpdateDept.setInt(1, depId);
                stUpdateDept.setInt(2, user.getId());
                stUpdateDept.execute();
            }
        }
    }

    public void updateUser(User user, String deptName) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.SQL_UPDATE)
        ) {/*
         "update public.testTable "+
            " set empl_name = ?, empl_last_name = ?, salary = ?, birthdate = ?,  male = ?"+
            " where id=?";
            */
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDouble(3, user.getSalary());
            statement.setTimestamp(4,
                    Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(user.getBirthdate())));
            statement.setBoolean(5, user.isMale());
            statement.setInt(6, user.getId());
            statement.execute();
            doUpdateUserDept(user, deptName);
        }
    }

    private User getUserByResultSet(ResultSet set) throws SQLException {
        User user = new User(set.getInt("id"),
                set.getString("empl_name"),
                set.getString("empl_last_name"),
                set.getTimestamp("birthdate"),
                set.getBoolean("male")
        );
        user.setSalary(set.getDouble("salary"));
        if (set.getInt("depid") != 0) {
            Dept dept = new Dept();
            dept.setName(set.getString("dept_name"));
            dept.setId(set.getInt("depid"));
            user.setDept(dept);
        }
        return user;
    }
}
