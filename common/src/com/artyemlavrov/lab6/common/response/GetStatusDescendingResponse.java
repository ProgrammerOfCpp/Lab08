package com.artyemlavrov.lab6.common.response;

import com.artyemlavrov.lab6.common.types.Status;

import java.util.List;

public class GetStatusDescendingResponse extends Response {
    private final List<Status> statusList;

    public GetStatusDescendingResponse(List<Status> statusList) {
        this.statusList = statusList;
    }

    public List<Status> getStatusList() {
        return statusList;
    }
}
