package com.dz.oa.controller;

import com.dz.oa.entity.ProjDocInfo;
import com.dz.oa.exception.FileContentException;
import com.dz.oa.service.DocumentService;
import com.dz.oa.service.MessageService;
import com.dz.oa.utility.Constants;
import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.FileUploadResponse;
import org.apache.commons.fileupload.FileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping({"/doc"})
public class DocIoController {
    private final static Logger LOGGER = Logger.getLogger(DocIoController.class);

    @RequestMapping("/create")
    public String newDoc(ModelMap model) {
        return "documents/uploadDoc";
    }

    @Value("${temp_file_location}")
    String tempFileLocation;

    @Autowired
    DocumentService documentService;

    @Autowired
    MessageService msg;

    @RequestMapping("/upload")
    @ResponseBody
    public String uploadProjDoc(@RequestParam("file") MultipartFile file, @RequestParam("project_id")String pid, @RequestParam("file_date") @DateTimeFormat(pattern="yyyy-MM-dd")Date fileDate, HttpSession session) throws FileContentException {
        LOGGER.debug("File uploading ......");
        String name = file.getOriginalFilename();
        if (fileDate == null) {
            fileDate = new Date();
        }
        if (!file.isEmpty()) {
            try{
                String id = pid.replace(Constants.PROJECT_ID_PREFIX, "");
                documentService.saveFile(file,(int)session.getAttribute(Constants.USER_ID), Integer.parseInt(id),fileDate);
                return msg.getMessage("file_uploaded_success",new String[]{name});
            } catch (Exception e) {
                LOGGER.error("error in uploading .. " + e.getLocalizedMessage());
                throw new FileContentException("Failed to upload the file.");
            }
        } else {
            throw new FileContentException( "You failed to upload " + name
                    + " because the file was empty.");
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void getProjFile(int fileId, HttpServletResponse response,
                        HttpServletRequest request) throws IOException {
        getFile(fileId,response,request);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void removeProjFile(@RequestParam("doc_to_remove_id") int fileId, HttpServletResponse response,
                            HttpServletRequest request) throws IOException {
       LOGGER.info("delete: " + fileId);
    }
    private void getFile(int fileId, HttpServletResponse response,
                         HttpServletRequest request) throws IOException{
        ProjDocInfo docInfo = documentService.getDocInfoById(fileId);
        File downloadFile = new File(docInfo.getFileLocation());
        FileInputStream inputStream = new FileInputStream(downloadFile);
        // get MIME type of the file
        String mimeType = request.getServletContext().getMimeType(docInfo.getFileLocation());
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                //downloadFile.getName());
                docInfo.getDocName());
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
//
//    @RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
//    public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response,
//                                      HttpServletRequest request) throws IOException {
//        File downloadFile = new File("/Users/daweizhuang/Documents/Resumes/Vincent Yin.docx");
//        FileInputStream inputStream = new FileInputStream(downloadFile);
//
//        // get MIME type of the file
//        String mimeType = request.getServletContext().getMimeType("/Users/daweizhuang/Documents/Resumes/Vincent Yin.docx");
//        if (mimeType == null) {
//            // set to binary type if MIME mapping not found
//            mimeType = "application/octet-stream";
//        }
//        System.out.println("MIME type: " + mimeType);
//
//        // set content attributes for the response
//        response.setContentType(mimeType);
//        response.setContentLength((int) downloadFile.length());
//
//        // set headers for the response
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("attachment; filename=\"%s\"",
//                downloadFile.getName());
//        response.setHeader(headerKey, headerValue);
//
//        // get output stream of the response
//        OutputStream outStream = response.getOutputStream();
//
//        byte[] buffer = new byte[4096];
//        int bytesRead = -1;
//
//        // write bytes read from the input stream into the output stream
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//
//        inputStream.close();
//        outStream.close();
//    }

    @RequestMapping("/manage")
    public String findDocs(ModelMap model) {
        return "documents/findDoc";
    }

    @ExceptionHandler(FileContentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public FileUploadResponse handleFileContentException(FileContentException e){
        LOGGER.error(e);
        FileUploadResponse response = new FileUploadResponse();
        response.setError(e.getMessage()); // has to be error field, front end js needs this
        return response;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public FileUploadResponse handleException(Exception e){
        LOGGER.error(e);
        FileUploadResponse response = new FileUploadResponse();
        response.setError(e.getMessage()); // has to be error field, front end js needs this
        return response;
    }
}
