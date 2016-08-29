package dev.zelenin.film_finder.commands.command_pools;

import dev.zelenin.film_finder.commands.shared_commands.MainCommand;
import dev.zelenin.film_finder.utils.Commands;

/**
 * Created by victor on 17.08.16.
 */
public class AdminCommandPool extends CommandPool {
    public AdminCommandPool() {
        addCommand(Commands.MAIN, new MainCommand());
//        addCommand();
//        addCommand();
//        addCommand();
//        addCommand();
    }
}
