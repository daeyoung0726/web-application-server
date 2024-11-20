package board.common.http;

import board.common.util.io.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpRequest {
    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

    private HttpMethod method;
    private String path;
    private HttpHeaders headers;
    private RequestParams params = new RequestParams();

    public HttpRequest(InputStream in) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            parseURI(br.readLine());
            headers = parseHeaders(br);
            parseBody(br);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void parseURI(String uri) {
        log.debug("request line : {}", uri);

        String[] split = uri.split(" ");

        this.method = HttpMethod.valueOf(split[0]);

        String[] url = split[1].split("\\?");
        this.path = url[0];

        if (url.length == 2) {
            params.addParams(url[1]);
        }
    }

    private HttpHeaders parseHeaders(BufferedReader br) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        String line;
        while (!(line = br.readLine()).equals("")) {
            headers.add(line);
        }
        return headers;
    }

    private void parseBody(BufferedReader br) throws IOException {
        params.addParams(IOUtil.readData(br, headers.getContentLength()));
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String key) {
        return headers.getHeader(key);
    }

    public String getParam(String key) {
        return params.getParameter(key);
    }

    public HttpCookie getCookies() {
        return headers.getCookies();
    }

    public HttpSession getSession() {
        return headers.getSession();
    }
}
