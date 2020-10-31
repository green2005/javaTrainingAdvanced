package by.grodno.pvt.site.webappsample.service;

public class SQL {

    public static final String SQL_GET_ALL_USERS = "select users.id, users.empl_name, users.dept_num, users.salary, \n" +
            "\t\t users.birthdate, users.empl_last_name, users.male, dept.dept_name, dept.id as \"depid\" " +
            " from public.testTable users\n" +
            " left join public.dept on dept.id = users.dept_num order by users.id ";

    public static final String SQL_GET_USER_BY_ID = "select users.id, users.empl_name, users.dept_num, users.salary, \n" +
            "\t\t users.birthdate, users.empl_last_name, users.male, dept.dept_name, dept.id as \"depid\" " +
            " from public.testTable users\n" +
            " left join public.dept on dept.id = users.dept_num where users.id = ?  order by users.id ";

    public static String SQL_DELETE_USER = "delete from public.testTable where id=?";
    public static final String SQL_INSERT = "INSERT INTO public.testTable "
            + "(empl_name, empl_last_name, salary, birthdate,  male) VALUES (?,?,?,?,?)";

    public static final String SQL_UPDATE = "update public.testTable " +
            " set empl_name = ?, empl_last_name = ?, salary = ?, birthdate = ?,  male = ?" +
            " where id=?";

    public static final String SQL_GET_DEPT_BY_NAME = "select d.id  from public.dept d  where lower(d.dept_name)=lower(?)";

    public static final String SQL_INSERT_DEPT = "insert into public.dept(dept_name) values(?) ";

    public static final String SQL_UPDATE_USER_DEPT = "update testtable\n" +
            "set dept_num = ?\n" +
            "where id=? ";


    public static final String SQL_GET_DEPS = "select d.id, d.dept_name, count(users.id) as \"qtyWorkers\", avg(users.salary) as \"avgSalary\" \n" +
            " from public.dept d\n" +
            " left join public.testtable users on users.dept_num = d.id\n" +
            " group by d.id, d.dept_name order by d.id ";

    public static final String SQL_DELETE_DEP = "delete from public.dept where id=?";

    public static final String SQL_GET_DEPT_BY_ID = "select d.id, d.dept_name " +
            " from public.dept d where d.id=?";

    public static final String SQL_UPDATE_DEPT = "update public.dept set dept_name=? where id = ?";

}
