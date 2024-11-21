package board.domain.post.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeletePostController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(DeletePostController.class);

    private final PostService postService;

    public DeletePostController() {
        this.postService = PostService.getInstance();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        Long id = Long.valueOf(request.getParam("id"));

        postService.deletePost(id);

        response.sendRedirect("/index.html");
    }
}
