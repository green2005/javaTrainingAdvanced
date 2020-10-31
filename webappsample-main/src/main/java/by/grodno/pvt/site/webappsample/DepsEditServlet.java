package by.grodno.pvt.site.webappsample;

import by.grodno.pvt.site.webappsample.service.Dept;
import by.grodno.pvt.site.webappsample.service.DeptsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepsEditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int depid = (Integer) req.getSession().getAttribute("depid");
        String depName = (String) req.getParameter("Name");
        req.getSession().removeAttribute("depid");
        DeptsService.getService().updateDept(depName, depid);
        resp.sendRedirect("/webappsample/deps");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int deptId = Integer.parseInt(req.getParameter("depid"));
        Dept dept = null;
        try {
            dept = DeptsService.getService().getDeptById(deptId);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (dept == null) {
            resp.sendRedirect("/webappsample/deps");
        } else {
            req.getSession().setAttribute("depid", dept.getId());
            req.setAttribute("dep", dept);
            getServletContext().getRequestDispatcher("/depsEdit.jsp").forward(req, resp);
        }
    }
}
