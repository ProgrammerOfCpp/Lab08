package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.request.GetSumOfSalaryRequest;
import com.artyemlavrov.lab5.common.response.GetSumOfSalaryResponse;

public class SumOfSalaryCommand extends ClientServerCommand<GetSumOfSalaryRequest, GetSumOfSalaryResponse> {

    @Override
    public String getDescription() {
        return ": вывести сумму значений поля salary для всех элементов коллекции";
    }

    @Override
    public String getName() {
        return "sum_of_salary";
    }

    @Override
    protected GetSumOfSalaryRequest buildRequest(IOManager ioManager) {
        return new GetSumOfSalaryRequest();
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetSumOfSalaryResponse response) {
        Double sumOfSalary = response.getSumOfSalary();
        ioManager.writeLine(sumOfSalary);
    }

    @Override
    protected Class<GetSumOfSalaryResponse> getResponseClass() {
        return GetSumOfSalaryResponse.class;
    }
}
