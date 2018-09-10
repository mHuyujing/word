package org.hyj.dao;

import org.hyj.bean.Admin;

public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);
}