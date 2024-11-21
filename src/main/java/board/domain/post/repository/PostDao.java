package board.domain.post.repository;

import board.database.ConnectionMaker;
import board.database.DataBase;
import board.domain.post.model.Post;

import java.util.List;

public class PostDao {
    private static final PostDao postDao = new PostDao();
    private final DataBase<Post> postDataBase;

    private PostDao() {
        this.postDataBase = ConnectionMaker.getPostDataBase();
    }

    public static PostDao getInstance() {
        return postDao;
    }

    public Long savePost(Post post) {
        return (Long) postDataBase.add(post);
    }

    public Post findPostById(Long id) {
        return postDataBase.findById(id);
    }

    public List<Post> findAllPosts() {
        return postDataBase.findAll();
    }

    public void updatePost(Long id, Post post) {
        postDataBase.updateById(id, post);
    }

    public void deletePost(Long id) {
        postDataBase.deleteById(id);
    }
}
