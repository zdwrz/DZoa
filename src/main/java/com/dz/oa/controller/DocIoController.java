package com.dz.oa.controller;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping({"/doc"})
public class DocIoController {
    private final static Logger LOGGER = Logger.getLogger(DocIoController.class);

    @RequestMapping("/create")
    public String newDoc(ModelMap model) {
        return "documents/uploadDoc";
    }

    @RequestMapping("/upload")
    public String uploadDoc(ModelMap model) {
        LOGGER.debug("File uploading ......");
        return "123";
    }

    @RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
    public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response,
                                      HttpServletRequest request) throws IOException {
        File downloadFile = new File("/Users/daweizhuang/Documents/Resumes/Vincent Yin.docx");
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = request.getServletContext().getMimeType("/Users/daweizhuang/Documents/Resumes/Vincent Yin.docx");
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }

    @RequestMapping("/manage")
    public String findDocs(ModelMap model) {
        return "documents/findDoc";
    }

}
