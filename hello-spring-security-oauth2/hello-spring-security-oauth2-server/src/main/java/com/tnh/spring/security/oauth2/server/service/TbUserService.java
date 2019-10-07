package com.tnh.spring.security.oauth2.server.service;

import com.tnh.spring.security.oauth2.server.domain.TbUser;

public interface TbUserService{

    TbUser getTbUserByName(String username);
}
