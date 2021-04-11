package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.request.GetStatusDescendingRequest;
import com.artyemlavrov.lab5.common.response.GetStatusDescendingResponse;
import com.artyemlavrov.lab5.common.types.Status;

import java.util.List;

public class PrintFieldDescendingStatusCommand extends ClientServerCommand<GetStatusDescendingRequest, GetStatusDescendingResponse> {

    @Override
    public String getDescription() {
        return ": вывести значения поля status всех элементов в порядке убывания";
    }

    @Override
    public String getName() {
        return "print_field_descending_status ";
    }

    @Override
    protected GetStatusDescendingRequest buildRequest(IOManager ioManager) {
        return new GetStatusDescendingRequest();
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetStatusDescendingResponse response) {
        List<Status> statusList = response.getStatusList();
        if (statusList.isEmpty()) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            statusList.forEach(status -> ioManager.write(status + " "));
            ioManager.newLine();
        }
    }

    @Override
    protected Class<GetStatusDescendingResponse> getResponseClass() {
        return GetStatusDescendingResponse.class;
    }
}
