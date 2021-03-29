package Service;

import Repository.ArticleRepository;
import Repository.JdbcArticleRepository;
import domain.Article;

import java.util.List;

public class BoardService {
    private final ArticleRepository articleRepository;

    public BoardService() {
        articleRepository = new JdbcArticleRepository();
    }


    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
