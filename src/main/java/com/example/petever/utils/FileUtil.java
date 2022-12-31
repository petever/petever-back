package com.example.petever.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileUtil {

    private static final String FILE_PATH = "src/main/resources/static/images/";
//    private static final String FILE_PATH = "/home/ec2-user/new-petever/src/main/resources/static/images/";
    private static final String RETURN_FILE_PATH = "/images/";

    public static String save(String urlPath, String fileName) {
        try (ReadableByteChannel rbc = Channels.newChannel(new URL(urlPath).openStream());
             FileOutputStream fos = new FileOutputStream(FILE_PATH + fileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            return RETURN_FILE_PATH + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
