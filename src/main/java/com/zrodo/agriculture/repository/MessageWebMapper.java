package com.zrodo.agriculture.repository;

import com.zrodo.agriculture.entity.MessageWeb;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageWebMapper {

    public int addMessageWeb(MessageWeb messageWeb);

    public int deleteMessageWeb(Integer messageId);

    public int updateMessageWeb(MessageWeb messageWeb);

    public int modifyPublishStatus(@Param("messageId") Integer messageId,
                                   @Param("pustatus") Boolean pustatus);

    public int modifyShowStatus(@Param("messageId") Integer messageId,
                                @Param("isShow") Boolean isShow);

    public List<Map<String,Object>> queryMessageWebList(@Param("typeId") Integer typeId,
                                                        @Param("pustatus") Boolean pustatus,
                                                        @Param("isShow") Boolean isShow,
                                                        @Param("isSendWeiXin") Boolean isSendWeiXin,
                                                        @Param("startNo") Integer startNo,
                                                        @Param("pageSize") Integer pageSize);

    public Map<String,Object>queryMessageWebById(Integer messageId);
}
