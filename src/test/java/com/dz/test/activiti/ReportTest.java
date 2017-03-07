package com.dz.test.activiti;

import org.trs.oa.config.ActivitiConfig;
import org.trs.oa.config.MvcConfig;
import org.trs.oa.config.PersistenceJPAConfig;
import org.trs.oa.service.PDFReportService;
import org.trs.oa.service.ProjectService;
import net.sf.jasperreports.engine.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
