package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FragileParcelTest {

    @Test
    void shouldCalculateCostAs8() {
        FragileParcel fragileParcel = new FragileParcel("Хрупкая посылка 1", 2, "Адрес 1",
                1);
        int expectedCost = 8;

        int actualCost = fragileParcel.calculateDeliveryCost();

        assertEquals(expectedCost, actualCost);
    }

    @Test
    void shouldCalculateCostAs24() {
        FragileParcel fragileParcel = new FragileParcel("Хрупкая посылка 2", 6, "Адрес 2",
                3);
        int expectedCost = 24;

        int actualCost = fragileParcel.calculateDeliveryCost();

        assertEquals(expectedCost, actualCost);
    }
}