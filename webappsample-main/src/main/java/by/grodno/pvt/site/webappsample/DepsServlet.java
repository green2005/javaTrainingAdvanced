package by.grodno.pvt.site.webappsample;

import by.grodno.pvt.site.webappsample.service.Dept;
import by.grodno.pvt.site.webappsample.service.DeptsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> depts = null;
        try {
            depts = DeptsService.getService().getDeptsList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("deps", depts);
        getServletContext().getRequestDispatcher("/deps.jsp").forward(req, resp);
    }
}