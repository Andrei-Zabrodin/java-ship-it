package ru.yandex.practicum.delivery;

public abstract class Parcel {

    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    protected void packageItem() {
        System.out.printf("Посылка <<%s>> упакована\n", description);
    }

    protected void deliver() {
        System.out.printf("Посылка <<%s>> доставлена по адресу %s\n", description, deliveryAddress);
    }

    protected int calculateDeliveryCost() {
        return weight * getParcelBaseCost();
    }

    protected abstract int getParcelBaseCost();
}
