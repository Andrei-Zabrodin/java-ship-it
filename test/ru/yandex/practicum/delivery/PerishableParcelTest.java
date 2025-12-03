package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerishableParcelTest {

    @Test
    void shouldBeExpiredWhenDateEqualsExpirationDate() {
        PerishableParcel parcel = new PerishableParcel("Посылка 1", 15, "Адрес 1",
                4, 5);

        boolean actualResult = parcel.isExpired(9);

        assertTrue(actualResult);
    }

    @Test
    void shouldBeExpiredWhenDateIsAfterExpirationDate() {
        PerishableParcel parcel = new PerishableParcel("Посылка 1", 15, "Адрес 1",
                4, 1);

        boolean actualResult = parcel.isExpired(9);

        assertFalse(actualResult);
    }

    @Test
    void shouldNotBeExpiredWhenDateIsBeforeExpirationDate() {
        PerishableParcel parcel = new PerishableParcel("Посылка 1", 15, "Адрес 1",
                4, 2);

        boolean actualResult = parcel.isExpired(9);

        assertFalse(actualResult);
    }

    @Test
    void shouldCalculateCostAs9() {
        PerishableParcel perishableParcel = new PerishableParcel("Скоропортящаяся посылка 1", 3,
                "Адрес 1", 2, 5);
        int expectedCost = 9;

        int actualCost = perishableParcel.calculateDeliveryCost();

        assertEquals(expectedCost, actualCost);
    }

    @Test
    void shouldCalculateCostAs30() {
        PerishableParcel perishableParcel = new PerishableParcel("Скоропортящаяся посылка 2", 10,
                "Адрес 2", 5, 12);
        int expectedCost = 30;

        int actualCost = perishableParcel.calculateDeliveryCost();

        assertEquals(expectedCost, actualCost);
    }
}