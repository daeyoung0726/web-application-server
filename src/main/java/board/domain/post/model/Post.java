package board.domain.post.model;

public class Post {
    private Long id;
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void updatePost(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Override
    public String toString() {
        return "Post [id=" + id +
                ", title=" + title +
                ", content=" + content +
                "]";
    }
}
