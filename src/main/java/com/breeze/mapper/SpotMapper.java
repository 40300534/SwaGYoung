package com.breeze.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotMapper {
    @Delete("DELETE  FROM spot WHERE id in(${ids})")
    void del(@Param("ids") String ids);
}
