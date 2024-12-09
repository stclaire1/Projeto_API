package iftm.edu.br.user_api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import iftm.edu.br.user_api.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByCpf(String cpf);
    List<User> queryByNomeLike(String nome);
}
