package by.grodno.pvt.site.webappsample;

import by.grodno.pvt.site.webappsample.service.Dept;
import by.grodno.pvt.site.webappsample.service.DeptsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeptsAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String depName = (String) req.getParameter("Name");
        Dept dept = new Dept();
        dept.setName(depName);
        try {
            DeptsService.getService().addDept(dept);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        resp.sendRedirect("/webappsample/deps");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/depsEdit.jsp").forward(req, resp);
    }


}
