package com.koltsov.cakes.manager.cakesservice.repository;

import com.koltsov.cakes.manager.cakesservice.data.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
}
