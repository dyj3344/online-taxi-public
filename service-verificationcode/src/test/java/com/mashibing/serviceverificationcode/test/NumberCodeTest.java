package com.mashibing.serviceverificationcode.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberCodeTest {


    @Test
    public void test(){
        double random=(Math.random()*9+1)*Math.pow(10,5);
        System.out.println(random);
        int result = (int) random;
        System.out.println(result);
    }

}
