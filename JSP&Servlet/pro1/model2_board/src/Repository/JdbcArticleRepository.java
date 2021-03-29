package Repository;

import domain.Article;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcArticleRepository implements ArticleRepository{
    private DataSource dataSource;

    public JdbcArticleRepository() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            String query = "SELECT LEVEL,"
            + "ARTICLEID, PARENTID, LPAD(' ',4*(LEVEL-1)) || TITLE title, CONTENT, WRITEDDATE, ID"
            + " FROM BOARD START WITH PARENTID = 0 CONNECT BY PRIOR ARTICLEID=PARENTID ORDER SIBLINGS BY ARTICLEID DESC";

            System.out.println(query);
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Article article = setArticle(rs);
                articleList.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con,pstmt,rs);
        }
        return articleList;
    }

    @Override
    public Optional<Article> findById(Long id) {
        String query = "SELECT * FROM BOARD WHERE ID=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1,id);

            rs = pstmt.executeQuery();
            if(rs.next()) {
                Article article = setArticle(rs);
                return Optional.of(article);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    @Override
    public Article save(Article article) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "INSERT INTO BOARD (PARENTID, TITLE, CONTENT, IMAGEFILENAME, ID)"
                + "VALUES (?,?,?,?,?)";
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, article.getParentId());
            pstmt.setString(2,article.getTitle());
            pstmt.setString(3,article.getContent());
            pstmt.setString(4,article.getImageFileName());
            pstmt.setString(5,article.getId());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                article.setArticleId(rs.getLong(1));
            } else {
                throw new SQLException("article id 조회 실패");
            }
            return article;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(con,pstmt,rs);
        }
    }

    private Article setArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setLevel(rs.getLong("level"));
        article.setArticleId(rs.getLong("articleId"));
        article.setParentId(rs.getLong("parentId"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setId(rs.getString("id"));
        article.setWriteDate(rs.getDate("writedDate"));
        return article;
    }

    private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                close(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection con) throws  SQLException {
        con.close();
    }
}
