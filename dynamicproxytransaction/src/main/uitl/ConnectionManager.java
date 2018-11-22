package uitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private ConnectionManager() {
    }

    private static ThreadLocal<Connection> threadConn = new ThreadLocal<Connection>();

    // 获取数据库连接
    public static Connection getConnection() {
        Connection conn = threadConn.get();
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/redsun", "root", "123456");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadConn.set(conn);
        }
        return conn;
    }

    // 设置事务手动提交
    public static void benigTransction(Connection conn) {
        try {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 提交事务
    public static void endTransction(Connection conn) {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 设置Connection的原始状态
    public static void recoverTransction(Connection conn) {
        try {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                } else {
                    conn.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 发生异常回滚事务
    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭连接,并将其从当前线程删除
    public static void close() {
        Connection conn = threadConn.get();
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                threadConn.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}