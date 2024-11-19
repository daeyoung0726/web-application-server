package board.common.util.http;

import board.common.util.io.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

    private String method;
    private String path;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> params = new HashMap<>();

    private static final String CONTENT_LENGTH = "Content-Length";

    public HttpRequest(InputStream in) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            parseURI(br.readLine());
            parseHeaders(br);
            parseBody(br);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void parseURI(String uri) {
        log.debug("request line : {}", uri);

        String[] split = uri.split(" ");

        this.method = split[0];

        String[] url = split[1].split("\\?");
        this.path = url[0];

        if (url.length == 2) {
            params.putAll(HttpParsing.parseQueryString(url[1]));
        }
    }

    private void parseHeaders(BufferedReader br) throws IOException {

        String line;
        while (!(line = br.readLine()).isEmpty()) {
            String[] split = line.split(":");

            headers.put(split[0].trim(), split[1].trim());
        }
    }

    private void parseBody(BufferedReader br) throws IOException {
        String requestParam = IOUtil.readData(br, getContentLength());

        if (requestParam.isEmpty())
            return;

        params.putAll(HttpParsing.parseQueryString(requestParam));
    }

    private int getContentLength() {
        String len = headers.get(CONTENT_LENGTH);

        return len == null ? 0 : Integer.parseInt(len);
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public String getParam(String key) {
        return params.get(key);
    }
}
