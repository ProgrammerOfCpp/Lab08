package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.request.GetSumOfSalaryRequest;
import com.artyemlavrov.lab8.common.response.GetSumOfSalaryResponse;

public class SumOfSalaryCommand extends ClientServerCommand<GetSumOfSalaryResponse> {

    public SumOfSalaryCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

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
        return new GetSumOfSalaryRequest(getAuthentication());
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetSumOfSalaryResponse response) {
        Double sumOfSalary = response.getSumOfSalary();
        ioManager.writeLine(sumOfSalary);
    }

}
