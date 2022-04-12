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

    public Posts() {
    }

    public Posts(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
