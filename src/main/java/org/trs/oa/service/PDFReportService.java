package org.trs.oa.service;

import net.sf.jasperreports.engine.JRException;

/**
 * Created by daweizhuang on 11/2/16.
 */
public interface PDFReportService {
    Integer generateTimeSheetReport(int userId, int weekId) throws JRException;
}
