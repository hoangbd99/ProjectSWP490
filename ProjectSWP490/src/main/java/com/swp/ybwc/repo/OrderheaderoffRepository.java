package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Orderheaderoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderheaderoffRepository extends JpaRepository<Orderheaderoff, Long> {
}
