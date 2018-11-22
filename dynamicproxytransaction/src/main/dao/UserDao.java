package dao;

import bean.User;

import java.sql.SQLException;

public interface UserDao {
	public User selUser(String id ) throws SQLException ;
}