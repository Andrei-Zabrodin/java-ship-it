package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {

    ParcelBox<StandardParcel> box;
    StandardParcel parcel;

    @BeforeEach
    void setUp() {//создаём коробку и добавляем одну посылку весом 10
        box = new ParcelBox<>(15); //макс вместимость коробки — 15
        parcel = new StandardParcel("Посылка 1", 10, "Адрес 1", 4);
        box.addParcel(parcel);
    }

    @Test
    void shouldAddParcelIntoBoxWhenTotalWeightIsBelowLimit() {//добавляем посылку с весом 3 (вместимость не превышена)
        StandardParcel standardParcel = new StandardParcel("Посылка 2", 3, "Адрес 2",
                4);
        box.addParcel(standardParcel);

        //теперь в коробке должно быть две посылки
        boolean actualResult = box.parcelBox.contains(standardParcel) && box.parcelBox.contains(parcel);

        assertTrue(actualResult);
    }

    @Test
    void shouldAddParcelIntoBoxWhenTotalWeightEqualsToLimit() {//добавляем посылку с весом 5 (вместимость не превышена)
        StandardParcel standardParcel = new StandardParcel("Посылка 3", 5, "Адрес 2",
                4);
        box.addParcel(standardParcel);

        //теперь в коробке должно быть две посылки
        boolean actualResult = box.parcelBox.contains(standardParcel) && box.parcelBox.contains(parcel);

        assertTrue(actualResult);
    }

    @Test
    void shouldNotAddParcelIntoBoxWhenTotalWeightIsOverLimit() {//добавляем посылку с весом 6 (вместимость превышена)
        StandardParcel standardParcel = new StandardParcel("Посылка 3", 6, "Адрес 2",
                4);
        box.addParcel(standardParcel);

        boolean actualResult = box.parcelBox.contains(standardParcel);

        assertFalse(actualResult);
    }
}