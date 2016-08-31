package dev.zelenin.film_finder.commands.client_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.PersonalCabinetCommand;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.ClientService;
import dev.zelenin.film_finder.utils.Paths;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

import static dev.zelenin.film_finder.commands.client_commands.accept_commands.AcceptClientSignupFormCommand.isCorrectPassword;
import static dev.zelenin.film_finder.commands.client_commands.accept_commands.AcceptClientSignupFormCommand.parseClientAttributes;

/**
 * Created by victor on 29.08.16.
 */
// TODO need refactoring here
// TODO validation
public class AcceptEditCommand implements Command {
    private ServletFileUpload uploader = null;
    private static final String DIRECTORY = "/home/victor/IdeaProjects/film_finder_github/" +
            "src/main/webapp/resources/images/clients";

    private void init() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        this.uploader = new ServletFileUpload(fileFactory);
    }


    @Override
    public String execute(HttpServletRequest request) {
        String directory = "/resources/images/clients/";
        Client client = (Client) request.getSession().getAttribute("client");
        Client updatedClient;

        init();

        Map<String, Object> attributes;
        try {
            attributes = parseClientAttributes(uploader.parseRequest(request));
        } catch (FileUploadException e) {
            System.err.println("can not upload data");

            return Paths.EDIT;
        }

        if (!isCorrectPassword((String) attributes.get("password"),
                (String) attributes.get("password_confirmation"))) {
            // setup attributes
            System.out.println("Different passwords");
            request.setAttribute("samePassword", false);

            return Paths.EDIT;

        } else {
            if (attributes.get("photo_url") == null &&
                    client.getClientPhoto() != null) {
                attributes.put("photo_url", client.getClientPhoto());
            } else if (attributes.get("photo_url") != null &&
                    attributes.get("photo_url").equals(directory.trim())) {

                attributes.put("photo_url", null);
            }

            String name = attributes.get("first_name") + " " + attributes.get("last_name");
            long clientId = client.getId();

            System.out.println(attributes);


            updatedClient = ClientService.updateClient(
                    clientId,
                    name,
                    (String) attributes.get("gender"),
                    (String) attributes.get("email"),
                    (String) attributes.get("password"),
                    (String) attributes.get("photo_url")
            );

            try {
                ClientService.uploadImage(DIRECTORY + File.separator,
                        (FileItem) attributes.get("photo_item"));
            } catch (Exception e) {
                System.out.println("Not uploaded !!");
            }
        }

        request.getSession().setAttribute("client", updatedClient);

        return new PersonalCabinetCommand().execute(request);
    }
}
