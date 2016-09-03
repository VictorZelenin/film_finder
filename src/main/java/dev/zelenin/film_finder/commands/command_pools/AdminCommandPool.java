package dev.zelenin.film_finder.commands.command_pools;

import dev.zelenin.film_finder.commands.admin_commands.*;
import dev.zelenin.film_finder.commands.shared_commands.AcceptLoginFormCommand;
import dev.zelenin.film_finder.commands.shared_commands.LogoutCommand;
import dev.zelenin.film_finder.utils.Commands;

/**
 * Created by victor on 17.08.16.
 */
public class AdminCommandPool extends CommandPool {
    public AdminCommandPool() {
        addCommand(Commands.ADMIN_MAIN, new AdminPageCommand());
        addCommand(Commands.ACCEPT_LOG_IN_FORM, new AcceptLoginFormCommand());
        addCommand(Commands.ADMIN_ADD_MOVIE, new AddMovieCommand());
        addCommand(Commands.ADMIN_ADD_PERSON, new AddActingPersonCommand());
        addCommand(Commands.ADMIN_EDIT_MOVIE, new EditMovieCommand());
        addCommand(Commands.ADMIN_EDIT_PERSON, new EditActingPersonCommand());
        addCommand(Commands.ADMIN_SHOW_CLIENTS, new ShowClientsCommand());
        addCommand(Commands.ADMIN_SHOW_FEEDBACK, new ShowFeedbackCommand());
        addCommand(Commands.LOG_OUT, new LogoutCommand());
//        addCommand();
    }
}
