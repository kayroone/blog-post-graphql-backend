package de.jwiegmann.blog.application.user;

import de.jwiegmann.blog.domain.user.User;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.GraphQLException;
import org.eclipse.microprofile.graphql.Mutation;

import javax.inject.Inject;

@GraphQLApi
public class UserResource {

    @Inject
    UserService userService;

    @Mutation()
    @Description("Create a new User")
    public User createUser(User user) throws GraphQLException {

        if (userService.exists(user.getName())) {
            throw new GraphQLException("User with name " + user.getName() + " already exists");
        }

        return userService.create(user);
    }
}
