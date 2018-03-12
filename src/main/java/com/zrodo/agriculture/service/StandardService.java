package com.zrodo.agriculture.service;

import com.zrodo.agriculture.entity.ProductiveStandard;
import com.zrodo.agriculture.entity.ProductiveStandardGlsq;
import com.zrodo.agriculture.entity.TObject;
import com.zrodo.agriculture.repository.ProductMapper;
import com.zrodo.agriculture.repository.ProductiveGlsqMapper;
import com.zrodo.agriculture.repository.ProductiveStandardMapper;
import com.zrodo.agriculture.util.Tool;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import com.zrodo.agriculture.util.json.JsonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "standardService")
public class StandardService {
    @Autowired
    ProductiveStandardMapper standardMapper;

    @Autowired
    ProductiveGlsqMapper glsqMapper;

    @Autowired
    ProductMapper productMapper;

    @Transactional
    public String addProductiveStandard(ProductiveStandard productiveStandard) throws Exception {
        String json;
        int num = productMapper.existObject(productiveStandard.getObjectName());
        if (num == 0) {
            TObject tObject = new TObject();
            tObject.setObjectName(productiveStandard.getObjectName());
            tObject.setOpenFlag("1");
            if (productiveStandard.getProductTypeId() == 2) {
                tObject.setUnit("头");
            } else {
                tObject.setUnit("公斤");
            }
            tObject.setcUserId(String.valueOf(productiveStandard.getcUserId()));
            tObject.setProductType(productiveStandard.getProductTypeId());
            productMapper.insertObject(tObject);
            productiveStandard.setObjectId(tObject.getObjectId());
        } else {
            productiveStandard.setObjectId(productMapper.getObjectId(productiveStandard.getObjectName()));
        }
        int count = standardMapper.queryProductiveStandardByName(productiveStandard.getCompanyId(),
                productiveStandard.getObjectId(), productiveStandard.getName());
        if (count > 0) {
            json = JsonStatus.validationError("该标准已经存在,如有需要,可以返回上级页面对该标准进行编辑");
        } else {
            standardMapper.addProductiveStandard(productiveStandard);
            Map<String, Object> map = JsonMapUtils.buildSuccessMap();
            map.put("result", productiveStandard);
            json = Tool.getJsonFromObect(map);
        }
        return json;
    }

    @Transactional
    public void copyStandard(Integer standardId, Integer copyStandardId) throws Exception {
        List<Map<String, Object>> list = glsqMapper.queryGlsqListByStandard(copyStandardId);
        for (Map<String, Object> item : list) {
            ProductiveStandardGlsq productiveStandardGlsq = new ProductiveStandardGlsq();
            productiveStandardGlsq.setStandardId(standardId);
            productiveStandardGlsq.setGlsqName((String) item.get("glsqName"));
            productiveStandardGlsq.setSubject((String) item.get("subject"));
            productiveStandardGlsq.setRequirement((String) item.get("requirement"));
            productiveStandardGlsq.setCreateDate(new Date());
            glsqMapper.addGlsq(productiveStandardGlsq);
        }
    }
}
