package main.java.com;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/helloServlet")
public class Hello extends HttpServlet {

//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        handleRequest(req, resp);
//    }
//    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//
//        resp.setContentType("text/html");
//
//        // Post Parameters From The Request
//        String param1 = (String) req.getSession().getAttribute("uname");
//
//        // Building & Printing The HTML Response Code
//    }
}
