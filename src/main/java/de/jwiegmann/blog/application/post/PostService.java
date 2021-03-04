package de.jwiegmann.blog.application.post;

import de.jwiegmann.blog.domain.post.Post;
import de.jwiegmann.blog.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PostService {

    @Inject
    EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    @Transactional
    public Post create(Post post) throws EntityNotFoundException {

        return entityManager.merge(post);
    }

    public Post findById(int id) {

        return entityManager.find(Post.class, id);
    }

    public List<Post> findAll() {

        return findPartial(0, 100);
    }

    public List<Post> findPartial(int offset, int limit) {

        LOG.debug("Searching for Posts with offset " + offset + " and limit " + limit);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

        Root<Post> from = criteriaQuery.from(Post.class);
        criteriaQuery.select(from);

        TypedQuery<Post> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Post> results = query.getResultList();

        LOG.debug("Located {} Posts", results.size());

        return results;
    }

    public List<Post> findPartialByUser(int id, int offset, int limit) {

        LOG.debug("Searching for Posts from user with " + id + " with offset " + offset + " and limit " + limit);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

        Root<Post> from = criteriaQuery.from(Post.class);
        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("author"), id));

        TypedQuery<Post> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Post> results = query.getResultList();

        LOG.debug("Located {} Posts", results.size());

        return results;
    }
}
