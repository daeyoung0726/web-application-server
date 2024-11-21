package board.domain.post.service;

import board.domain.post.model.Post;
import board.domain.post.repository.PostDao;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PostService {
    private static final PostService postService = new PostService();
    private final PostDao postDao;

    private PostService() {
        this.postDao = PostDao.getInstance();
    }

    public static PostService getInstance() {
        return postService;
    }

    public Long savePost(Post post) {
        return postDao.savePost(decodeUser(post));
    }

    public Post findUserById(Long id) {
        return postDao.findPostById(id);
    }

    public List<Post> findAllPosts() {
        return postDao.findAllPosts();
    }

    public void updatePost(Long id, Post post) {
        postDao.updatePost(id, post);
    }

    public void deletePost(Long id) {
        postDao.deletePost(id);
    }

    private Post decodeUser(Post post) {
        return new Post(
                URLDecoder.decode(post.getTitle(), StandardCharsets.UTF_8),
                URLDecoder.decode(post.getContent(), StandardCharsets.UTF_8)
        );
    }
}
