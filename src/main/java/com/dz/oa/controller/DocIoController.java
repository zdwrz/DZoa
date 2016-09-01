package com.dz.oa.controller;

import com.dz.oa.exception.FileContentException;
import com.dz.oa.service.DocumentService;
import com.dz.oa.service.MessageService;
import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.FileUploadResponse;
import org.apache.commons.fileupload.FileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    public String uploadDoc(@RequestParam("file") MultipartFile file) throws FileContentException {
        LOGGER.debug("File uploading ......");
        String name = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try{
                documentService.saveFile(file.getBytes(), OaUtils.timeStampPrefix(name), 1);
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public FileUploadResponse handleValidationException(FileContentException e){
        LOGGER.error(e);
        FileUploadResponse response = new FileUploadResponse();
        response.setError(e.getMessage()); // has to be error field, front end js needs this
        return response;
    }
}
