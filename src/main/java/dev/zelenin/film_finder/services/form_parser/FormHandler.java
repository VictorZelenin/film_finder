package dev.zelenin.film_finder.services.form_parser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by victor on 03.09.16.
 */
@FunctionalInterface
public interface FormHandler {
    Map<String, Object> handle(HttpServletRequest request);
}
