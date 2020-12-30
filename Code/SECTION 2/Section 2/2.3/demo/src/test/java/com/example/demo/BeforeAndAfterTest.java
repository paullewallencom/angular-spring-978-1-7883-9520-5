package com.example.demo;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Before and After Test")
public class BeforeAndAfterTest {

    static Logger LOG = LoggerFactory.getLogger(BeforeAndAfterTest.class.getName());

    @BeforeAll
    static void tearUp() {
        LOG.info("@BeforeAll - Run the annotated method before all of the test");
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

    @AfterAll
    static void tearDown() {
        LOG.info("@AfterAll - Run the annotated method after all of the test");
    }
}
