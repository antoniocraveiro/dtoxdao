package tech.study.dtoxdao.persistence.dao.interfaces;

import tech.study.dtoxdao.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    void saveUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    void deleteUser(UserEntity userEntity);
}
