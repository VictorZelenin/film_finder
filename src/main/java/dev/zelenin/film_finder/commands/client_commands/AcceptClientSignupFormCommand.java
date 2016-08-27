package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.ClientSignupService;
import dev.zelenin.film_finder.services.ImageUploaderService;
import dev.zelenin.film_finder.utils.Paths;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by victor on 22.08.16.
 */
public class AcceptClientSignupFormCommand implements Command {
    private ServletFileUpload uploader = null;
    private static final String DIRECTORY = "/home/victor/IdeaProjects/film_finder_github/" +
            "src/main/webapp/resources/images/clients";

    public void init() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        this.uploader = new ServletFileUpload(fileFactory);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String directory = "/resources/images/clients/";

        init();

        Map<String, Object> attributes;
        try {
            attributes = parseClientAttributes(uploader.parseRequest(request));
        } catch (FileUploadException e) {
            System.err.println("can not upload data");
            // set up request attribute

            return Paths.SIGN_UP;
        }

        System.out.println(attributes);

        if (!isCorrectPassword((String) attributes.get("password"),
                (String) attributes.get("password_confirmation"))) {
            // setup attributes
            System.out.println("Different passwords");
            request.setAttribute("samePassword", false);

            return Paths.SIGN_UP;

        } else if (ClientSignupService.exists((String) attributes.get("email"))) {
            // setup attributes
            request.setAttribute("isRegistered", true);
            System.out.println("Existed");

            return Paths.SIGN_UP;
        } else {
            if (attributes.get("photo_url") != null &&
                    attributes.get("photo_url").equals(directory.trim())) {
                attributes.put("photo_url", null);
            }

            String name = attributes.get("first_name") + " " + attributes.get("last_name");
            Client client = ClientSignupService.register(
                    name,
                    (String) attributes.get("gender"),
                    (String) attributes.get("email"),
                    (String) attributes.get("password"),
                    (String) attributes.get("photo_url")
            );

            try {
                ImageUploaderService.uploadImage(DIRECTORY + File.separator,
                        (FileItem) attributes.get("photo_item"));
            } catch (Exception e) {

                return Paths.SIGN_UP;
            }

            request.getSession().setAttribute("client", client);
        }

        return new PersonalCabinetCommand().execute(request);
    }

    private Map<String, Object> parseClientAttributes(List<FileItem> fileItemList) {
        String directory = "/resources/images/clients/";
        Map<String, Object> map = new HashMap<>();

        for (FileItem fileItem : fileItemList) {
            if (fileItem.isFormField()) {
                switch (fileItem.getFieldName()) {
                    case "first_name":
                        map.put("first_name", fileItem.getString());
                        break;
                    case "last_name":
                        map.put("last_name", fileItem.getString());
                        break;
                    case "email":
                        map.put("email", fileItem.getString());
                        break;
                    case "optradio":
                        map.put("gender", fileItem.getString());
                        break;
                    case "password":
                        map.put("password", fileItem.getString());
                        break;
                    case "password_confirmation":
                        map.put("password_confirmation", fileItem.getString());
                        break;
                }
            } else {
                map.put("photo_url", directory + fileItem.getName());
                map.put("photo_item", fileItem);
            }
        }

        return map;
    }

    private boolean isCorrectPassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation) && !Objects.equals(password, "");
    }
}
