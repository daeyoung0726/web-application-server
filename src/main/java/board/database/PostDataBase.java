package board.database;

import board.domain.post.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostDataBase implements DataBase<Post> {
    private static final Map<Long, Post> postMap = new ConcurrentHashMap<>();
    private static Long idCounter = 1L;

    private static final PostDataBase postDataBase = new PostDataBase();

    private PostDataBase() {}

    public static PostDataBase getInstance() {
        return postDataBase;
    }

    @Override
    public void add(Post post) {
        post.setId(idCounter++);
        postMap.put(post.getId(), post);
    }

    @Override
    public Post findById(Object id) {
        return postMap.get((Long) id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    @Override
    public void updateById(Object id, Post post) {
        Post foundPost = postMap.get((Long) id);

        if (foundPost == null)
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        foundPost.updatePost(post);
    }

    @Override
    public void deleteById(Object id) {
        postMap.remove((Long) id);
    }
}
