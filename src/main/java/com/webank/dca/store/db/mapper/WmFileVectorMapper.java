package com.webank.dca.store.db.mapper;

import com.webank.dca.store.model.WmFileVector;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WmFileVectorMapper {

    @Insert("        insert into wm_file_vector_#{selectTable} (unique_id, feature, algorithm_type, unique_hash, app_id) " +
            "        values (" +
            "        #{item.uniqueId}, #{item.feature}, #{item.algorithmType}, #{item.uniqueHash}, #{item.appId} " +
            "        )")
    void insert(@Param("item") WmFileVector item, @Param("selectTable") int selectTable);


    @Select("<script>" +
            "<foreach collection='tables' separator='union all' item='table'>" +
            " select feature,unique_id from wm_file_vector_#{table} where unique_hash= #{uniqueHash} and algorithm_type = #{algorithmType}" +
            "</foreach> " +
            " </script>")
    @Results({
            @Result(column = "feature", property = "feature"),
            @Result(column = "unique_id", property = "uniqueId")
    })
    WmFileVector getVectorByHash(@Param("tables")List<Integer> tables,
                                 @Param("uniqueHash") String hash,
                                 @Param("algorithmType") int algorithmType);

    @Select("<script>" +
            "<foreach collection='tables' separator='union all' item='table'>" +
            " select feature,unique_id from wm_file_vector_#{table} where unique_id= #{uniqueId} and algorithm_type = #{algorithmType}" +
            "</foreach> " +
            " </script>")
    @Results({
            @Result(column = "feature", property = "feature"),
            @Result(column = "unique_id", property = "uniqueId")
    })
    WmFileVector getVectorByUniqueId(@Param("tables")List<Integer> tables,
                                 @Param("uniqueId") String uniqueId,
                                 @Param("algorithmType") int algorithmType);


    @Delete("delete from wm_file_vector_#{table} where unique_id = #{uniqueId} and app_id = #{appId}")
    void delVector(@Param("uniqueId")String uniqueId, @Param("appId")String appId, @Param("table") int table);

    @Select("select unique_hash from wm_file_vector_#{table} where unique_id = #{uniqueId} and app_id = #{appId}")
    String queryHashByUniqueId(String uniqueId, String appId, int table);

    @Select("select unique_hash from wm_file_vector_#{table} where app_id = #{appId}")
    List<String> queryUniqueHashByAppIdAndTable(String appId, int table);
}
