package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.GetSumOfSalaryRequest;
import com.artyemlavrov.lab6.common.response.GetSumOfSalaryResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class GetSumOfSalaryInvoker extends RequestInvoker<GetSumOfSalaryRequest, GetSumOfSalaryResponse> {

    @Override
    public GetSumOfSalaryResponse invoke(WorkersCollection collection, GetSumOfSalaryRequest request) {
        Double sumOfSalary = collection.getSumOfSalary();
        return new GetSumOfSalaryResponse(sumOfSalary);
    }

    @Override
    public Class<GetSumOfSalaryRequest> getRequestClass() {
        return GetSumOfSalaryRequest.class;
    }
}
