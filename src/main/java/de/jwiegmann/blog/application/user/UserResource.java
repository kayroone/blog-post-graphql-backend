package de.jwiegmann.blog.application.user;

import de.jwiegmann.blog.domain.user.User;
import de.jwiegmann.blog.domain.user.UserAlreadyExistsException;
import de.jwiegmann.blog.domain.user.UserNotFoundException;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.GraphQLException;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;

@GraphQLApi
public class UserResource {

    @Inject
    UserService userService;

    @Mutation()
    @Description("Create a new user")
    public User createUser(@NonNull String name) throws GraphQLException {

        if (userService.existsWithName(name)) {
            throw new UserAlreadyExistsException(name);
        }

        return userService.create(name);
    }

    @Query
    @Description("Get a user by it's ID")
    public User getUser(@NonNull Integer id) throws GraphQLException {

        User user = userService.findById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        return user;
    }
}
