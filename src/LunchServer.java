import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
public class LunchServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(30000);
        ArrayList<Order> arrayList = new ArrayList<Order>();
        while (true) {
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            //将Input转换为数组
            ByteArrayInputStream input = new ByteArrayInputStream(IOUtils.toByteArray(is));
            ObjectInputStream oos = new ObjectInputStream(input);
            Order order = (Order) oos.readObject();
            arrayList.add(order);
            if (arrayList.size() >= 2) {   //TODO
                saveOrder(arrayList);
                arrayList.clear();
            }
            oos.close();
            s.close();
        }
    }

    public static void saveOrder(ArrayList<Order> arrayList) throws Exception {
        if (arrayList != null && arrayList.size() > 0) {
            for (Order order : arrayList) {
                UtislDao<Order> utislDao = new UtislDao<Order>();
                utislDao.save(order);
            }
        }
    }
}
