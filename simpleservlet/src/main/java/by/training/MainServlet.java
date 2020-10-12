package by.training;

import sun.rmi.rmic.Main;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 102831973239L;

    public MainServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("it's a test");
        writer.close();
        resp.sendError(501,"blabla Error");
    }
}
