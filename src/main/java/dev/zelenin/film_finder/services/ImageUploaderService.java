package dev.zelenin.film_finder.services;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

/**
 * Created by victor on 10.08.16.
 */
public class ImageUploaderService {

    public static void uploadImage(String path, FileItem imageItem) throws Exception {
        File file = new File(path + imageItem.getName());
        imageItem.write(file);
    }
}
