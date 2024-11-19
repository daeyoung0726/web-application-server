package board.common.http;

import board.common.util.http.HttpParsing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class RequestParams {
    private static final Logger log = LoggerFactory.getLogger(RequestParams.class);

    private Map<String, String> params = new HashMap<>();

    private void putParams(String data) {
        log.debug("data : {}", data);

        if (data == null || data.isEmpty()) {
            return;
        }

        params.putAll(HttpParsing.parseQueryString(data));
        log.debug("params : {}", params);
    }

    void addParams(String queryString) {
        putParams(queryString);
    }

    String getParameter(String name) {
        return params.get(name);
    }
}
