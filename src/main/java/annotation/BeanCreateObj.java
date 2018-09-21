package annotation;

import java.lang.annotation.*;

/**
 * 通过xml中的bean创建对象
 *
 * @author water
 * @since 2018/9/20 17:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface BeanCreateObj {

}
