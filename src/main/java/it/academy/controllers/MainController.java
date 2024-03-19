package it.academy.controllers;

import it.academy.annotations.ControllerMapping;
import it.academy.annotations.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@ControllerMapping(mappingUrl = "/main")
public class MainController implements Controller {

    @GetMapping(url = "/test")
    public static void testMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF8");
        printWriter.print("<p>TEST METHOD</p>");
        printWriter.flush();
    }

}
