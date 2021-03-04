package de.jwiegmann.blog.application.post;

import de.jwiegmann.blog.application.user.UserService;
import de.jwiegmann.blog.domain.post.Post;
import de.jwiegmann.blog.domain.post.PostNotFoundException;
import de.jwiegmann.blog.domain.user.User;
import de.jwiegmann.blog.domain.user.UserNotFoundException;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.GraphQLException;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@GraphQLApi
public class PostResource {

    @Inject
    PostService postService;

    @Inject
    UserService userService;

    @Mutation()
    @Description("Create a new post")
    public Post createPost(@NonNull Post post) throws GraphQLException {

        if (!userService.existsWithId(post.getAuthor().getId())) {
            throw new UserNotFoundException(post.getAuthor().getId());
        }

        return postService.create(post);
    }

    @Query()
    @Description("Get a post by it's ID")
    public Post getPost(@NonNull Integer id) throws GraphQLException {

        Post post = postService.findById(id);

        if (post == null) {
            throw new PostNotFoundException(id);
        }

        return post;
    }

    @Query
    @Description("Get all posts with a specific keyword in the title")
    public List<Post> getPostsByTitleKeyword(@NonNull String keyword) throws GraphQLException {

        List<Post> posts = new ArrayList<>();

        try {
            for (Post post : postService.findAll()) {
                if (post.getTitle().contains(keyword)) {
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            throw new GraphQLException("Error while fetching posts with keyword " + keyword, posts);
        }

        return posts;
    }

    public List<Post> getPosts(@Source User user, int offset, int limit) {

        return postService.findPartialByUser(user.getId(), offset, limit);
    }
}
