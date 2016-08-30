package com.dz.oa.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by daweizhuang on 8/30/16.
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    private final static Logger LOGGER = Logger.getLogger(DocumentServiceImpl.class);

    @Value("${temp_file_location}")
    String tempFileLocation;

    @Override
    @Transactional
    public boolean saveFile(byte[] file, String fileName, int userId) throws IOException {
        this.saveFileToPath(file, fileName);
        return true;
    }

    private void saveFileToPath(byte[] file, String fileName) throws IOException {

        // Creating the directory to store file
        // String rootPath = System.getProperty("catalina.home");
        File dir = new File(tempFileLocation + File.separator + "tmpFiles");
        if (!dir.exists())
            dir.mkdirs();

        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(file);
        stream.close();

        LOGGER.info("Server File Location="
                + serverFile.getAbsolutePath());

    }
}
