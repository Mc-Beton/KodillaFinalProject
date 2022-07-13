package com.imdbmovie.kodillafinalproject.user.repository;



import com.imdbmovie.kodillafinalproject.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);
    List<User> findAll();
    Optional<User> findUserById(Long id);
    void deleteById(Long id);
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
    Optional<List<User>> findAllByName(String name);
}
