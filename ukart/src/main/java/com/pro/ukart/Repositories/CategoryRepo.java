package com.pro.ukart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pro.ukart.Entities.*;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>{
    
}
