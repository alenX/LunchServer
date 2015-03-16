package annos;

import java.lang.annotation.*;

/**
 * Created by wangss on 2015/3/12.
 * email:genhaoai@gmail.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String name();
}