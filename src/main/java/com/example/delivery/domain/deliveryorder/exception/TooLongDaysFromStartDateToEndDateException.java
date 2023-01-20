package com.example.delivery.domain.deliveryorder.exception;

public class TooLongDaysFromStartDateToEndDateException extends RuntimeException{
    public TooLongDaysFromStartDateToEndDateException() {
        super("시작날짜와 끝날짜의 차이가 3일 이내의 기간만 조회가능합니다.");
    }

    public TooLongDaysFromStartDateToEndDateException(String message) {
        super(message);
    }
}
