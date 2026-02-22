package com.mclients.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mclients.dao.ClientDao;

import jakarta.annotation.Resource;


@Service("UserService")
@Transactional
public class UserService implements IUserService {
	
    @Resource  
    private ClientDao userDao;  

	public String getUserInfo(String username) {
		
		// TODO Auto-generated method stub
		return this.userDao.findById(1).get().getNom();
	}


}
