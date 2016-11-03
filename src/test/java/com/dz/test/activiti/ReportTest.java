package com.dz.test.activiti;

import com.dz.oa.config.ActivitiConfig;
import com.dz.oa.config.MvcConfig;
import com.dz.oa.config.PersistenceJPAConfig;
import com.dz.oa.converter.TimesheetToReportConverter;
import com.dz.oa.entity.TsApproval;
import com.dz.oa.service.PDFReportService;
import com.dz.oa.service.ProjectService;
import com.dz.oa.service.TimesheetService;
import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.TimeSheetDateVO;
import com.dz.oa.vo.TimeSheetProjectVO;
import com.dz.oa.vo.TsReportItem;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daweizhuang on 11/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ActivitiConfig.class, PersistenceJPAConfig.class, MvcConfig.class})
@EnableTransactionManagement
public class ReportTest {
    @Autowired
    ProjectService projectService;

    @Autowired
    PDFReportService PDFReportService;
//    @Test
//    @Ignore
//    public void testReport() throws JRException {
//        String jasperFileName = "/Users/daweizhuang/JaspersoftWorkspace/MyReports/Timesheet_report.jasper";
//        HashMap<String, Object> parameters = new HashMap<>();
////		Class.forName("com.mysql.jdbc.Driver");
////		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
////		JRDataSource dataSource = new JREmptyDataSource();
//        List<Apple> testList = new ArrayList<>();
//        testList.add(new Apple(1,"123123123"));
//        testList.add(new Apple(2,"asdf"));
//        testList.add(new Apple(3,"dfg"));
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(testList);
//        JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, parameters, dataSource);
//        JasperExportManager.exportReportToPdfFile(jprint, "/Users/daweizhuang/Desktop/ts.pdf");
//    }
    @Test
    @Ignore
    public void testReport1() throws JRException {
        int userId = 1;
        int weekId = -3;
        PDFReportService.generateTimeSheetReport(userId, weekId);
    }
}
