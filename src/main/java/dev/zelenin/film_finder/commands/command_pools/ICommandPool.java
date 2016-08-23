package dev.zelenin.film_finder.commands.command_pools;

import dev.zelenin.film_finder.commands.Command;

/**
 * Created by victor on 17.08.16.
 */
public interface ICommandPool {

    Command getCommand(String name);
}
