package tech.study.dtoxdao.persistence.dao.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.study.dtoxdao.persistence.dao.interfaces.IUserDAO;
import tech.study.dtoxdao.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return this.em.createQuery("SELECT u FROM UserEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(this.em.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void saveUser(UserEntity userEntity) {
        this.em.persist(userEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void updateUser(UserEntity userEntity) {
        this.em.merge(userEntity);
    }

    @Override
    @Transactional
    public void deleteUser(UserEntity userEntity) {
        this.em.remove(userEntity);
    }
}
