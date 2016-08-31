package dev.zelenin.film_finder.commands;

import dev.zelenin.film_finder.commands.command_pools.AdminCommandPool;
import dev.zelenin.film_finder.commands.command_pools.ClientCommandPool;
import dev.zelenin.film_finder.commands.command_pools.ICommandPool;
import dev.zelenin.film_finder.utils.Commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by victor on 17.08.16.
 */
public class CommandProvider {
    private static final Map<String, ICommandPool> commandPools = new HashMap<>();

    static {
        commandPools.put("controller", new ClientCommandPool());
        commandPools.put("admin", new AdminCommandPool());
    }

    public static Command getCommand(HttpServletRequest request) {
        final String commandName;
        final Command command;
        final ICommandPool commandPool;

        commandName = request.getParameter("command");
        System.out.println(commandPools.get(chooseCommandPool(request)));
        commandPool = commandPools.get(chooseCommandPool(request));
        command = commandPool.getCommand(commandName);

        if (Objects.equals(request.getRequestURI(), "/admin") && command == null) {
            return commandPool.getCommand(Commands.ADMIN_MAIN);
        }

        if (command != null) {
            return command;
        }

        return commandPool.getCommand(Commands.MAIN);
    }

    private static String chooseCommandPool(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/admin") ? "admin" : "controller";
    }
}
