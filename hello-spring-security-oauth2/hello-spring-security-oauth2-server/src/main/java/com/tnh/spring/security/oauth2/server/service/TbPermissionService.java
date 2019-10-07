package com.tnh.spring.security.oauth2.server.service;

import com.tnh.spring.security.oauth2.server.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbPermissionService{
    List<TbPermission> selectByUserId(Long id);

}
