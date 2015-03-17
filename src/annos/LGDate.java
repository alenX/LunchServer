package annos;

import java.lang.annotation.*;

/**
 * Created by wangss on 2015/3/17.
 * email:genhaoai@gmail.com
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LGDate {
    String readFormat()default "yyyy-MM-dd";
    String writeFormat() default "yyyyMMdd";
}