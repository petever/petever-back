package com.example.petever.utils;

import com.example.petever.config.FileConfiguration;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FileUtil {

    //    private static final String FILE_PATH = "src/main/resources/static/images/";
    private static final String FILE_PATH = "/home/ec2-user/petever-back/src/main/resources/static/images/";
    private static final String RETURN_FILE_PATH = "/images/";

    public static String save(byte[] value, String fileName, String ext) {
        return FileUtil.save(value, fileName, ext, false, true, null);
    }

    public static String save(byte[] value, String fileName, String ext, boolean append, boolean override, FileConfiguration fileConfiguration) {
        String base64EncodingFileName = new String(Base64.getUrlEncoder().encode(fileName.getBytes(StandardCharsets.UTF_8))) + "." + ext;
        String folderPath = fileConfiguration == null ? FILE_PATH : fileConfiguration.getSaveFolderPath();
        String filePath = folderPath + base64EncodingFileName;
        File file = new File(filePath);
        if (file.exists() && !file.isFile()) {
            throw new RuntimeException("Not File");
        }
        if (file.exists() && override) {
            file.deleteOnExit();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can Not Create File", e);
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, append)) {
            fileOutputStream.write(value);
            fileOutputStream.flush();
            return RETURN_FILE_PATH + base64EncodingFileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
