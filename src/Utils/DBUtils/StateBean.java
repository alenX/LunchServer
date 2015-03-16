package Utils.DBUtils;

import java.io.Serializable;

/**
 * Created by wangss on 2015/3/16.
 * email:genhaoai@gmail.com
 */
public abstract class StateBean implements Serializable {
    private  int state= StateBean.STATE_NEW;//默认为新增
    protected static final int STATE_NEW = 1;
    protected static final int STATE_MODIFY = 2;
    protected static final int STATE_DELETE = 3;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
