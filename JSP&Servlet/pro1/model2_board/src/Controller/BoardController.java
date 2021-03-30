package Controller;

import Service.BoardService;
import domain.Article;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "BoardController", value = "/board/*")
public class BoardController extends HttpServlet {
    private String ARTICLE_IMAGE_REPO;
    private BoardService boardService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ARTICLE_IMAGE_REPO = getServletContext().getInitParameter("ARTICLE_IMAGE_REPO");
        boardService = new BoardService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = "";
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getPathInfo();
        System.out.println("action : " + action);

        try {
            List<Article> articleList = boardService.findArticles();
            System.out.println("articleList");
            for (Article i : articleList) {
                System.out.println("articleTitle : " + i.getTitle());
            }
            if (action == null) {
                Map map = getArticleSection(request, response);
                request.setAttribute("articleMap", map);
                nextPage = "/board0/articleList.jsp";
            } else if (action.equals("/articleList.do")) {
                Map map = getArticleSection(request, response);
                request.setAttribute("articleMap", map);
                nextPage = "/board0/articleList.jsp";
            } else if (action.equals("/articleForm.do")) {
                nextPage = "/board0/articleForm.jsp";
            } else if (action.equals("/save.do")) {
                Article article = getArticle(request, response);
                article.setParentId(0);
                article.setId("lee");
                boardService.save(article);

                upsertImage(article);

                PrintWriter pw = response.getWriter();
                pw.print("<script type='text/javascript'> alert('새 글을 작성했습니다.')</script>");
                nextPage = "/board/articleList.do";
            } else if (action.equals("/articleView.do")) {
                String articleId = request.getParameter("articleId");
                System.out.println("article Id : " + articleId);
                Optional<Article> article = boardService.findOne(Long.parseLong(articleId));
                if (article.isPresent()) {
                    request.setAttribute("article", article.get());
                    nextPage = "/board0/articleView.jsp";
                } else {
                    PrintWriter pw = response.getWriter();
                    pw.print("<script> alert('잘못된 접근입니다.') </script>");
                    nextPage = "/board/articleList.do";
                }
            } else if (action.equals("/articleModify.do")){
                Article article = getArticle(request, response);

                boardService.flush(article);

                upsertImage(article);

                PrintWriter pw = response.getWriter();
                pw.print("<script type='text/javascript'> alert('새 글을 작성했습니다.')</script>");
                nextPage = "/board/articleList.do";
            } else if (action.equals("/articleRemove.do")) {
                Long articleId = Long.parseLong(request.getParameter("articleId"));
                List<Long> articleIdList = boardService.delete(articleId);
                for(long i : articleIdList) {
                    File imgDir = new File(ARTICLE_IMAGE_REPO+"\\"+i);
                    if(imgDir.exists())
                        FileUtils.deleteDirectory(imgDir);
                }

                // 잘못된 article id 온 경우 처리 x
                PrintWriter pw = response.getWriter();
                pw.print("<script type='text/javascript'> alert('삭제를 완료했습니다.')</script>");
                nextPage = "/board/articleList.do";

            } else if(action.equals("/replyForm.do")) {
                Long parentId = Long.parseLong(request.getParameter("parentId"));
                HttpSession session = request.getSession();
                session.setAttribute("parentId", parentId);
                nextPage = "/board0/replyForm.jsp";
            } else if (action.equals("/addReply.do")){
                HttpSession session = request.getSession();
                Long parentId = (Long) session.getAttribute("parentId");
                session.removeAttribute("parentId");

                Article article = getArticle(request, response);
                article.setParentId(parentId);
                article.setId("admin");

                boardService.save(article);

                upsertImage(article);
                PrintWriter pw = response.getWriter();
                pw.print("<script type='text/javascript'> alert('답글 작성 완료.')</script>");
                nextPage = "/board/articleList.do";


            } else {
                nextPage = "/board0/articleList.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request,response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> upload (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> articleMap = new HashMap<>();
        String encoding = "utf-8";
        File currentDirPath = new File(ARTICLE_IMAGE_REPO);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(currentDirPath);
        factory.setSizeThreshold(1024*1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List items = upload.parseRequest(request);
            for(int i=0;i<items.size();i++) {
                FileItem fileItem = (FileItem) items.get(i);
                if(fileItem.isFormField()) {
                    articleMap.put(fileItem.getFieldName(),fileItem.getString(encoding));
                } else {
                    articleMap.put(fileItem.getFieldName(), fileItem.getName());

                    /**
                     * 업로드한 파일이 존재하는 경우 업로드한 파일의 이름으로 저장소 업로드
                     */
                    if (fileItem.getSize() > 0) {
                        int idx = fileItem.getName().lastIndexOf("\\");
                        if (idx == -1) {
                            idx = fileItem.getName().lastIndexOf("/");
                        }

                        String fileName = fileItem.getName().substring(idx + 1);
                        File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
                        fileItem.write(uploadFile);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articleMap;
    }

    private Article getArticle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> articleMap = upload(request, response);

        Article article = new Article();
        article.setTitle(articleMap.get("title"));
        article.setContent(articleMap.get("content"));
        article.setImageFileName(articleMap.get("imageFileName"));

        return article;
    }

    private Map getArticleSection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempSection = request.getParameter("section");
        String tempPageNum = request.getParameter("pageNum");
        int section = (tempSection == null) ? 1 : Integer.parseInt(tempSection);
        int pageNum = (tempPageNum == null) ? 1 : Integer.parseInt(tempPageNum);

        Map pagingMap = new HashMap();
        pagingMap.put("section",section);
        pagingMap.put("pageNum",pageNum);

        Map articleMap = boardService.articleList(pagingMap);
        articleMap.put("section", section);
        articleMap.put("pageNum", pageNum);

        return articleMap;
    }
    /**
     * {ARTICLE_IAMGE_REPO}\\TEMP 에 저장된 이미지를 {ARTICLE_IMAGE_REPO}\\ARTICLEID 에 옮겨저장
     * @param article
     * @throws IOException
     */
    private void upsertImage(Article article) throws IOException{
        // ARTICLE ID 로 폴더 생성 후 TEMP -> ATRICLE_ID로 IMAGE 파일 이동
        if (article.getImageFileName() != null && article.getImageFileName().length() != 0) {
            File srcFile = new File(ARTICLE_IMAGE_REPO + "\\temp\\" + article.getImageFileName());
            File destFile = new File(ARTICLE_IMAGE_REPO + "\\" + article.getArticleId());
            destFile.mkdir();
            FileUtils.moveFileToDirectory(srcFile, destFile, true);
        }
    }
}
