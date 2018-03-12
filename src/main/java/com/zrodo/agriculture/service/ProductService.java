package com.zrodo.agriculture.service;

import com.zrodo.agriculture.entity.Product;
import com.zrodo.agriculture.repository.ProductMapper;
import com.zrodo.agriculture.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

@Service(value = "productService")
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    @Transactional
    public void addProduct(Product product) {
        String productNo = UUID.randomUUID().toString();
        product.setProductNo(productNo);
        int productTypeId = product.getProductTypeId();
        Calendar a = Calendar.getInstance();
        int nextval = productMapper.getProductSequence(product.getCompanyId(), a.get(Calendar.YEAR));
        String result = String.format("%03d", nextval) + Tool.getRandomNumber(2);
        String year = "" + a.get(Calendar.YEAR);
        String processNo = year.substring(year.length() - 2) + String.format("%02d", a.get(Calendar.MONTH) + 1) + productTypeId + result;
        product.setProcessNo(processNo);
        product.setSerialNumber(String.format("%03d", nextval));
        productMapper.addProduct(product);
    }

}
