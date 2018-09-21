package annotation;

import java.lang.annotation.*;

/**
 * 通过注解创建对象
 *
 * @author water
 * @since 2018/9/20 17:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface AutoCreateObj {

}
