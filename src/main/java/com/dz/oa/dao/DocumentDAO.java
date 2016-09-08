package com.dz.oa.dao;

import com.dz.oa.entity.ProjDocInfo;

import java.util.List;

/**
 * Created by daweizhuang on 8/23/16.
 */
public interface DocumentDAO {
    List<ProjDocInfo> getDocInfoByProjId(int projId);

    ProjDocInfo saveFileInfo(ProjDocInfo projDocInfo);
}
