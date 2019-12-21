package com.treko.es.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.treko.es.model.Customers;

@Repository
public interface CustomersRepo extends ElasticsearchRepository<Customers, String> {

	List<Customers> findByFirstname(String firstname);

	Page<Customers> findByFirstname(String firstname, Pageable pageable);

	List<Customers> findByAge(int age);

}
