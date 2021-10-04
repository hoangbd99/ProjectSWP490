package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Ordertable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdertableRepository extends JpaRepository<Ordertable, Long> {
}
