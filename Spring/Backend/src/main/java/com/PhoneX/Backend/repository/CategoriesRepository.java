package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
    boolean existsByCategoriesName(String categoriesName);

    Optional<Categories> findById(long categories);
}
