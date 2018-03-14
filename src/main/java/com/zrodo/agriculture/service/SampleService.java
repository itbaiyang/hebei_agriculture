package com.zrodo.agriculture.service;

import com.zrodo.agriculture.entity.Sample;
import com.zrodo.agriculture.repository.CacheMapper;
import com.zrodo.agriculture.repository.ProductMapper;
import com.zrodo.agriculture.repository.SampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "sampleService")
public class SampleService {
    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public void addSample(Sample sample) {
        String sampleNo, sampleList;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String typeSymbol = productMapper.queryProductTypeById(sample.getProductTypeId());
        sampleNo = typeSymbol + formatter.format(date);
        sampleList = sampleMapper.getSampleNos(sampleNo);
        DecimalFormat df = new DecimalFormat("0000");
        if (sampleList != null) {
            if (sampleList.length() > 10) {
                sampleNo = sampleNo + df.format(1 + Integer.parseInt(sampleList.substring(7, 11)));
                if (Integer.parseInt(df.format(1 + Integer.parseInt(sampleList.substring(7, 11)))) > 9998) {
                    sampleNo = sampleNo + "0001";
                }
            } else {
                sampleNo = sampleNo + "0001";
            }
        } else {
            sampleNo = sampleNo + "0001";
        }
        sample.setSampleNo(sampleNo);
        sampleMapper.addSample(sample);
    }
}
