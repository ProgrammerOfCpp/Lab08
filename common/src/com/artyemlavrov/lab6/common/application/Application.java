package com.artyemlavrov.lab6.common.application;

import com.artyemlavrov.lab6.common.exception.RequestFailureException;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.response.Response;

public abstract class Application {

    public abstract <ResponseType extends Response, RequestType extends Request> ResponseType getResponse(RequestType request) throws RequestFailureException;
}
