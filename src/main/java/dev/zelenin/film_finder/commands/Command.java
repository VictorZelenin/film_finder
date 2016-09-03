package dev.zelenin.film_finder.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 17.08.16.
 */
public interface Command {

    String execute(HttpServletRequest request);
}
