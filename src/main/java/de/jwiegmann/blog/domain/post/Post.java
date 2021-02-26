package de.jwiegmann.blog.domain.post;

import de.jwiegmann.blog.domain.user.User;
import org.eclipse.microprofile.graphql.Ignore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {

    @Ignore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User author;

    public Post() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    /*public static PostBuilder newBuilder() {
        return new PostBuilder();
    }

    public static class PostBuilder extends DefaultBuilder<Post> {

        public PostBuilder() {
            super();
        }

        public PostBuilder withId(int id) {
            this.instance.id = id;
            return this;
        }

        public PostBuilder withTitle(String title) {
            this.instance.title = title;
            return this;
        }

        public PostBuilder withContent(String content) {
            this.instance.content = content;
            return this;
        }
    }*/
}
