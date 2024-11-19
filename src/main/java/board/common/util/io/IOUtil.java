package board.common.util.io;

import java.io.BufferedReader;
import java.io.IOException;

public class IOUtil {

    public static String readData(BufferedReader br, int contentLength) throws IOException {
        char[] body = new char[contentLength];
        br.read(body, 0, contentLength);
        return String.copyValueOf(body);
    }
}