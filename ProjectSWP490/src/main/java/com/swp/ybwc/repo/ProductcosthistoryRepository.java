package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Productcosthistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductcosthistoryRepository extends JpaRepository<Productcosthistory, Long> {
}
