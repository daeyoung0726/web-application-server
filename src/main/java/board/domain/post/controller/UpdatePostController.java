package board.domain.post.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.post.model.Post;
import board.domain.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdatePostController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdatePostController.class);

    private final PostService postService;

    public UpdatePostController() {
        this.postService = PostService.getInstance();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        Long id = Long.valueOf(request.getParam("id"));

        Post post = new Post(
                request.getParam("title"),
                request.getParam("content")
        );

        postService.updatePost(id, post);

        log.debug("updated post info : {}", post);
        response.sendRedirect("/post/post.html?id=" + id);
    }
}
