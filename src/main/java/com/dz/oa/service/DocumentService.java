package com.dz.oa.service;

import com.dz.oa.entity.ProjDocInfo;
import com.dz.oa.vo.ProjDocVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by daweizhuang on 8/30/16.
 */
public interface DocumentService {
    boolean saveFile(MultipartFile file,int userId) throws IOException;

    boolean saveFile(MultipartFile file,  int userId, int projId , Date fileDate) throws IOException;

    List<ProjDocInfo> getDocInfoByProjectId(int id);

    ProjDocInfo getDocInfoById(int fileId);

    List<ProjDocVO> getLatestDoc();
}
