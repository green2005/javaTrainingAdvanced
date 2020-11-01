package by.grodno.pvt.site.webappsample;

import by.grodno.pvt.site.webappsample.service.User;
import by.grodno.pvt.site.webappsample.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserEditServlet extends HttpServlet {
    private static final String USER_NO_PARAM = "number";
    private static final String USER_ATTRIBUTE = "user";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userNo = (String) req.getSession().getAttribute(USER_NO_PARAM);
        req.getSession().removeAttribute(USER_NO_PARAM);
        int uId = Integer.parseInt(userNo);
        User usr = null;
        try {
            usr = UserService.getService().getUserById(uId);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (usr != null) {
            usr.setFirstName(req.getParameter("firstName"));
            usr.setLastName(req.getParameter("lastName"));
            usr.setMale("true".equals(req.getParameter("male")));
            usr.setSalary(0.0);
            String salary = req.getParameter("salary");
            if ((salary != null) && (!"".equals(salary))) {
                try {
                    usr.setSalary(Double.parseDouble(salary));
                } catch (NumberFormatException e) {
                }
            }
            String dept = req.getParameter("dept");
            try {
                String sbday = req.getParameter("bday");
                usr.setBirthdate(new SimpleDateFormat("yyy-MM-dd").parse(sbday));
            } catch (ParseException e) {
                //throw new ServletException(e.getMessage());
            }
            try {
                UserService.getService().updateUser(usr, dept);
                //UserService.getService().
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        resp.sendRedirect("/webappsample/jstl1");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userNo = req.getParameter(USER_NO_PARAM);
        if ((userNo != null) && (!userNo.equals(""))) {

            User user = null;
            try {
                user = UserService.getService().getUserById(Integer.parseInt(userNo));
            } catch (Exception e) {
            }
            ;
            if (user != null) {
                req.getSession().setAttribute(USER_NO_PARAM, userNo);
                req.setAttribute(USER_ATTRIBUTE, user);
                getServletContext().getRequestDispatcher("/userEdit.jsp").forward(req, resp);
            }
        }
    }
}

