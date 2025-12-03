package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>(); //список для всех посылок
    private static List<Trackable> trackableParcels = new ArrayList<>(); //список только для посылок с трек-номером

    //создаём коробки для каждого типа посылок
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(24);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(10);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(5);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    getLocation();
                    break;
                case 5:
                    showBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Узнать местоположение посылки (только для посылок с трек-номером)");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();

        System.out.println("Введите вес посылки:");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправки посылки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная посылка");
        System.out.println("2 — Хрупкая посылка");
        System.out.println("3 — Скоропортящаяся посылка");
        int parcelType = Integer.parseInt(scanner.nextLine());

        switch (parcelType) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                standardBox.addParcel(standardParcel);
                System.out.printf("Добавлена стандартная посылка <<%s>> c весом %d и адресом доставки %s." +
                        " День отправки: %d\n\n", description, weight, deliveryAddress, sendDay);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileBox.addParcel(fragileParcel);
                System.out.printf("Добавлена хрупкая посылка <<%s>> c весом %d и адресом доставки %s." +
                        "День отправки: %d\n\n", description, weight, deliveryAddress, sendDay);
                break;
            case 3:
                System.out.println("Введите день срок годности посылки:");
                int timeToLive = Integer.parseInt(scanner.nextLine());

                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay,
                        timeToLive);
                allParcels.add(perishableParcel);
                perishableBox.addParcel(perishableParcel);
                System.out.printf("Добавлена скоропортящаяся посылка <<%s>> c весом %d и адресом доставки %s." +
                                " День отправки: %d, срок годности: %d\n\n", description, weight, deliveryAddress,
                        sendDay, timeToLive);
                break;
            default:
                System.out.println("Такого типа посылок пока нет.\n");
        }
    }

    private static void sendParcels() {
        for(Parcel parcel: allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int totalCost = 0;

        for(Parcel parcel: allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }

        System.out.println("Общая стоимость всех доставок: " + totalCost + "\n");
    }

    private static void getLocation() {
        String description;

        for(Trackable parcel: trackableParcels) {
            if (parcel instanceof FragileParcel) {
                description = ((FragileParcel) parcel).description;
            } else {
                description = "Неизвестная посылка";
            }

            System.out.printf("Введите местоположение посылки <<%s>>:\n", description);
            String newLocation = scanner.nextLine();
            parcel.reportStatus(newLocation);
        }
    }

    private static void showBox() {
        System.out.println("Выберите тип коробки: ");
        System.out.println("1 — Коробка с обычными посылками");
        System.out.println("2 — Коробка с хрупкими посылками");
        System.out.println("3 — Коробка со скоропортящимися посылками");
        int boxType = Integer.parseInt(scanner.nextLine());

        switch (boxType) {
            case 1:
                standardBox.getAllParcels();
                break;
            case 2:
                fragileBox.getAllParcels();
                break;
            case 3:
                perishableBox.getAllParcels();
                break;
            default:
                System.out.println("Такой коробки нет.");
        }
    }
}
