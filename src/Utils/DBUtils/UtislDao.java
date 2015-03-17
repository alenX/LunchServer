package Utils.DBUtils;

import Utils.Exceptions.DBException;
import annos.Column;
import annos.Table;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
public class UtislDao<T> {
    private DataBase dataBase = new DataBase();
    public UtislDao() {
    }

    public void save(T t) throws Exception {

        if (!(t instanceof StateBean)) {
            throw new DBException("错误的实例!");
        }
        StateBean sb = (StateBean) t;

        switch (sb.getState()) {
            case StateBean.STATE_NEW: //新增的直接插入
                break;
            case StateBean.STATE_MODIFY://修改的只针对有主键的（唯一或者组合）
                break;
            case StateBean.STATE_DELETE://删除同修改
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
            if (value == null||"".equals(value)) {//说明没有数值,Bean里没有数值的不处理
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

        dataBase.update(insertSql + tableName + columnSql.subSequence(0, columnSql.length() - 1) + ")" + valueSql.subSequence(0, valueSql.length() - 1) + ")");
        dataBase.close();
    }

    public int update(String updateSql, Object... object) {
        //替换updateSql中的占位符
        return 0;
    }
    /*删除主键ID数据*/
    public int delete(ArrayList<T> arrayList) throws Exception {
        if (arrayList == null || arrayList.size() < 1) {
            return 0;
        }
        String[] deleteIds = new String[arrayList.size()];//删除的主键数组
        String tableName = arrayList.get(0).getClass().getAnnotation(Table.class).name(); //表名
        String keyField = arrayList.get(0).getClass().getAnnotation(Table.class).keyField();
        if (keyField == null) {
            throw new DBException("Bean中没有定义主键！");
        }
        int i = 0;
        for (T t : arrayList) {
            if (!(t instanceof StateBean)) {
                throw new DBException("删除实例错误！");
            }
            //目前只支持唯一主键
            Class cls = t.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                Column cl = field.getAnnotation(Column.class);
                if (cl == null)
                    continue;
                String columnName = cl.columnName();
                if (columnName != null && !"".equals(columnName)) {
                    if (keyField.equals(columnName)) {
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
                                cls);
                        Method writeMethod = pd.getWriteMethod();//写方法
                        Method readMethod = pd.getReadMethod();//读方法
                        deleteIds[i] = (String) readMethod.invoke(t);
                        i++;
                    }
                }
            }

        }
        String conditionStr = "( '";
        for (String str:deleteIds){
            conditionStr+=str+"'," ;
        }
        conditionStr  =  conditionStr.substring(0,conditionStr.length()-1);
        conditionStr+=")" ;
        String deleteSql = "DELETE FROM "+tableName+" WHERE "+keyField+"  IN " +conditionStr  ;
        return dataBase.update(deleteSql);
    }
}