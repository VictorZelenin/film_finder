package dev.zelenin.film_finder.services.form_parser;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 05.09.16.
 */
@SuppressWarnings("Duplicates")
// TODO refactoring here !!
public class ActingPersonFormHandler extends FormHandler {
    private static final String DIRECTORY = "/home/victor/IdeaProjects/film_finder_github/" +
            "src/main/webapp/resources/images/acting_person_images";

    @Override
    public Map<String, Object> handle(HttpServletRequest request) {
        String directory = "/resources/images/acting_person_images/";

        Map<String, Object> attributes = new HashMap<>();
        List<ActingRole> roles = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        initUploader();

        try {
            for (FileItem fileItem : uploader.parseRequest(request)) {
                if (fileItem.getFieldName().equals("roles")) {
                    roles.add(ActingRole.valueOf(fileItem.getString()));
                }

                if (fileItem.getFieldName().equals("genres")) {
                    genres.add(Genre.valueOf(fileItem.getString()));
                }

                if (fileItem.isFormField()) {
                    attributes.put(fileItem.getFieldName(), fileItem.getString());
                } else {
                    attributes.put("photo_url", directory + fileItem.getName());
                    attributes.put("photo_item", fileItem);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        attributes.put("genres", genres);
        attributes.put("roles", roles);

        savePhoto(attributes, DIRECTORY);

        return attributes;
    }

    @Override
    protected void savePhoto(Map<String, Object> attributes, String directory) {
        if (!attributes.get("photo_url").equals("/resources/images/acting_person_images/"))
            super.savePhoto(attributes, directory);
        else
            attributes.put("photo_url", "");
    }
}
