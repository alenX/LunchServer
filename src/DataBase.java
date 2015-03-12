import java.sql.*;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
public class DataBase {
    private Connection conn = null;
    private final String url = "jdbc:mysql://127.0.0.1:3306/androidtest";
    private final String username = "root";
    private final String password = "password";
    int count = 0;
    private ResultSet resultSet = null;

    private PreparedStatement pstmt = null;

    public DataBase() {
        conn = connectionDB();
    }

    @SuppressWarnings("finally")
    public Connection connectionDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet query(String sql) {
        try {
            pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int update(String sql) {
        try {
            pstmt = conn.prepareStatement(sql);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean close(){
        boolean isClose = false;
        if (resultSet!=null){
            try {
                resultSet.close();
                resultSet=null;
                isClose = true;
            } catch (SQLException e){
                isClose = false;
                e.printStackTrace();
            }
        }

        if (pstmt!=null){
            try {
                pstmt.close();
                pstmt = null;
                isClose = true ;
            } catch (SQLException e){
                isClose = false;
                e.printStackTrace();
            }
        }

        if (conn !=null){
            try {
                conn.close();
                conn = null;
                isClose = true;

            }catch (SQLException e){
                isClose = false;
                e.printStackTrace();
            }
        }
        return isClose;
    }
}
