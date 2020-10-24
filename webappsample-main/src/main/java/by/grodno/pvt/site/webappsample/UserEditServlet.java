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
        int uNo = Integer.parseInt(userNo);
        User usr = UserService.getService().getUserByNom(uNo);
        if (usr != null) {
            usr.setFirstName(req.getParameter("firstName"));
            usr.setLastName(req.getParameter("lastName"));
            usr.setMale("true".equals(req.getParameter("male")));
            try {
                usr.setBirthdate(new SimpleDateFormat("yyy-MM-dd").parse(req.getParameter("birthdate")));
            } catch (ParseException e) {
                throw new ServletException(e.getMessage());
            }
            UserService.getService().updateUser(usr, uNo);
        }
        resp.sendRedirect("/webappsample/jstl1");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userNo = req.getParameter(USER_NO_PARAM);
        if ((userNo != null) && (!userNo.equals(""))) {
            User user = UserService.getService().getUserByNom(Integer.parseInt(userNo));
            if (user != null) {
                req.getSession().setAttribute(USER_NO_PARAM, userNo);
                req.setAttribute(USER_ATTRIBUTE, user);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/useredit.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
