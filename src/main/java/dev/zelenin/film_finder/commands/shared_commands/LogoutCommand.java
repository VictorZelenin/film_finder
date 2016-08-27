package dev.zelenin.film_finder.commands.shared_commands;

import dev.zelenin.film_finder.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 22.08.16.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();

        return new MainCommand().execute(request);
    }
}
