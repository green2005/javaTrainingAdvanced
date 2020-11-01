package by.grodno.pvt.site.webappsample.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.grodno.pvt.site.webappsample.service.SQL.*;

public class DeptsService {
    private static DeptsService sService;

    private DeptsService() {
    }

    public static DeptsService getService() {
        if (sService == null) {
            sService = new DeptsService();
        }
        return sService;
    }

    public void deleteDep(int depId) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DEP);
        ) {
            statement.setInt(1, depId);
            statement.execute();
        }
    }

    public int addDept(Dept dept) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement stDeptByName = connection.prepareStatement(SQL.SQL_GET_DEPT_BY_NAME);
             PreparedStatement stInsertDept = connection.prepareStatement(SQL.SQL_INSERT_DEPT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stUpdateDept = connection.prepareStatement(SQL.SQL_UPDATE_USER_DEPT)
        ) {
            stDeptByName.setString(1, dept.getName());
            ResultSet depset = stDeptByName.executeQuery();
            int depId = 0;
            if (depset.next()) {
                depId = depset.getInt("id");
            }
            depset.close();
            if (depId == 0) {
                stInsertDept.setString(1, dept.getName());
                stInsertDept.executeUpdate();

                try (ResultSet rs = stInsertDept.getGeneratedKeys();) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        }
        return 0;
    }

    public void updateDept(String deptName, int deptId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DEPT);
        ) {
            statement.setString(1, deptName);
            statement.setInt(2, deptId);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Dept getDeptById(int id) throws Exception {
        Dept dept = null;
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_DEPT_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                dept = new Dept();
                dept.setId(set.getInt("id"));
                dept.setName(set.getString("dept_name"));
            }
            set.close();
        }
        return dept;
    }

    public List<Dept> getDeptsList() throws Exception {
        List<Dept> depts = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.SQL_GET_DEPS);
        ) {
            //select d.id, d.dept_name, count(users.id) as \"qtyWorkers\", avg(users.salary) as \"avgSalary\" \n" +
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Dept dept = new Dept();
                dept.setId(set.getInt("id"));
                dept.setName(set.getString("dept_name"));
                dept.setAvgSalary(set.getDouble("avgSalary"));
                dept.setWorkerCount(set.getInt("qtyWorkers"));
                depts.add(dept);
            }
        }
        return depts;
    }


}
