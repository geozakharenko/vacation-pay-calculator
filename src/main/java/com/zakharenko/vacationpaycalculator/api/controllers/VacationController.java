package com.zakharenko.vacationpaycalculator.api.controllers;

import com.zakharenko.vacationpaycalculator.api.dto.VacationPayResponse;
import com.zakharenko.vacationpaycalculator.api.exceptions.BadRequestException;
import com.zakharenko.vacationpaycalculator.api.services.VacationPayCalculatorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VacationController {

    VacationPayCalculatorService calculatorService;

    public static final String CALCULATE_VACATION_PAY = "/calculate";

    @GetMapping(CALCULATE_VACATION_PAY)
    public VacationPayResponse calculate(@RequestParam("average_salary") String averageSalaryString,
                                         @RequestParam("vacation_days") String vacationDaysString,
                                         @RequestParam(value = "on_vac", required = false) Optional<String> dayGoingOnVacation,
                                         @RequestParam(value = "from_vac", required = false) Optional<String> dayOfArrivalFromVacation) {
        Double vacationPay;

        if (dayGoingOnVacation.isPresent() && dayOfArrivalFromVacation.isPresent()) {
            try {
                LocalDate dayGoingOnVacationDate = LocalDate.parse(dayGoingOnVacation.get());
                LocalDate dayOfArrivalFromVacationDate = LocalDate.parse(dayOfArrivalFromVacation.get());
                vacationDaysString = Long.toString(calculatorService.calculateVacationDaysByExcludingDates(
                        dayGoingOnVacationDate, dayOfArrivalFromVacationDate));
            } catch (Exception ex) {
                throw new BadRequestException("Incorrect format of date");
            }
        }

        try {
            Double averageSalary = Double.parseDouble(averageSalaryString);
            Integer vacationDays = Integer.parseInt(vacationDaysString);
            vacationPay = calculatorService.calculateVacationPay(averageSalary, vacationDays);
        } catch (Exception ex) {
            throw new BadRequestException("Incorrect format");
        }

        return new VacationPayResponse(vacationPay);
    }
}
