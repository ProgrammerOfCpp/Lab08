package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.request.GetSumOfSalaryRequest;
import com.artyemlavrov.lab5.common.response.GetSumOfSalaryResponse;

public class SumOfSalaryCommand extends ServersideCommand<GetSumOfSalaryRequest, GetSumOfSalaryResponse> {

    @Override
    public String getDescription() {
        return ": вывести сумму значений поля salary для всех элементов коллекции";
    }

    @Override
    public String getName() {
        return "sum_of_salary";
    }

    @Override
    protected GetSumOfSalaryRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        return new GetSumOfSalaryRequest();
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, GetSumOfSalaryResponse response) {

    }
}
