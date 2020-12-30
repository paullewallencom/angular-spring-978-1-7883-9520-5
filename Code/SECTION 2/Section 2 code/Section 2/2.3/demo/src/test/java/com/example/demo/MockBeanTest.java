package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Mock Bean Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest //Creates the ApplicationContext used in your SpringApplication.
public class MockBeanTest {

    @MockBean
    private MathService mathService;

    @Autowired
    private Calculator calculator;

    @Test
    public void mockService() {
        Mockito.when(mathService.sum(Mockito.anyInt(), Mockito.anyInt())).thenReturn(20);

        Assertions.assertEquals(calculator.sum(1, 1), 20);
    }
}
