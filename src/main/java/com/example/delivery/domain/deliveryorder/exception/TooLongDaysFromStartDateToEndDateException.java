package com.example.delivery.domain.deliveryorder.exception;

public class TooLongDaysFromStartDateToEndDateException extends RuntimeException{
    public TooLongDaysFromStartDateToEndDateException() {
        super("Too Long Days From Start Date to End Date, Please set 3 days duration.");
    }

    public TooLongDaysFromStartDateToEndDateException(String message) {
        super(message);
    }
}
