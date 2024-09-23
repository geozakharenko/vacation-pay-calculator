package com.zakharenko.vacationpaycalculator;

import com.zakharenko.vacationpaycalculator.api.services.VacationPayCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacationPayCalculatorServiceTest {

    private final VacationPayCalculatorService calculatorService = new VacationPayCalculatorService();

    @Test
    void calculateVacationPay_ShouldReturnCorrectPay_WhenValidInput() {
        Double averageSalary = 100_000.00;
        Integer vacationDays = 28;

        Double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);

        assertEquals(95563.13, result, 0.06);
    }

    @Test
    void calculateVacationPay_ShouldReturnZero_WhenZeroVacationDays() {
        Double averageSalary = 29300.0;
        Integer vacationDays = 0;

        Double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);

        assertEquals(0.0, result);
    }

    @Test
    void calculateVacationDaysByExcludingDates_ShouldExcludeHolidaysAndReturnCorrectDays() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 10);

        Integer result = calculatorService.calculateVacationDaysByExcludingDates(startDate, endDate);

        // ExcludingDates: 1, 2, 3, 4, 5, 6, 7, 8 January
        assertEquals(2, result);
    }

    @Test
    void calculateVacationDaysByExcludingDates_ShouldReturnAllDays_WhenNoExcludedDatesInRange() {
        LocalDate startDate = LocalDate.of(2024, 7, 1);
        LocalDate endDate = LocalDate.of(2024, 7, 22);

        Integer result = calculatorService.calculateVacationDaysByExcludingDates(startDate, endDate);

        assertEquals(22, result);
    }

    @Test
    void calculateVacationDaysByExcludingDates_ShouldReturnZero_WhenAllDatesExcluded() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 8);

        Integer result = calculatorService.calculateVacationDaysByExcludingDates(startDate, endDate);

        assertEquals(0, result);
    }

    @Test
    void calculateVacationDaysByExcludingDates_ShouldReturnCorrectDays_ForOneDayRange() {
        LocalDate startDate = LocalDate.of(2024, 2, 24);
        LocalDate endDate = LocalDate.of(2024, 2, 24);

        Integer result = calculatorService.calculateVacationDaysByExcludingDates(startDate, endDate);

        assertEquals(1, result);
    }
}

