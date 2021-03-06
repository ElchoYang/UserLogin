package com.ibm.mvc.dao.impl;

import java.io.Serializable;

import java.util.List;

import com.ibm.mvc.entity.User;
import com.ibm.mvc.dao.IUserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/******
 * 持久层的实现类，采用 Spring提供的JdbcTemplate 模板类来实现
 */
@Repository    //功能同 @Component,只是语义上的加强
public class UserDaoImpl implements IUserDao{
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    @Autowired  //自动注入
    private JdbcTemplate template;

    @Override
	public void save(User user) {
		// TODO Auto-generated method stub
		if( user !=null) {
			String sql = "insert into TBL_USER(name,role,email, USERNAME,PASSWORD) values(?,?,?,?,?)";
			template.update(sql,user.getName(), "user",user.getEmail(),user.getUserName(),user.getPassword());
		}
	}

	@Override
	public User findById(Serializable id) {
		// TODO Auto-generated method stub
		if(id != null) {
			//
			String sql = "select id,name,role,email,USERNAME,PASSWORD from TBL_USER where id = ?";
			//准备一个把结果集装配到实体类对象的 对象
			BeanPropertyRowMapper<User> bprm = new BeanPropertyRowMapper<>(User.class);
			//
			return template.queryForObject(sql, bprm, id);
		}

		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		//
		String sql = "select id,name,role,email,USERNAME,PASSWORD from TBL_USER";
		//准备一个把结果集装配到实体类对象的 对象
		BeanPropertyRowMapper<User> bprm = new BeanPropertyRowMapper<>(User.class);
		//
		return template.query(sql, bprm);
	}

	@Override
	public void update(User user, Integer id) {
		// TODO Auto-generated method stub
		if(user != null) {
			//
			String sql = "update TBL_USER set name=?,role=?,email=?,USERNAME=?,PASSWORD=? where id = ?";
			//
			template.update(sql,user.getName(),user.getRole(),user.getEmail(),user.getUserName(),user.getPassword(),id);
		}
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		//
		if(id != null) {
			String sql = "delete from TBL_USER where id = ?";
			//          //
			template.update(sql, id);
		}
	}

	@Override
	public User queryByAccountId(Serializable accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User queryByUserName(String userName) {
		// TODO Auto-generated method stub
		User user  = null;
		if(userName != null) {
			//
			try{
				String sql = "select id,name,role,USERNAME,PASSWORD from TBL_USER where USERNAME = ?";
				//准备一个把结果集装配到实体类对象的 对象
				BeanPropertyRowMapper<User> bprm = new BeanPropertyRowMapper<>(User.class);
				//
				user = template.queryForObject(sql, bprm, userName);
			}
			catch (Exception e){
				log.info("error: ");
			}
		}

		return user;
	}

	
}
