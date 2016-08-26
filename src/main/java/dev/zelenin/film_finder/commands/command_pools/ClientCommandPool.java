package dev.zelenin.film_finder.commands.command_pools;

import dev.zelenin.film_finder.commands.client_commands.AcceptClientSignupFormCommand;
import dev.zelenin.film_finder.commands.client_commands.PersonalCabinetCommand;
import dev.zelenin.film_finder.commands.shared_commands.*;
import dev.zelenin.film_finder.utils.Commands;

/**
 * Created by victor on 17.08.16.
 */
public class ClientCommandPool extends CommandPool {
    public ClientCommandPool() {
        addCommand(Commands.MAIN, new MainCommand());
        addCommand(Commands.PERSONAL_CABINET, new PersonalCabinetCommand());
        addCommand(Commands.LOG_IN, new LoginCommand());
        addCommand(Commands.ACCEPT_LOG_IN_FORM, new AcceptLoginFormCommand());
        addCommand(Commands.LOG_OUT, new LogoutCommand());
        addCommand(Commands.SIGN_UP, new SignupCommand());
        addCommand(Commands.ACCEPT_SIGN_UP_FORM, new AcceptClientSignupFormCommand());
        addCommand(Commands.SHOW_MOVIE, new MovieDescriptionCommand());
        addCommand(Commands.SHOW_MOVIE_LIST, new ShowMovieListCommand());
        addCommand(Commands.SEARCH, new SearchCommand());
    }
}
