package de.jwiegmann.blog.domain.user;

import org.eclipse.microprofile.graphql.GraphQLException;

public class UserAlreadyExistsException extends GraphQLException {

   public UserAlreadyExistsException(String name) {
      super("User with name " + name + " already exists.", ExceptionType.DataFetchingException);
   }
}
