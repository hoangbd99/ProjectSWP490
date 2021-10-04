package com.swp.ybwc.repo;

import com.swp.ybwc.domain.Feedbackonl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackonlRepository extends JpaRepository<Feedbackonl, Long> {
}
