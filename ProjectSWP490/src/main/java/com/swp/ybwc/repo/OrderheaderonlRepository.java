package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Orderheaderonl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderheaderonlRepository extends JpaRepository<Orderheaderonl, Long> {
}
