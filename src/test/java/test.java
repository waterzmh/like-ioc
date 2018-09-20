import org.junit.Test;
import util.BeanBuilder;

/**
 * @author mati
 * @since 2018/9/18 16:13
 */
public class test {
    @Test
    public void test1() throws Exception {
        BeanBuilder.create();
        BeanBuilder.listObj();
    }
    @Test
    public void test2() {
        String f = test.class.getClassLoader().getResource("beans-demo.xml").getPath();
        System.out.println(f);
    }
}
