package Service;

import Repository.ArticleRepository;
import Repository.JdbcArticleRepository;
import domain.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<Article> findOne(Long articleId) {
        return articleRepository.findById(articleId);
    }

    public Article flush(Article article) {
        return articleRepository.flush(article);
    }

    public List<Long> delete(Long articleId) {
        List<Long> list = articleRepository.selectRemoved(articleId);
        articleRepository.delete(articleId);
        return list;
    }

    public Map articleList(Map pagingMap) {
        Map map = new HashMap();
        List list = articleRepository.findAll(pagingMap);
        int totalArticles = articleRepository.selectToArticles();
        map.put("articleList",list);
        map.put("totalArticles",totalArticles);
        return map;
    }
}
