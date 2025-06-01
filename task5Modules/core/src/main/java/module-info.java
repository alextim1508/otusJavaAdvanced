module core {
    requires spring.context;

    requires static lombok;

    exports ru.otus.timofeev.task5.core.entity;
    exports ru.otus.timofeev.task5.core.repository;
    exports ru.otus.timofeev.task5.core.util;

    opens ru.otus.timofeev.task5.core.entity;
    opens ru.otus.timofeev.task5.core.repository to spring.core, spring.beans, spring.context;
    opens ru.otus.timofeev.task5.core.util to spring.core, spring.beans, spring.context;
}