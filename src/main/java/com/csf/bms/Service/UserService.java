package com.csf.bms.Service;
import com.csf.bms.Dto.UserDto;
import com.csf.bms.Model.User;
import com.csf.bms.Repo.UserRepo;
import com.csf.bms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

        @Autowired
        private UserRepo userRepo;
        private UserDto createUser(UserDto userDto){
        User user=maptoEntity(userDto);
        User savedUser=userRepo.save(user);
        return mapToDto(savedUser);
    }

    public UserDto getUserById(Long id){
        User user=userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Use not found with id: " +id));
        return mapToDto(user);
    }

    public List<UserDto> getAllUsers(){
       List<User> users=userRepo.findAll();
       return users.stream()
               .map(this::mapToDto)
               .collect(Collectors.toList());
    }

    public UserDto updateUser(String email,UserDto userDto){
        User user=userRepo.findByEmail(email)
        .orElseThrow(()->new ResourceNotFoundException("Movie not found with id: " +email));
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setName(userDto.getName());

        User updateUser = userRepo.save(user);
        return mapToDto(updateUser);

    }

    public void deleteUser(String email){

        User user =userRepo.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id: " + email));
        userRepo.delete(user);
    }

    private User maptoEntity(UserDto userDto){
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    private UserDto mapToDto(User user){

        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}
