package com.janissary.fundraising.repository;

import com.janissary.fundraising.model.CollectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionBoxRepository extends JpaRepository<CollectionBox, Long> {
}
