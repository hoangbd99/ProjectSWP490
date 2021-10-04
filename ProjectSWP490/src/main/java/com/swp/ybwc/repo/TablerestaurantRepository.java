package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Tablerestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablerestaurantRepository extends JpaRepository<Tablerestaurant, Long> {
}
