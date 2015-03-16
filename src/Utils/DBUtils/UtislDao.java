package Utils.DBUtils;

import Utils.Exceptions.DBException;
import annos.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
public class UtislDao<T> {

    public UtislDao() {
    }

    public void save(T t) throws Exception {

        if(!(t instanceof StateBean)){
          throw new DBException("错误的实例!");
        }
        StateBean sb = (StateBean) t;

        switch (sb.getState()) {
            case StateBean.STATE_NEW:
                break;
            case StateBean.STATE_MODIFY:
                break;
            case StateBean.STATE_DELETE:
                break;
            default:
                break;
        }
        Class cls = t.getClass();
        //1.获取表名
        String tableName = t.getClass().getAnnotation(Table.class).name();
        //2.获取Column字段
        Field[] fields = cls.getDeclaredFields();
        StringBuffer insertSql = new StringBuffer("INSERT INTO ");
        StringBuffer columnSql = new StringBuffer(" (");
        StringBuffer valueSql = new StringBuffer(" VALUES ( ");
        for (Field field : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
                    cls);
            Method writeMethod = pd.getWriteMethod();//写方法
            Method readMethod = pd.getReadMethod();//读方法
            // 3.获取Write方法，得到该对象的某一属性的值
            Column cl = field.getAnnotation(Column.class);
            String columnName = cl.columnName();//字段名称
            Object value = readMethod.invoke(t);
            annos.UUID uuid = field.getAnnotation(annos.UUID.class);
            if (value == null) {//说明没有数值,Bean里没有数值的不处理
                if (uuid != null && uuid.isUUID()) {  //如果是UUID那么进行处理
                    columnSql.append(columnName + ",");
                    String strUUID = java.util.UUID.randomUUID().toString().toUpperCase().replaceAll("\\-", "").substring(0, 31);
                    valueSql.append("'" + strUUID + "',");
                }
            } else {
                columnSql.append(columnName + ",");
                valueSql.append("'" + readMethod.invoke(t) + "',");
            }
        }
        DataBase dataBase = new DataBase();
        dataBase.update(insertSql + tableName + columnSql.subSequence(0, columnSql.length() - 1) + ")" + valueSql.subSequence(0, valueSql.length() - 1) + ")");
        dataBase.close();
    }
}