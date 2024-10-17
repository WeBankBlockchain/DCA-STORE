package com.webank.dca.store.db.mapper;

import com.webank.dca.store.model.WmComputeTask;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WmComputeTaskMapper {

    @Insert("insert into wm_compute_task (unique_id,app_id,watermark_file_hash)" +
            " values (#{item.uniqueId},#{item.appId},#{item.watermarkFileHash})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "pkId")
    int insert(@Param("item") WmComputeTask item);

    @Select("select count(1) from wm_compute_task where unique_id = #{uniqueId} and app_id = #{appId}")
    int existUniqueId(@Param("uniqueId")String uniqueId, @Param("appId")String appId);

    @Select("select pk_id from wm_compute_task where unique_id = #{uniqueId} and app_id = #{appId}")
    long queryByUniqueId(@Param("uniqueId")String uniqueId, @Param("appId")String appId);

    @Delete("delete from wm_compute_task where unique_id = #{uniqueId} and app_id = #{appId}")
    void delComputeTask(@Param("uniqueId")String uniqueId, @Param("appId")String appId);
}
