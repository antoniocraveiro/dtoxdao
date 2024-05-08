package tech.study.dtoxdao.service.interfaces;

import tech.study.dtoxdao.presentation.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, Long id);
    String delete(Long id);
}
