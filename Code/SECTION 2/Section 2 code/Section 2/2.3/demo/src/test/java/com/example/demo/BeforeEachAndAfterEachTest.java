package com.example.demo;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Before and After Each")
public class BeforeEachAndAfterEachTest {

    static Logger LOG = LoggerFactory.getLogger(BeforeEachAndAfterEachTest.class.getName());

    @BeforeEach
    void tearUp() {
        LOG.info("@BeforeEach - Run the annotated method before each test");
    }

    @Test
    @DisplayName("Test One")
    void firstTest() {
        LOG.info("First Test");
        assertEquals(2, 2);
    }

    @Test
    @DisplayName("Test Two")
    void secondTest() {
        LOG.info("Second Test");
        assertEquals(2, 2);
    }

    @AfterEach
    void tearDown() {
        LOG.info("@AfterEach - Run the annotated method after each test");
    }
}
