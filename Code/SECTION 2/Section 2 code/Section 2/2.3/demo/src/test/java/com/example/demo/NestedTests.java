package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Nested Level 1")
class NestedTests {

    @Nested
    @DisplayName("Nested Level 2")
    class InnerTests {

        @Test
        void testOne() {
        }
    }

    @Test
    void testOne() {
    }

    @Test
    void testTwo() {
    }

}
