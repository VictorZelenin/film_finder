package dev.zelenin.film_finder.commands.admin_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.services.ActingPersonService;
import dev.zelenin.film_finder.services.form_parser.ActingPersonFormHandler;
import dev.zelenin.film_finder.services.form_parser.FormHandler;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 05.09.16.
 */
public class AcceptAddingPersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        FormHandler formHandler = new ActingPersonFormHandler();
        Map<String, Object> attributes = formHandler.handle(request);

        System.out.println(attributes);

        String name = constructClientName(attributes);
        String gender = (String) attributes.get("gender");
        double height = Double.parseDouble((String) attributes.get("height"));
        String country = (String) attributes.get("country");
        int age = Integer.parseInt((String) attributes.get("age"));
        int totalMoviesNumber = Integer.parseInt((String) attributes.get("movies_number"));
        Date deathDate = null;
        try {
            deathDate = parseDeathDate((String) attributes.get("death_date"));
        } catch (ParseException e) {
            System.err.println("can not parse date");
            e.printStackTrace();
        }
        String photoUrl = (String) attributes.get("photo_url");

        List<ActingRole> roles = (List<ActingRole>) attributes.get("roles");
        List<Genre> genres = (List<Genre>) attributes.get("genres");

        ActingPersonService.saveActingPerson(name, gender, height, country, age, deathDate,
                totalMoviesNumber, photoUrl, roles, genres);

        return Paths.ADMIN;
    }


    private String constructClientName(Map<String, Object> attributes) {
        return attributes.get("first_name") + " " + attributes.get("last_name");
    }

    private Date parseDeathDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return date.isEmpty() ? null : simpleDateFormat.parse(date);
    }
}
