module hotel.reservation.service {
    requires spring.context;
    requires spring.beans;

    requires lombok;

    requires core;
    requires tax.payment.provider;

    exports ru.otus.timofeev.task5.hotelservice.service;
    exports ru.otus.timofeev.task5.hotelservice.entity;
    exports ru.otus.timofeev.task5.hotelservice.exception;

    opens ru.otus.timofeev.task5.hotelservice.service to spring.core, spring.beans, spring.context;
    opens ru.otus.timofeev.task5.hotelservice.repository to spring.core, spring.beans, spring.context;
}