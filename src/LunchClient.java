import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
public class LunchClient {
    public static void main(String[] args) throws IOException {
        Order order = new Order("address", "telephone_number", new BigDecimal(100));

        Socket s = new Socket("10.14.11.7", 30000);
        OutputStream ops = s.getOutputStream()  ;
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
