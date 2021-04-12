package com.test.URLMinifier.infrastructure;

import com.test.URLMinifier.model.entities.URLMap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLMapRepository extends CrudRepository<URLMap, String> {
}
