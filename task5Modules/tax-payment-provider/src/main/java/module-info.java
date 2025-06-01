module tax.payment.provider {
    requires spring.context;
    requires spring.beans;

    requires lombok;

    requires core;

    exports ru.otus.timofeev.task5.taxprovider.entity;
    exports ru.otus.timofeev.task5.taxprovider.service;
    exports ru.otus.timofeev.task5.taxprovider.exception;

    opens ru.otus.timofeev.task5.taxprovider.service to spring.core, spring.beans, spring.context;
    opens ru.otus.timofeev.task5.taxprovider.repository to spring.core, spring.beans, spring.context;
}