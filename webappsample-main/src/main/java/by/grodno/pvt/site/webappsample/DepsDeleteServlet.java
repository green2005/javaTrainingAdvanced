package by.grodno.pvt.site.webappsample;

import by.grodno.pvt.site.webappsample.service.DeptsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepsDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int depid = Integer.parseInt(req.getParameter("depid"));
        try {
            DeptsService.getService().deleteDep(depid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/webappsample/deps");
    }
}
