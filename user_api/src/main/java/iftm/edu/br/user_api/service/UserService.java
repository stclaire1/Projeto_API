package iftm.edu.br.user_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iftm.edu.br.user_api.models.User;
import iftm.edu.br.user_api.models.dto.UserDTO;
import iftm.edu.br.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //utilizado no lugar do @Autowired, devido ao Lombok
public class UserService {
    private final UserRepository userRepo;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepo.findAll();
        return usuarios.stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    public UserDTO findById(String userId) {
        User usuario = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.convert(usuario);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepo.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(String userId) {
        User user = userRepo
            .findById(userId).orElseThrow(() -> new RuntimeException());
                userRepo.delete(user);
                return UserDTO.convert(user);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepo.findByCpf(cpf);
        if(user != null) {
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String nome) {
        List<User> usuarios = userRepo.queryByNomeLike(nome);
        return usuarios
            .stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    public UserDTO editUser(String userId, UserDTO userDTO) {
        User user = userRepo
            .findById(userId).orElseThrow(() -> new RuntimeException());
        if(userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())) {
            user.setTelefone(userDTO.getTelefone());
        }
        if(userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }
        user = userRepo.save(user);
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPages(Pageable page) {
        Page<User> users = userRepo.findAll(page);
        return users
            .map(UserDTO::convert);
    }
}
