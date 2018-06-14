package com.jhipster.application.repository.search;

import com.jhipster.application.domain.Task2;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Task2 entity.
 */
public interface Task2SearchRepository extends ElasticsearchRepository<Task2, Long> {
}
