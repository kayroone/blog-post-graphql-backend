
# Exercises for the _GraphQL_ workshop

## Overview

####EXERCISES:

1. [EXERCISE 1: UserResource - Add a method to create a new user](src/main/java/de/jwiegmann/blog/application/post/UserResource.java)
1. [EXERCISE 2: PostResource - Add a method to create a new post](src/main/java/de/jwiegmann/blog/application/post/PostResource.java)
3. [EXERCISE 3: PostResource - Add a method to fetch a single post](src/main/java/de/jwiegmann/blog/application/post/PostResource.java)
4. [EXERCISE 4: PostResource - Add a method to fetch all post matching a given keyword in the title](src/main/java/de/jwiegmann/blog/application/post/PostResource.java)
4. [EXERCISE 5: PostResource - Add a method to add posts to the user query response](src/main/java/de/jwiegmann/blog/application/post/PostResource.java)
4. [EXERCISE 6: UserResourceTest - Write tests for all your resource methods](src/test/java/UserResourceTest.java)
4. [EXERCISE 7: PostResourceTest - Write tests for all your resource methods](src/test/java/PostResourceTest.java)

####Important stuff:

- GraphQL MicroProfile Documentation: [MP GraphQL](https://download.eclipse.org/microprofile/microprofile-graphql-1.0/microprofile-graphql.html)
- Start your application with: `./mvnw compile quarkus:dev`
- GraphQL UI to test your endpoints: `http://localhost:8080/q/graphql-ui`
- Raw schema file available at: `curl http://localhost:8080/graphql/schema.graphql`
- To wrap and start your application into a docker container: [Dockerfile](src/main/docker/Dockerfile.native)

### Exercise 1

Read the [MP GraphQL](https://download.eclipse.org/microprofile/microprofile-graphql-1.0/microprofile-graphql.html) Documentation 
to find out what you need to *write* files with GraphQL to the server.

Todos:
1. Find and read the appropriate section in the documentation.
2. Create a method and use an appropriate service to persist a new user to the database. Consider that a corresponding 
   message should be returned by the API if a user with the name already exists. 
3. Test your new method over the GraphQL UI.

### Exercise 2

Read the [MP GraphQL](https://download.eclipse.org/microprofile/microprofile-graphql-1.0/microprofile-graphql.html) Documentation
to find out what you need to *write* files with GraphQL to the server.

Todos:
1. Find and read the appropriate section in the documentation.
2. Create a method and use an appropriate service to persist a new post to the database. Consider that a corresponding
   message should be returned by the API if a user/author with the given ID doesn't exist. 
3. Test your new method over the GraphQL UI.

### Exercise 3

Read the [MP GraphQL](https://download.eclipse.org/microprofile/microprofile-graphql-1.0/microprofile-graphql.html) Documentation
to find out what you need to *read* files with GraphQL to the server.

Todos:
1. Find and read the appropriate section in the documentation.
2. Create a method and use an appropriate service to fetch a single post user from the database. Consider that a corresponding
   message should be returned by the API if a post with the given ID doesn't exist.
3. Test your new method over the GraphQL UI.

### Exercise 4

Read the [MP GraphQL](https://download.eclipse.org/microprofile/microprofile-graphql-1.0/microprofile-graphql.html) Documentation
to find out what you need to *read* files and *fetch partial results* with GraphQL to the server.

Todos:
1. Find and read the appropriate section in the documentation.
2. Create a method and use an appropriate service to *fetch partial posts* from the database. Create your method so 
   that if an exception is thrown during fetching, partial results will still be returned. 
3. Test your new method over the GraphQL UI.

### Exercise 5

Read the [MP GraphQL](https://download.eclipse.org/microprofile/microprofile-graphql-1.0/microprofile-graphql.html) Documentation
to find out what you need to add additional data to an existing query.

Hint: You have to @Source the additional data.

Todos:
1. Find and read the appropriate section in the documentation.
2. Create a method that allows to query the posts of the corresponding user via the user query. 
3. Test your method by adding a post query to the user query over the GraphQL UI.

### Exercise 6 & 7

Write unit tests for your resource methods with the queries from your GraphQL UI tests using JUnit 5 and RestAssured.
