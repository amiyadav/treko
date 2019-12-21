package com.treko.es.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "treko",type="customers",shards=2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	private int age;
}
