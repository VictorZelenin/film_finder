package dev.zelenin.film_finder.commands.shared_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.admin_commands.AdminPageCommand;
import dev.zelenin.film_finder.commands.client_commands.MainCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by victor on 22.08.16.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("client") != null) {
            session.removeAttribute("client");
            return new MainCommand().execute(request);
        }

        if (session.getAttribute("admin") != null) {
            session.removeAttribute("admin");
            return new AdminPageCommand().execute(request);
        }

        return new MainCommand().execute(request);
    }
}
