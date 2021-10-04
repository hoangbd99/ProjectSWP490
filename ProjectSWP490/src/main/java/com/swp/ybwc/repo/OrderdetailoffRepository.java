package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Orderdetailoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderdetailoffRepository extends JpaRepository<Orderdetailoff, Long> {
}
