package com.pro.ukart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pro.ukart.Entities.*;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //Optional<Cart> findByUser(User user);
    Optional<Cart> findByUserId(Long userId);
}
