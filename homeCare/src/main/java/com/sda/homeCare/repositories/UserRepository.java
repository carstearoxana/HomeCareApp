package com.sda.homeCare.repositories;

import com.sda.homeCare.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUserName(String name);
    public User findByUserEmail(String email);
}


