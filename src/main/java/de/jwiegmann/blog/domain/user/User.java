package de.jwiegmann.blog.domain.user;

import org.eclipse.microprofile.graphql.Ignore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Ignore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public static UserBuilder newBuilder() {
        return new UserBuilder();
    }

    public static class UserBuilder extends DefaultBuilder<User> {

        public UserBuilder() {

            super();
            this.instance.posts = new HashSet<>();
        }

        public UserBuilder withId(int id) {
            this.instance.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.instance.name = name;
            return this;
        }

        public UserBuilder withPosts(Set<Post> posts) {
            this.instance.posts = posts;
            return this;
        }
    }*/
}
