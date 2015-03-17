import Utils.DBUtils.UtislDao;
import beans.Order;
import com.example.myapp.Model.Ord;
import com.example.myapp.Model.Product;
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
        ArrayList<Product> arrayList = new ArrayList<Product>();
        while (true) {
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            //将Input转换为数组
            ByteArrayInputStream input = new ByteArrayInputStream(IOUtils.toByteArray(is));
            ObjectInputStream oos = new ObjectInputStream(input);
            Product ord = (Product) oos.readObject();
            arrayList.add(ord);
//            UtislDao<Order> utislDao = new UtislDao<Order>();
//            utislDao.delete(arrayList);
            if (arrayList.size() !=0) {   //TODO
                saveOrder(arrayList);
                arrayList.clear();
            }
            oos.close();
            s.close();
        }
    }

    public static void saveOrder(ArrayList<Product> arrayList) throws Exception {
        if (arrayList != null && arrayList.size() > 0) {
            for (Product order : arrayList) {
                UtislDao<Product> utislDao = new UtislDao<Product>();
                utislDao.save(order);
            }
        }
    }
}
