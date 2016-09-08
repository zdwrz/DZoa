package com.dz.oa.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by daweizhuang on 8/30/16.
 */
public interface DocumentService {
    boolean saveFile(byte[] file, String fileName, int userId) throws IOException;

    boolean saveFile(byte[] bytes, String s, int attribute, int id) throws IOException;
}
