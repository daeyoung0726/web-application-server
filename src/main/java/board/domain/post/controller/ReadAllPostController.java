package board.domain.post.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.post.model.Post;
import board.domain.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReadAllPostController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreatePostController.class);

    private final PostService postService;

    public ReadAllPostController() {
        this.postService = PostService.getInstance();
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        List<Post> posts = postService.findAllPosts();

        log.debug("posts : {}", posts);

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            sb.append(createJsonData(post));
            if (i < posts.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        response.forwardJson(sb.toString());
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
