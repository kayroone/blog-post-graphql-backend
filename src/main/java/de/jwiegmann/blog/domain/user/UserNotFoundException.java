package de.jwiegmann.blog.domain.user;

import org.eclipse.microprofile.graphql.GraphQLException;

public class UserNotFoundException extends GraphQLException {

   public UserNotFoundException(Integer id) {
      super("User with id " + id + " not found.", ExceptionType.DataFetchingException);
   }
}
