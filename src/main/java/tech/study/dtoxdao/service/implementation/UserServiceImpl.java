package tech.study.dtoxdao.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.study.dtoxdao.persistence.dao.interfaces.IUserDAO;
import tech.study.dtoxdao.persistence.entity.UserEntity;
import tech.study.dtoxdao.presentation.dto.UserDTO;
import tech.study.dtoxdao.service.interfaces.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserDTO> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        return this.userDAO.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<UserEntity> userEntity = this.userDAO.findById(id);

        if(userEntity.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            UserEntity currentUser = userEntity.get();
            return modelMapper.map(currentUser, UserDTO.class);
        } else{
          return   new UserDTO();
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            this.userDAO.saveUser(userEntity);

            return userDTO;
        } catch (Exception e){
            throw new UnsupportedOperationException("Erro ao salvar usuario");
        }

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {

        Optional<UserEntity> userEntity = this.userDAO.findById(id);

        if(userEntity.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            userDTO.setId(id);
            UserEntity currentUser = modelMapper.map(userDTO, UserEntity.class);
            this.userDAO.updateUser(currentUser);
        } else {
            throw new IllegalArgumentException("Usuario não existe");
        }


        return null;
    }

    @Override
    public String delete(Long id) {

        Optional<UserEntity> userEntity = this.userDAO.findById(id);

        if(userEntity.isPresent()){
            UserEntity currentUserEntity = userEntity.get();
            this.userDAO.deleteUser(currentUserEntity);
            return "Usuario com id " + id + " excluido";
        }

        return "O usuario com id" +id+ " não existe";
    }
}
