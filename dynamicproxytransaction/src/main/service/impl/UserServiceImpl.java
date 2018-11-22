package service.impl;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    // 这个和我以前写的不一样： 我都是写在每个方法中，写成属性应该好一点，减少创建对象的次数
    private UserDao userDao = null;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public User findUser(String id) throws SQLException {
        return userDao.selUser(id);
    }

}
