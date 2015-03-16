package Utils.Exceptions;

/**
 * Created by wangss on 2015/3/16.
 * email:genhaoai@gmail.com
 */
public class DBException extends Exception {
    public DBException() {
        super();
    }

    public DBException(String msg){
        super(msg);
    }
}
