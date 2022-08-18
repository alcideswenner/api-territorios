package com.alcideswenner.apiterritorios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alcideswenner.apiterritorios.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public User findByUsername(String username);
}
