package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.GetSumOfSalaryRequest;
import com.artyemlavrov.lab5.common.response.GetSumOfSalaryResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;

public class GetSumOfSalaryInvoker extends RequestInvoker<GetSumOfSalaryRequest, GetSumOfSalaryResponse> {

    @Override
    protected GetSumOfSalaryResponse getResponse(WorkersCollection collection, GetSumOfSalaryRequest request) {
        Double sumOfSalary = collection.getSumOfSalary();
        return new GetSumOfSalaryResponse(sumOfSalary);
    }

    @Override
    public Class<GetSumOfSalaryRequest> getRequestClass() {
        return null;
    }
}
