package board.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;

public class TechExplorationBoardApplication {
    private static final Logger log = LoggerFactory.getLogger(TechExplorationBoardApplication.class);
    private static final int PORT = 8080;

    public static void main(String args[]) throws Exception {

        try (ServerSocket listenSocket = new ServerSocket(PORT)) {
            log.info("Server started {} port.", PORT);

            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }
        }
    }
}
