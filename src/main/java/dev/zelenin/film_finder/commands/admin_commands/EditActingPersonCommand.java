package dev.zelenin.film_finder.commands.admin_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 31.08.16.
 */
public class EditActingPersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Paths.ADMIN_EDIT_PERSON;
    }
}
