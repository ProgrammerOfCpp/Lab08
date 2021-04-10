package com.artyemlavrov.lab5.response;

public class GetSumOfSalaryResponse extends Response {
    private final Double sumOfSalary;


    public GetSumOfSalaryResponse(Double sumOfSalary) {
        this.sumOfSalary = sumOfSalary;
    }

    public Double getSumOfSalary() {
        return sumOfSalary;
    }
}
