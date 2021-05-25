package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Status;

import java.util.List;

public class GetStatusDescendingResponse extends Response {
    private final List<Status> statusList;

    public GetStatusDescendingResponse(Authentication authentication, List<Status> statusList) {
        super(authentication);
        this.statusList = statusList;
    }

    public List<Status> getStatusList() {
        return statusList;
    }
}
