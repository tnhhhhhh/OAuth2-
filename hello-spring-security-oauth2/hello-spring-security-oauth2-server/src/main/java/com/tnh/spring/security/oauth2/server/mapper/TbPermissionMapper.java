package com.tnh.spring.security.oauth2.server.mapper;

import com.tnh.spring.security.oauth2.server.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.MyMapper;

import java.util.List;

public interface TbPermissionMapper extends MyMapper<TbPermission> {
    @Select(" SELECT\n" +
            "      p.*\n" +
            "    FROM\n" +
            "      tb_user AS u\n" +
            "      LEFT JOIN tb_user_role AS ur\n" +
            "        ON u.id = ur.user_id\n" +
            "      LEFT JOIN tb_role AS r\n" +
            "        ON r.id = ur.role_id\n" +
            "      LEFT JOIN tb_role_permission AS rp\n" +
            "        ON r.id = rp.role_id\n" +
            "      LEFT JOIN tb_permission AS p\n" +
            "        ON p.id = rp.permission_id\n" +
            "    WHERE u.id = #{id}")
    List<TbPermission> selectByUserId(Long id);
}