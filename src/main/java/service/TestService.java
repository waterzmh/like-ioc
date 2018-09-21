package service;

import annotation.AutoCreateObj;
import annotation.BeanCreateObj;
import model.Car;

/**
 * @author mati
 * @since 2018/9/20 18:03
 */
public class TestService {
    @AutoCreateObj
    private Car benz;
    @BeanCreateObj
    private Car bmw;

    public void printObj() {
        System.out.println(benz.getName());
        System.out.println(benz.getHeight() == null);
        System.out.println(bmw.getName());
        System.out.println(bmw.getHeight());
    }
}
