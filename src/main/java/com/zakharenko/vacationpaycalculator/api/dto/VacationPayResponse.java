package com.zakharenko.vacationpaycalculator.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacationPayResponse {

    double vacationPay;
}
