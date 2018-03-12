package com.zrodo.agriculture.service;

import com.zrodo.agriculture.entity.DetectReport;
import com.zrodo.agriculture.entity.Product;
import com.zrodo.agriculture.entity.Sample;
import com.zrodo.agriculture.repository.DetectReportMapper;
import com.zrodo.agriculture.repository.SampleMapper;
import com.zrodo.agriculture.util.Tool;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service(value = "detectService")
public class DetectService {

    @Autowired
    private DetectReportMapper detectReportMapper;
    @Autowired
    private SampleMapper sampleMapper;

    @Transactional
    public void insertDetectReport(String sampleDate, Integer tacheId, Integer sampleNoId, DetectReport detectReport) {
        Sample sample = new Sample();
        if (detectReport.getSampleId() == 0) {
            sample.setProductId(detectReport.getProductId());
            sample.setSampleId(sampleNoId);
            sample.setTacheId(tacheId);
            sample.setSampleDate(sampleDate);
            sample.setcUserId(detectReport.getDetectUserId());
            int sampleId = sampleMapper.addSample(sample);
            detectReport.setSampleId(sampleId);
        }
        detectReportMapper.addDetectReport(detectReport);
        List<String> img = detectReport.getImageUrls();
        for (String item : img) {
            detectReportMapper.addDetectImages(detectReport.getDetectId(), item);
        }
    }

    @Transactional
    public void deleteDetectReport(Integer detectId) {
        Sample sample = new Sample();
        detectReportMapper.deleteDetectReport(detectId);
        detectReportMapper.deleteDetectImages(detectId);
    }
}
