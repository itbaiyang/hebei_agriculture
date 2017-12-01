package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.SysCompany;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CompanyMapper {
    public int addCompany(SysCompany sysCompany);

    public int deleteCompany(Integer companyId);

    public int updateCompany(SysCompany sysCompany);

    public List<Map<String,Object>> queryCompanyList(@Param("areaId") Integer areaId,
                                                     @Param("deptId") Integer deptId,
                                                     @Param("startNo") Integer startNo,
                                                     @Param("pageSize") Integer pageSize);
    public List<Map<String,Object>> queryCompanyListByDept(@Param("areaId") Integer areaId,
                                                           @Param("productType") Integer productType);
    public Map<String,Object>queryCompanyById(Integer companyId);

    public int auditCompany(@Param("companyId") Integer companyId,
                            @Param("auditing") Integer auditing);
}
