import model.Car;
import org.junit.Test;
import service.TestService;
import util.BeanBuilder;
import util.ClassResourceScanner;

/**
 * @author water
 * @since 2018/9/18 16:13
 */
public class test {
    @Test
    public void test1() throws Exception {
        BeanBuilder.createAnotationObj();
        BeanBuilder.listObj();
    }

    @Test
    public void test2() {

    }

    @Test
    public void test3() {
        String sPackageName = Car.class.getPackage().getName();
        System.out.println(sPackageName);
    }

    // 用注解创建对象并赋予初始值
    @Test
    public void test4() throws Exception {
        // 先创建管理bean的容器
        BeanBuilder.createAnotationObj();
        // 创建对象
        TestService testService = new TestService();
        ClassResourceScanner.initObj(testService);
        testService.printObj();
    }
}
