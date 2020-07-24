package jdbc.dao;

import java.util.List;

import jdbc.entities.Article;

public interface ArticleDao {

	List<Article> extraire();
	
	void insert(Article article);
	
	int update(Article articleModifie);
	
	boolean delete(Article article);
	
}
