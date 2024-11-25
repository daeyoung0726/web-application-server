package board.domain.post.repository;

import board.database.ConnectionManager;
import board.database.RowMapper;
import board.database.exception.DataAccessException;
import board.domain.post.exception.PostException;
import board.domain.post.exception.PostExceptionCode;
import board.domain.post.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    private static final PostDao postDao = new PostDao();

    private PostDao() {}

    public static PostDao getInstance() {
        return postDao;
    }

    private final RowMapper<Post> postMapper = new RowMapper<Post>() {
        @Override
        public Post mapRow(ResultSet rs) throws SQLException {
            return new Post(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content")
            );
        }
    };

    public Long savePost(Post post) {
        String sql = "INSERT INTO \"Post\"(title, content) VALUES(?, ?)";
        Long id = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        return id;
    }

    public Post findPostById(Long id) {
        String sql = "SELECT * FROM \"Post\" WHERE id = ?";
        Post post = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                post = postMapper.mapRow(rs);
            }

            rs.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        if (post == null)
            throw new PostException(PostExceptionCode.POST_NOT_FOUND);
        return post;
    }

    public List<Post> findAllPosts() {
        String sql = "SELECT * FROM \"Post\"";
        List<Post> posts = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Post post = postMapper.mapRow(rs);
                posts.add(post);
            }

            rs.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return posts;
    }

    public void updatePost(Long id, Post post) {
        String sql = "UPDATE \"Post\" SET title = ?, content = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void deletePost(Long id) {
        String sql = "DELETE FROM \"Post\" WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
