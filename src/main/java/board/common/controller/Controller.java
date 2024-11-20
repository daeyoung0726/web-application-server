package board.common.controller;

import board.common.http.HttpRequest;
import board.common.http.HttpResponse;

public interface Controller {
    void service(HttpRequest request, HttpResponse response);
}
