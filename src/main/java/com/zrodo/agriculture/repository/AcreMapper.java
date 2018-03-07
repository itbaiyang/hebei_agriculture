package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.Acre;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AcreMapper {

    public int insertAcre(Acre acre);

    public void insertAcreImg(@Param("acreId") Integer acreId,
                              @Param("url") String url);

    public void deleteAcreImg(@Param("id") Integer id);

    public int updateAcre(Acre acre);

    public void deleteAcre(@Param("acreId") int acreId);

    public List<Map<String, Object>> queryAcreList(@Param("startDate") String startDate,
                                                   @Param("endDate") String endDate,
                                                   @Param("startNo") Integer startNo,
                                                   @Param("pageSize") Integer pageSize,
                                                   @Param("companyId") String companyId,
                                                   @Param("acreName") String acreName,
                                                   @Param("typeId") Integer typeId);

    public Integer queryAcreListCount(@Param("startDate") String startDate,
                                      @Param("endDate") String endDate,
                                      @Param("companyId") String companyId,
                                      @Param("acreName") String acreName,
                                      @Param("typeId") Integer typeId);

    public Map<String, Object> queryAcreById(@Param("acreId") int acreId);

    public List<Map<String, Object>> queryAcreImgList(@Param("acreId") int acreId);

    public List<Map<String, Object>> queryAcreTypeList();
}