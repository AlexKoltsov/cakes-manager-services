package com.koltsov.cake.manager.peopleservice.repository;

import com.koltsov.cake.manager.peopleservice.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
