package dev.zelenin.film_finder.services.form_parser;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

import static dev.zelenin.film_finder.services.ClientService.uploadClientImage;

/**
 * Created by victor on 03.09.16.
 */
public abstract class FormHandler {
    protected ServletFileUpload uploader = null;

    protected void initUploader() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        this.uploader = new ServletFileUpload(fileFactory);
    }

    public abstract Map<String, Object> handle(HttpServletRequest request);

//    protected void fillUpAttributes(Map<String, Object> attributes, HttpServletRequest request,
//                                    String directory) {
//        initUploader();
//        System.out.println(uploader);
//
//        try {
//            for (FileItem item : uploader.parseRequest(request)) {
//                System.out.println(item.getString());
//                if (item.isFormField()) {
//                    attributes.put(item.getFieldName(), item.getString());
//                } else {
//                    attributes.put("photo_url", directory + item.getString());
//                    attributes.put("photo_item", item);
//                }
//            }
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//    }
//
    protected void savePhoto(Map<String, Object> attributes, String directory) {
        try {
            uploadClientImage(directory + File.separator, (FileItem) attributes.get("photo_item"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
