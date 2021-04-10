package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.request.GetSumOfSalaryRequest;
import com.artyemlavrov.lab5.response.GetSumOfSalaryResponse;

public class SumOfSalaryCommand extends ServerCommand<GetSumOfSalaryRequest, GetSumOfSalaryResponse> {
    @Override
    public String getDescription() {
        return ": вывести сумму значений поля salary для всех элементов коллекции";
    }

    @Override
    public String getName() {
        return "sum_of_salary";
    }

    @Override
    protected GetSumOfSalaryRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        return new GetSumOfSalaryRequest();
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, GetSumOfSalaryResponse response) {

    }
}
