package de.jwiegmann.blog.domain.post;

import org.eclipse.microprofile.graphql.GraphQLException;

public class PostNotFoundException extends GraphQLException {

    public PostNotFoundException(int id) {
        super("Post with id " + id + " not found", ExceptionType.DataFetchingException);
    }
}
