package com.irs.mapper;

import com.irs.pojo.TbMenus;
import com.irs.pojo.TbMenusExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbMenusMapper {
    int countByExample(TbMenusExample example);

    int deleteByExample(TbMenusExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(TbMenus record);

    int insertSelective(TbMenus record);

    List<TbMenus> selectByExample(TbMenusExample example);

    TbMenus selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") TbMenus record, @Param("example") TbMenusExample example);

    int updateByExample(@Param("record") TbMenus record, @Param("example") TbMenusExample example);

    int updateByPrimaryKeySelective(TbMenus record);

    int updateByPrimaryKey(TbMenus record);
}