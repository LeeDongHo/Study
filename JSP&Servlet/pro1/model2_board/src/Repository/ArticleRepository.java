package Repository;

import domain.Article;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticleRepository {
    List<Article> findAll();
    List<Article> findAll(Map pagingMap);
    Optional<Article> findById(Long id);
    Article save(Article article);
    Article flush(Article article);
    void delete(Long articleId);
    List<Long> selectRemoved(Long articleId);

    int selectToArticles();
}
