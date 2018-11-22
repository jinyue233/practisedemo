package test;

import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;
import uitl.TransactionProxy;

/**
 * 手动用动态代理实现数据库事务的AOP编程
 * 1，编写一个TransactionProxy类，用于代理数据库业务逻辑（增删改查）类，同时
 * 实现InvocationHandler接口并实现invoke方法，并在invoke方法里执行目标方法的前后添加
 * 开始事务，提交事务等逻辑。
 * 2，在测试时产生代理类时传入目标代理类对象UserServiceImpl
 *
 */
public class Test {
    public static void main(String[] args) throws Exception {
        TransactionProxy transctionProxy = new TransactionProxy();

        // //产生代理对象
        UserService userService = (UserService) transctionProxy
                .newProxyInstance(new UserServiceImpl());
        User user = userService.findUser("root");
        System.out.println("用户名:" + user.getName());
    }

}
