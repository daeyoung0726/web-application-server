package board.domain.post.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.post.model.Post;
import board.domain.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePostController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreatePostController.class);

    private final PostService postService;

    public CreatePostController() {
        this.postService = PostService.getInstance();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        Post post = new Post(
                request.getParam("title"),
                request.getParam("content")
        );

        log.debug("post : {}", post);
        Long id = postService.savePost(post);
        response.sendRedirect("/post/post.html?id=" + id);
    }
}
