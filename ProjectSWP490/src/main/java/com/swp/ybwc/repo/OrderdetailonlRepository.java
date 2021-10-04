package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Orderdetailonl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderdetailonlRepository extends JpaRepository<Orderdetailonl, Long> {
}
