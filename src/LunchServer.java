import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
public class LunchServer {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        ServerSocket ss = new ServerSocket(30000);
        while (true) {
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            //将Input转换为数组
            ByteArrayInputStream input = new ByteArrayInputStream(IOUtils.toByteArray(is));
            ObjectInputStream oos = new ObjectInputStream(input);
            Order order = (Order) oos.readObject();
            System.out.println(order.getAddress());
            oos.close();
            s.close();
        }
    }
}
