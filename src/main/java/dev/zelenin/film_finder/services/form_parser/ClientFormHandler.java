package dev.zelenin.film_finder.services.form_parser;

import dev.zelenin.film_finder.data.data_sets.users.Client;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by victor on 03.09.16.
 */
public class ClientFormHandler extends FormHandler {
    private static final String DIRECTORY = "/home/victor/IdeaProjects/film_finder_github/" +
            "src/main/webapp/resources/images/clients";
    private HttpServletRequest request;
//
//    private void initUploader() {
//        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
//        this.uploader = new ServletFileUpload(fileFactory);
//    }

    @Override
    public Map<String, Object> handle(HttpServletRequest request) {
        this.request = request;
        String directory = "/resources/images/clients/";

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

        savePhoto(attributes, DIRECTORY);

        return attributes;
    }

    @Override
    protected void savePhoto(Map<String, Object> attributes, String directory) {
        Client client = (Client) request.getSession().getAttribute("client");

        if (client == null || client.getClientPhoto() == null) {
            super.savePhoto(attributes, directory);
        } else {
            attributes.put("photo_url", client.getClientPhoto());
        }
    }
}
