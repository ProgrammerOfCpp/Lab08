package com.artyemlavrov.lab8.common.application;

import com.artyemlavrov.lab8.common.request.GetAllRequest;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.GetAllResponse;
import com.artyemlavrov.lab8.common.response.Response;

public class CollectionUpdater {
    private boolean stopFlag = false;

    private final BasicApplication application;

    public CollectionUpdater(BasicApplication application) {
        this.application = application;
    }

    public void start() {
        new Thread(() -> {
            while (!stopFlag) {
                Request request = new GetAllRequest(application.getAuthentication());
                try {
                    Response response = application.getResponse(request);
                    if (response instanceof GetAllResponse) {
                        GetAllResponse getAllResponse = (GetAllResponse) response;
                        application.updateCollection(getAllResponse.getElementsList());
                    }
                    //noinspection BusyWait
                    Thread.sleep(2000);
                } catch (Exception ignored) { }
            }
        }).start();
    }

    public void stop() {
        stopFlag = true;
    }
}
