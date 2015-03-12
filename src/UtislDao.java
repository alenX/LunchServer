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
            columnSql.append(columnName + ",");
            valueSql.append("'" + readMethod.invoke(t) + "',");
        }
        DataBase dataBase = new DataBase();
        dataBase.update(insertSql + tableName + columnSql.subSequence(0, columnSql.length() - 1) + ")" + valueSql.subSequence(0, valueSql.length() - 1) + ")");
    }
}