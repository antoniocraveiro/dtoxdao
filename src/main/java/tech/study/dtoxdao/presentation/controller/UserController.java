package tech.study.dtoxdao.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.study.dtoxdao.presentation.dto.UserDTO;
import tech.study.dtoxdao.service.interfaces.IUserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //FindAll
    @GetMapping("/find")
    public ResponseEntity<List<UserDTO>> findAll(){
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    //FindById
    @GetMapping("find/{id}")
    public ResponseEntity <UserDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
    }

    //create User
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.createUser(userDTO), HttpStatus.CREATED);
    }
    //Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        return new ResponseEntity<>(this.userService.updateUser(userDTO, id), HttpStatus.OK);
    }

    //Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.delete(id), HttpStatus.NO_CONTENT);
    }
}
