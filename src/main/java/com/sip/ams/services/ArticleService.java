package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import com.sip.ams.entities.Article;
import com.sip.ams.dto.ArticleDTO;

public interface ArticleService {  // CRUD
	public List<Article> getAllArticles(); // select R(Read)
	public Article saveArticle(Article article); // insert ou bien update C(Create|Update)
	public Optional<Article> getArticleById(int id);  //Select
	public void deleteArticleById(int id);  // Delete
	public Article updateArticle(ArticleDTO articleModel); // update d'un article

}
