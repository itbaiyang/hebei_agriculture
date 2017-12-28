package com.zrodo.agriculture.service;

import com.zrodo.agriculture.entity.SysArea;
import com.zrodo.agriculture.entity.SysCity;
import com.zrodo.agriculture.repository.AreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "areaService")
public class AreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Transactional
    public void insertAreaFromCity(int cityId){

        /*根据城市的根节点，从city表获取区划列表*/
        List<SysCity> sysCities = areaMapper.getCityListById(cityId);
        /*把区划列表复制到area表*/
        SysArea sysArea = new SysArea();
        for(SysCity sysCity : sysCities) {
            sysArea.setAreaName(sysCity.getCityName());
            sysArea.setAreaNo(sysCity.getCityId());
            sysArea.setpId(sysCity.getpId());
            sysArea.setLevelId(sysCity.getZone());
            areaMapper.insertArea(sysArea);
        }
    }
    @Transactional
    public void deleteAreaById(int areaNo){
        areaMapper.deleteAreaList(areaNo);
    }
}
