import com.google.common.base.Objects;

public class Posts {
    int userId;
    int id;
    String title;
    String body;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts = (Posts) o;
        return userId == posts.userId && id == posts.id &&
                Objects.equal(title, posts.title) && Objects.equal(body, posts.body);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, id, title, body);
    }

    private Posts() {
    }

    private Posts(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    private int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }
}
