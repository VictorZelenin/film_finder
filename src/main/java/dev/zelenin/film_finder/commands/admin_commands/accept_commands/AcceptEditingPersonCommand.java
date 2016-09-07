package dev.zelenin.film_finder.commands.admin_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 07.09.16.
 */
public class AcceptEditingPersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {


        return Paths.ADMIN_EDIT_PERSON;
    }
}
