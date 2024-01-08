package com.testcafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @Test
    public void getName() {
        // given
        Americano americano = new Americano();

        // when
        // then
//        assertEquals("아메리카노", americano.getName());
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    public void getPrice() {
        // given
        Americano americano = new Americano();

        // when
        // then
        assertThat(americano.getPrice()).isEqualTo((4000));
    }
}