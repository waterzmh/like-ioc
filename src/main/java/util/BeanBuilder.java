package util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mati
 * @since 2018/9/20 11:10
 */
public class BeanBuilder {

    private static Map<String, Object> objectMap = new HashMap<>();

    public static void create() throws Exception {
        loadXml();
    }

    private static void loadXml() throws Exception {
        SAXReader reader = new SAXReader();
        String url = BeanBuilder.class.getClassLoader().getResource("beans-demo.xml").getFile();
        File file = new File(url);
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        loadAllBeans(root);
    }

    private static void loadAllBeans(Element rootElement) throws Exception {
        List<Element> beanElements = rootElement.elements("bean");
        if (beanElements == null) {
            return;
        }
        for (Element beanElement : beanElements) {
            if (objectMap.get(beanElement.attributeValue("id")) != null) {
                doThrow(new Exception("bean id不能重复"));
            }
            Class objectClass = null;
            try {
                objectClass = Class.forName(beanElement.attributeValue("class"));
            } catch (ClassNotFoundException e1) {
                doThrow(new Exception("无法创建" + beanElement.getStringValue() + "请检查类全名是否正确"));
            }
            Object object = objectClass.newInstance();
            List<Element> propertyElements = beanElement.elements("property");
            if (propertyElements == null) {
                continue;
            }
            for (Element propertyElement : propertyElements) {
                String propertyName = propertyElement.attributeValue("name");
                Field field = objectClass.getDeclaredField(propertyName);
                if (field == null) {
                    doThrow(new Exception("bean的属性名不对"));
                }
                // 如果基本类型传null 会出错,所以要判断
                if (propertyElement.attributeValue("value") != null) {
                    // 反射在操作属性时取消访问检查
                    field.setAccessible(true);
                    field.set(object, propertyElement.attributeValue("value"));
                }
            }

            objectMap.put(beanElement.attributeValue("id"), object);
        }
    }

    /**
     * 列出所有的bean
     */
    public static void listObj() throws IllegalAccessException {
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            System.out.println(entry.getKey());
            Object object = entry.getValue();
            Class objClass = object.getClass();
            Field[] fields = objClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.print(field.getName() + "  " + field.get(object));
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * 抛出非受检是不需要显示处理的，我们可以在forEach中把受检异常包装成非受检异常再抛出
     */
    private static <E extends Exception> void doThrow(Exception e) throws E {
        throw (E) e;
    }

}
