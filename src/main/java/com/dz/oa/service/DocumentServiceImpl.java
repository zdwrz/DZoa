package com.dz.oa.service;

import com.dz.oa.dao.DocumentDAO;
import com.dz.oa.entity.ProjDocInfo;
import com.dz.oa.entity.Project;
import com.dz.oa.entity.User;
import com.dz.oa.utility.OaUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by daweizhuang on 8/30/16.
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    private final static Logger LOGGER = Logger.getLogger(DocumentServiceImpl.class);

    @Value("${temp_file_location}")
    String tempFileLocation;

    @Autowired
    DocumentDAO docDAO;

    @Override
    @Transactional
    public boolean saveFile(MultipartFile file, int userId) throws IOException {
        this.saveFileToPath(file.getBytes(), file.getOriginalFilename());
        return true;
    }

    @Override
    @Transactional(rollbackFor = IOException.class)
    public boolean saveFile(MultipartFile file, int userId, int projId) throws IOException {
        String fileLocation = this.saveFileToPath(file.getBytes(), file.getOriginalFilename());
        ProjDocInfo projDocInfo = new ProjDocInfo();
        projDocInfo.setProject(new Project(projId));
        projDocInfo.setUploadTime(new Date());
        projDocInfo.setUser(new User(userId));
        projDocInfo.setDocName(file.getOriginalFilename());
        projDocInfo.setFileLocation(fileLocation);
        projDocInfo.setFileType(file.getContentType());
        docDAO.saveFileInfo(projDocInfo);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjDocInfo> getDocInfoByProjectId(int id) {
        return docDAO.getDocInfoByProjId(id);
    }

    private String saveFileToPath(byte[] file, String fileName) throws IOException {

        // Creating the directory to store file
        // String rootPath = System.getProperty("catalina.home");
        File dir = new File(tempFileLocation + File.separator + "tmpFiles");
        if (!dir.exists())
            dir.mkdirs();

        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + OaUtils.timeStampPrefix(fileName));
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(file);
        stream.close();

        LOGGER.info("Server File Location="
                + serverFile.getAbsolutePath());

        return serverFile.getAbsolutePath();
    }
}
