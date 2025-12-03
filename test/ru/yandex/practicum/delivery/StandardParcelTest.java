package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardParcelTest {

    @Test
    void shouldCalculateCostAs18() {
        StandardParcel standardParcel = new StandardParcel("Обычная посылка 1", 9,
                "Адрес 1", 5);
        int expectedCost = 18;

        int actualCost = standardParcel.calculateDeliveryCost();

        assertEquals(expectedCost, actualCost);
    }

    @Test
    void shouldCalculateCostAs28() {
        StandardParcel standardParcel = new StandardParcel("Обычная посылка 2", 14,
                "Адрес 2", 25);
        int expectedCost = 28;

        int actualCost = standardParcel.calculateDeliveryCost();

        assertEquals(expectedCost, actualCost);
    }
}