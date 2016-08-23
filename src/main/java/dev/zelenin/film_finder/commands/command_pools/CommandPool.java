package dev.zelenin.film_finder.commands.command_pools;

import dev.zelenin.film_finder.commands.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by victor on 17.08.16.
 */
public abstract class CommandPool implements ICommandPool {

    private final Map<String, Command> commandMap;

    protected CommandPool() {
        commandMap = new HashMap<>();
    }

    protected void addCommand(String name, Command command) {
        commandMap.put(name, command);
    }

    private Command removeCommand(String name) {
        return commandMap.remove(name);
    }

    @Override
    public Command getCommand(String name) {
        return commandMap.get(name);
    }
}
