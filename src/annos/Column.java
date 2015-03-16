package annos;

import java.lang.annotation.*;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String columnName();
}