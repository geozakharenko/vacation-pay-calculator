package com.zakharenko.vacationpaycalculator.api.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class VacationPayCalculatorService {

    private static final List<LocalDate> excludedDates = List.of(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 2),
            LocalDate.of(2024, 1, 3),
            LocalDate.of(2024, 1, 4),
            LocalDate.of(2024, 1, 5),
            LocalDate.of(2024, 1, 6),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 1, 8),
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 6, 12),
            LocalDate.of(2024, 11, 4)
    );

    private static final Double AVERAGE_NUMBER_OF_DAYS_PER_MONTH = 29.3;

    public Double calculateVacationPay(Double averageSalary, Integer vacationDays) {
        BigDecimal vacationPay = BigDecimal.valueOf((averageSalary / AVERAGE_NUMBER_OF_DAYS_PER_MONTH) * vacationDays);
        return vacationPay.setScale(2, RoundingMode.FLOOR).doubleValue();
    }

    public Integer calculateVacationDaysByExcludingDates(LocalDate onVac, LocalDate fromVac) {
        Integer vacationDays = 0;

        for (LocalDate date = onVac; !date.isAfter(fromVac); date = date.plusDays(1)) {
            if (!excludedDates.contains(date)) {
                vacationDays++;
            }
        }
        return vacationDays;
    }
}
