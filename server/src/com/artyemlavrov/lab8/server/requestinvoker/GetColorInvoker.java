package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.GetColorRequest;
import com.artyemlavrov.lab8.common.response.GetColorResponse;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.server.ServerApplication;

public class GetColorInvoker extends RequestInvoker<GetColorRequest> {
    public GetColorInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public Response buildResponse(GetColorRequest request) {
        Integer color = getCollectionProvider(request).getElementColor(request.getElementId());
        return new GetColorResponse(request.getAuthentication(), color);
    }

    @Override
    public Class<GetColorRequest> getRequestClass() {
        return GetColorRequest.class;
    }
}
