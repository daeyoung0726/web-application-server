package board.domain.post.repository;

import board.database.JdbcTemplate;
import board.database.RowMapper;
import board.domain.post.model.Post;

import java.util.List;

public class PostDao {
    private static final PostDao postDao = new PostDao();

    private final JdbcTemplate jdbcTemplate;

    private PostDao() {
        this.jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static PostDao getInstance() {
        return postDao;
    }

    private final RowMapper<Post> postMapper = rs -> new Post(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("content")
    );

    public Long savePost(Post post) {
        String sql = "INSERT INTO \"Post\"(title, content) VALUES(?, ?)";
        return (Long) jdbcTemplate.update(
                sql,
                post.getTitle(),
                post.getContent()
        );
    }

    public Post findPostById(Long id) {
        String sql = "SELECT * FROM \"Post\" WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, postMapper, id);
    }

    public List<Post> findAllPosts() {
        String sql = "SELECT * FROM \"Post\"";
        return jdbcTemplate.query(sql, postMapper);
    }

    public void updatePost(Long id, Post post) {
        String sql = "UPDATE \"Post\" SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                post.getTitle(),
                post.getContent(),
                id
        );
    }

    public void deletePost(Long id) {
        String sql = "DELETE FROM \"Post\" WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
