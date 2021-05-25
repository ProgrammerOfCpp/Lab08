package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;

public class GetSumOfSalaryResponse extends Response {
    private final Double sumOfSalary;

    public GetSumOfSalaryResponse(Authentication authentication, Double sumOfSalary) {
        super(authentication);
        this.sumOfSalary = sumOfSalary;
    }

    public Double getSumOfSalary() {
        return sumOfSalary;
    }
}
