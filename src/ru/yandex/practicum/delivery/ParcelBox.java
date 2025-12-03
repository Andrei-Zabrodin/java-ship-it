package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    int maxWeight;
    int currentWeight = 0;

    List<T> parcelBox = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if(isCausingOverweight(parcel)) {
            System.out.println("Невозможно положить посылку, т.к. будет превышен максимальный вес коробки!");
        } else{
            parcelBox.add(parcel);
            currentWeight += parcel.weight;
        }
    }

    private boolean isCausingOverweight (T parcel) {
        int newWeight = currentWeight + parcel.weight;
        return newWeight > maxWeight;
    }

    public void getAllParcels() {
        System.out.println("В коробке есть следующие посылки:");
        for (T parcel: parcelBox) {
            System.out.println(parcel.description);
        }
        System.out.println("");
    }
}
