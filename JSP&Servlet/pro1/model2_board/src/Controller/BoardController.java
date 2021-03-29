package Controller;

import Service.BoardService;
import domain.Article;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BoardController", value = "/board/*")
public class BoardController extends HttpServlet {
    private static String ARTICLE_IMAGE_REPO = "../web/article_image";
    private final BoardService boardService;

    public BoardController() {
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

        try{
            List<Article> articleList = boardService.findArticles();
            System.out.println("articleList");
            for(Article i : articleList) {
                System.out.println("articleTitle : " + i.getTitle());
            }
            if(action == null) {
                request.setAttribute("articleList",articleList);
                nextPage = "/board0/articleList.jsp";
            } else if (action.equals("/articleList.do")) {
                request.setAttribute("articleList",articleList);
                nextPage = "/board0/articleList.jsp";
            } else if (action.equals("/articleForm.do")){
                nextPage = "/board0/articleForm.jsp";
            } else if(action.equals("/save.do")) {
                Map<String, String> articleMap = upload(request, response);

                Article article = new Article();
                article.setParentId(0);
                article.setId("lee");
                article.setTitle(articleMap.get("title"));
                article.setContent(articleMap.get("content"));
                article.setImageFileName(articleMap.get("imageFileName"));

                boardService.save(article);
                nextPage="/board0/articleList.do";
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
                    System.out.println(fileItem.getFieldName() +"=" +fileItem.getString(encoding));
                    articleMap.put(fileItem.getFieldName(),fileItem.getString(encoding));
                } else {
                    System.out.println("파라미터 이름 : " + fileItem.getFieldName());
                    System.out.println("파일 이름 : " + fileItem.getName());
                    System.out.println("파일 크기 : " + fileItem.getSize()+"bytes");
                    articleMap.put(fileItem.getFieldName(),fileItem.getName());
                }

                /**
                 * 업로드한 파일이 존재하는 경우 업로드한 파일의 이름으로 저장소 업로드
                 */
                if(fileItem.getSize() > 0) {
                    int idx = fileItem.getName().lastIndexOf("\\");
                    if(idx == -1) {
                        idx = fileItem.getName().lastIndexOf("/");
                    }

                    String fileName = fileItem.getName().substring(idx+1);
                    File uploadFile = new File(currentDirPath+"\\"+fileName);
                    fileItem.write(uploadFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articleMap;
    }
}
