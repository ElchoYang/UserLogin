package com.ibm.mvc.service.impl;

import java.util.List;

import com.ibm.mvc.dao.IUserDao;
import com.ibm.mvc.entity.User;
import com.ibm.mvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl  implements IUserService{

	@Autowired  //自动注入
	private IUserDao userDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED,
			isolation = Isolation.READ_COMMITTED,
			rollbackFor = {RuntimeException.class})
	public void add(User u) {
		// TODO Auto-generated method stub
		userDao.save(u);
		//
		System.out.println("--- save finish ------");
	}

	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public User findUserByUserName(String username) {
		return userDao.queryByUserName(username);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public void update(User u) {
		userDao.update(u);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}
}
