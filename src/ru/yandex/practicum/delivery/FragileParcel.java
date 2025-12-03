package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {

    private static final int PARCEL_BASE_COST = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected void packageItem() {
        System.out.printf("Посылка <<%s>> обёрнута в защитную плёнку\n", description);
        System.out.printf("Посылка <<%s>> упакована\n", description);
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.printf("Хрупкая посылка <<%s>> изменила местоположение на %s\n", description, newLocation);
    }

    @Override
    protected int getParcelBaseCost() {
        return PARCEL_BASE_COST;
    }
}
