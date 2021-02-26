package de.jwiegmann.blog.application.post;

import de.jwiegmann.blog.application.user.UserService;
import de.jwiegmann.blog.domain.post.Post;
import de.jwiegmann.blog.domain.user.User;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.GraphQLException;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class PostResource {

    @Inject
    PostService postService;

    @Inject
    UserService userService;

    @Mutation()
    @Description("Create a Blogpost")
    public Post createPost(Post post) throws GraphQLException {

        if (userService.findById(post.getAuthor().getId()) == null) {
            throw new GraphQLException("User with id " + post.getAuthor().getId() + " doesn't exist.");
        }

        return postService.create(post);
    }

    @Query()
    @Description("Get a specific Blogpost by it's ID")
    public Post getPost(int id) throws GraphQLException {

        Post post = postService.findById(id);

        if (post == null) {
            throw new GraphQLException("Post with id " + id + " doesn't exist.");
        }

        return post;
    }

    @Query()
    @Description("Get partial Blogposts")
    public List<Post> getPosts(@Source User user, int offset, int limit) {

        return postService.findPartialByUser(user.getId(), offset, limit);
    }
}
