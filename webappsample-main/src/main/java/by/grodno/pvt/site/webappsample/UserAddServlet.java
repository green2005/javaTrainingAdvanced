package by.grodno.pvt.site.webappsample;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grodno.pvt.site.webappsample.service.User;
import by.grodno.pvt.site.webappsample.service.UserService;

public class UserAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String s = req.getParameter("bday");
            User usr = new User(
                    null,
                    req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    new SimpleDateFormat("yyy-MM-dd").parse(s),
                    "true".equals(req.getParameter("male"))
            );
            usr.setSalary(0.0);
            String salary = req.getParameter("salary");
            if ((salary != null) && (!"".equals(salary))) {
                try {
                    usr.setSalary(Double.parseDouble(salary));
                } catch (NumberFormatException e) {
                }
            }

            String dept = req.getParameter("dept");
            UserService.getService().addUser(usr, dept);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/webappsample/jstl1");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/userEdit.jsp").forward(req, resp);

        //resp.sendRedirect("/webappsample/userEdit.jsp");
    }
}
