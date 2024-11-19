package board.common.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class HttpHeaders {
    private static final Logger log = LoggerFactory.getLogger(HttpHeaders.class);

    private static final String CONTENT_LENGTH = "Content-Length";

    private Map<String, String> headers = new HashMap<>();

    void add(String header) {
        log.debug("header : {}", header);
        String[] splitedHeaders = header.split(":");
        headers.put(splitedHeaders[0], splitedHeaders[1].trim());
    }

    String getHeader(String name) {
        return headers.get(name);
    }

    int getContentLength() {
        String header = getHeader(CONTENT_LENGTH);
        return header == null ? 0 : Integer.parseInt(header);
    }
}
