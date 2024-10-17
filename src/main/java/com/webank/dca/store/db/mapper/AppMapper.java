package com.webank.dca.store.db.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuzhichu
 */
@Component
public interface AppMapper {
    @Select("SELECT capacity FROM `app_info` where app_id = #{appId}")
    int getCapacityFromAppInfo(@Param("appId")String appId);

    @Select("SELECT app_id FROM `app_info`")
    List<String> getAppIds();
}
