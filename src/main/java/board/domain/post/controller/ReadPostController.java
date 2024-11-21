package board.domain.post.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.post.model.Post;
import board.domain.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadPostController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ReadPostController.class);

    private final PostService postService;

    public ReadPostController() {
        this.postService = PostService.getInstance();
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        Long id = Long.valueOf(request.getParam("id"));

        Post foundPost = postService.findUserById(id);

        if (foundPost == null) {
            response.forwardJson("{\"error\": \"Post not found\"}");
            return;
        }

        log.debug("read post info : {}", foundPost);

        response.forwardJson(createJsonData(foundPost));
    }

    private String createJsonData(Post foundPost) {
        return String.format(
                "{\"id\": \"%s\", \"title\": \"%s\", \"content\": \"%s\"}",
                foundPost.getId(),
                foundPost.getTitle(),
                foundPost.getContent()
        );
    }
}
