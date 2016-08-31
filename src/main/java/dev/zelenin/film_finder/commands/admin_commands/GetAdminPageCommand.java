package dev.zelenin.film_finder.commands.admin_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 31.08.16.
 */
public class GetAdminPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") == null)
            return Paths.ADMIN_LOGIN;

        return Paths.ADMIN;
    }
}
