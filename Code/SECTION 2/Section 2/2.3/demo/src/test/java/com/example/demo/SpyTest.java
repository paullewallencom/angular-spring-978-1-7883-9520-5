package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Spy Bean Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest //Creates the ApplicationContext used in your SpringApplication.
public class SpyTest {

    @SpyBean
    private MathService mathService;

    @Autowired
    private Calculator calculator;

    @Test
    public void spyService() {
        calculator.sum(1, 1);
        Mockito.verify(mathService).sum(1, 1);
//        Mockito.verify(mathService).mul(Mockito.anyInt(), Mockito.anyInt());
    }
}
