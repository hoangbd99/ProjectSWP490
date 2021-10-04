package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatrgotyRepository extends JpaRepository<Category, Long> {
}
