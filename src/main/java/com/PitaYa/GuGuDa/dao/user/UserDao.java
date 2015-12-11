package com.PitaYa.GuGuDa.dao.user;

import org.apache.ibatis.annotations.Param;

import com.PitaYa.model.user.User;

/**
 * 查询User表格相关数据
 * @author boli
 *
 */
public interface UserDao {
	
	User getUser(@Param("ID")Integer ID);
	
	Integer insert(User user);
	
}
