package de.jwiegmann.blog.application.user;

import de.jwiegmann.blog.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public User create(String name) {

        User user = new User();
        user.setName(name);

        entityManager.persist(user);

        LOG.debug("User with id" + user.getId() + " created");

        return user;
    }

    public User findById(Integer id) {

        return entityManager.find(User.class, id);
    }

    public List<User> findByName(String name) {

        LOG.debug("Locating Users with search {}", name);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> from = cq.from(User.class);

        Predicate predicateUserName =
                cb.like(cb.lower(from.get("name")), "%" + name.toLowerCase() + "%");
        cq.select(from).where(cb.or(predicateUserName));

        TypedQuery<User> query = entityManager.createQuery(cq);
        List<User> users = query.getResultList();

        LOG.debug("Found Users {}", users);

        return Collections.unmodifiableList(users);
    }

    public boolean existsWithName(String name) {

        return findByName(name).size() > 0;
    }

    public boolean existsWithId(Integer id) {

        return findById(id) != null;
    }
}
