package board.common.util.http;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpParsing {

    public static Map<String, String> parseQueryString(String queryString) {
        return parseValues(queryString, "&");
    }

    private static Map<String, String> parseValues(String values, String separator) {
        if (values == null || values.isEmpty()) {
            return Map.of();
        }

        return Arrays.stream(values.split(separator))
                .map(entry -> entry.split("=", 2))
                .filter(keyValue -> keyValue.length == 2)
                .collect(Collectors.toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
    }
}
