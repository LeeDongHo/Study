package Repository;

import domain.Article;

import javax.management.Query;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
                article.setLevel(rs.getLong("level"));
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
    public List<Article> findAll(Map pagingMap) {
        List<Article> list = new ArrayList();
        int section = (int) pagingMap.get("section");
        int pageNum = (int) pagingMap.get("pageNum");

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM ( SELECT ROWNUM, Lv, ARTICLEID, PARENTID, TITLE, ID, WRITEDDATE " +
                "FROM (SELECT LEVEL as Lv, ARTICLEID, PARENTID, TITLE, ID, WRITEDDATE FROM BOARD START WITH PARENTID = 0 " +
                "CONNECT BY PRIOR ARTICLEID = PARENTID ORDER SIBLINGS BY ARTICLEID DESC))" +
                "WHERE ROWNUM BETWEEN(?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,section);
            pstmt.setInt(2,pageNum);
            pstmt.setInt(3,section);
            pstmt.setInt(4,pageNum);

            rs = pstmt.executeQuery();
            while(rs.next()){
                Article article = setArticlePreview(rs);
                article.setLevel(rs.getLong("Lv"));
                list.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con,pstmt,rs);
        }
        return list;
    }

    @Override
    public Optional<Article> findById(Long id) {
        String query = "SELECT * FROM BOARD WHERE ARTICLEID=?";
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
            pstmt = con.prepareStatement(query,  new String[]{"ARTICLEID"});
            pstmt.setLong(1, article.getParentId());
            pstmt.setString(2,article.getTitle());
            pstmt.setString(3,article.getContent());
            pstmt.setString(4,article.getImageFileName());
            pstmt.setString(5,article.getId());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                article.setArticleId((rs.getBigDecimal(1)).longValue());
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

    @Override
    public Article flush(Article article) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "UPDATE BOARD SET TITLE=?, CONTENT =?";
        if(article.getImageFileName() != null && article.getImageFileName().length() != 0)
            query += ", IMAGEFILENAME=?";
        query += " WHERE ARTICLEID = ?";

        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query, new String[]{"ARTICLEID"});
            int index = 1;
            pstmt.setString(index++,article.getTitle());
            pstmt.setString(index++,article.getContent());
            if(article.getImageFileName() != null && article.getImageFileName().length() != 0)
                pstmt.setString(index++,article.getImageFileName());
            pstmt.setLong(index++,article.getArticleId());

            pstmt.executeUpdate();
            return article;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(con,pstmt,rs);
        }
    }

    @Override
    public void delete(Long articleId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "DELETE FROM BOARD WHERE ARTICLEID IN( " +
                "SELECT ARTICLEID FROM BOARD " +
                "START WITH ARTICLEID = ? " +
                "CONNECT BY PRIOR ARTICLEID = PARENTID)";
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1,articleId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(con,pstmt,rs);
        }
    }

    @Override
    public List<Long> selectRemoved(Long articleId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Long> list = new ArrayList<>();

        String query = "SELECT ARTICLEID FROM BOARD START WITH ARTICLEID = ? " +
                "CONNECT BY PRIOR ARTICLEID = PARENTID";
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1,articleId);

            rs = pstmt.executeQuery();
            if(rs.next()) {
                long resultId = rs.getLong("articleId");
                list.add(resultId);
            } else {
                throw new SQLException("article id 조회 실패");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(con,pstmt,rs);
        }
        return list;
    }

    @Override
    public int selectToArticles() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT COUNT(ARTICLEID) FROM BOARD";
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con,pstmt,rs);
        }
        return 0;
    }

    private Article setArticlePreview(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setArticleId(rs.getLong("articleId"));
        article.setParentId(rs.getLong("parentId"));
        article.setTitle(rs.getString("title"));
        article.setId(rs.getString("id"));
        article.setWriteDate(rs.getDate("writedDate"));
        return article;
    }

    private Article setArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
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
