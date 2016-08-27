package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 26.08.16.
 */
public class GetSearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Paths.SEARCH_PAGE;
    }
}
