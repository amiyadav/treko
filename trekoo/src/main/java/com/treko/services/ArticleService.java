package com.treko.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.treko.es.model.Article;
import com.treko.es.repo.ArticleRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public Article save(Article article) {
		if (article.getId() == null) {
			article.setId(UUID.randomUUID().toString());
		}
		return articleRepository.save(article);
	}

	public Page<Article> getAll(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	public Optional<Article> getByLink(String link) {
		return articleRepository.findByLink(link);
	}

	public Optional<Article> getById(String id) {
		return articleRepository.findById(id);
	}

	public void deleteById(String id) {
		articleRepository.deleteById(id);
	}

	public Page<Article> search(String q, Pageable pageable) {
		return articleRepository.findByTitleContainingAndBodyContaining(q, q, pageable);
	}
}