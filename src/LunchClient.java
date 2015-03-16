import beans.Order;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
public class LunchClient {
    public static void main(String[] args) throws IOException {
        Order order = new Order("地址位置1", "13805311008", new BigDecimal(100));
        order.setId("222");
        Socket s = new Socket("ip", 30000);
        OutputStream ops = s.getOutputStream();
        /*OutputStream ops = s.getOutputStream();
        ops.write("dd@测试".getBytes("UTF-8"));
        ops.close();*/
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(order);
        ops.write(out.toByteArray());
        s.close();
    }
}
