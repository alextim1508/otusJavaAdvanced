module api {
    requires spring.boot;
    requires spring.context;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.aop;
    requires spring.core;

    requires jakarta.validation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    requires static lombok;
    requires org.slf4j;

    requires org.mapstruct;
    requires org.mapstruct.processor;
    requires lombok.mapstruct;

    requires hotel.reservation.service;
    requires tax.payment.provider;

    exports ru.otus.timofeev.task5.api.error;
    exports ru.otus.timofeev.task5.api.dto;
    exports ru.otus.timofeev.task5.api.controller;

    opens ru.otus.timofeev.task5.api to spring.core, spring.beans, spring.context;
    opens ru.otus.timofeev.task5.api.config to spring.core, spring.beans, spring.context;
    opens ru.otus.timofeev.task5.api.service to spring.core, spring.beans, spring.context;
    opens ru.otus.timofeev.task5.api.controller to spring.core, spring.beans, spring.context, spring.web, spring.aop;
    opens ru.otus.timofeev.task5.api.error to spring.core, spring.beans, spring.context;
    opens ru.otus.timofeev.task5.api.dto;
}