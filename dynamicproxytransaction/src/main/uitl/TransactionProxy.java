package uitl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class TransactionProxy implements InvocationHandler {
    private Object obj = null;

    // obj:需要代理的类
    public Object newProxyInstance(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(this.obj.getClass().getClassLoader(),
                this.obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // 用于接收参数
        Object param = null;
        // 如果是以下方法开头,则代理事务
        if (method.getName().startsWith("add")
                || method.getName().startsWith("modify")
                || method.getName().startsWith("find")
                || method.getName().startsWith("del")) {
            Connection conn = ConnectionManager.getConnection();
            try {
                // 手动提交事务
                ConnectionManager.benigTransction(conn);
                param = method.invoke(obj, args);
                // 提交事务
                ConnectionManager.endTransction(conn);
            } catch (Exception e) {
                // 回滚事务
                ConnectionManager.rollback(conn);
                if (e instanceof InvocationTargetException) {
                    InvocationTargetException inv = (InvocationTargetException) e;
                    throw inv.getTargetException();
                } else {
                    throw new Exception("操作失败!");
                }
            } finally {
                // 还原状态
                ConnectionManager.recoverTransction(conn);
                ConnectionManager.close();
            }
        }
        return param;
    }
}
