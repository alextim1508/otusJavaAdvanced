package ru.otus.timofeev.task5.taxprovider.exception;

import lombok.Getter;

public class TaxNotFoundException extends RuntimeException {

    @Getter
    private final long taxId;

    public TaxNotFoundException(long taxId) {
        super("Tax with ID %d not found".formatted(taxId));
        this.taxId = taxId;
    }
}