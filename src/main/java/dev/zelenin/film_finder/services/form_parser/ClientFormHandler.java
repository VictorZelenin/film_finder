package dev.zelenin.film_finder.services.form_parser;

import dev.zelenin.film_finder.data.data_sets.users.Client;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static dev.zelenin.film_finder.services.ClientService.uploadClientImage;

/**
 * Created by victor on 03.09.16.
 */
public class ClientFormHandler implements FormHandler {
    private ServletFileUpload uploader = null;
    private static final String DIRECTORY = "/home/victor/IdeaProjects/film_finder_github/" +
            "src/main/webapp/resources/images/clients";


    private void initUploader() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        this.uploader = new ServletFileUpload(fileFactory);
    }

    @Override
    public Map<String, Object> handle(HttpServletRequest request) {
        String directory = "/resources/images/clients/";
        Client client = (Client) request.getSession().getAttribute("client");

        initUploader();
        Map<String, Object> attributes = new HashMap<>();

        try {
            for (FileItem fileItem : uploader.parseRequest(request)) {
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

        savePhoto(attributes, client);

        return attributes;
    }

    private void savePhoto(Map<String, Object> attributes, Client client) {
        if (client.getClientPhoto() == null) {
            try {
                uploadClientImage(DIRECTORY + File.separator, (FileItem) attributes.get("photo_item"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            attributes.put("photo_url", client.getClientPhoto());
        }
    }
}
