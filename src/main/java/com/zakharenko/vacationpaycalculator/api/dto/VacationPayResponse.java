package com.zakharenko.vacationpaycalculator.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacationPayResponse {

    @JsonProperty("vacation_pay")
    double vacationPay;
}
