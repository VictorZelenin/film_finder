package dev.zelenin.film_finder.controllers;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.CommandProvider;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by victor on 17.08.16.
 */
@WebServlet(urlPatterns = {"/controller", "/admin", ""})
public class FrontController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final Command command = CommandProvider.getCommand(request);
        final String page = command.execute(request);
        System.out.println(request.getRequestURI());
        System.out.println(command);
        System.out.println(page);

        dispatch(request, response, page);

    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response,
                          String page) throws ServletException, IOException {
        if ((request.getRequestURI().equals("/") ||
                request.getRequestURI().equals("/controller") ||
                request.getRequestURI().equals("/admin")) && Objects.equals(page, Paths.CONTROLLER)) {

            request.getRequestDispatcher(Paths.MAIN).forward(request, response);

        } else if (page.equals(request.getRequestURI())) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
