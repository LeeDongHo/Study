package Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "FileDownloadController", value = "/download.do")
public class FileDownloadController extends HttpServlet {
    private String ARTICLE_IMAGE_REPO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ARTICLE_IMAGE_REPO = getServletConfig().getInitParameter("ARTICLE_IMAGE_REPO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String imageFileName = request.getParameter("imageFileName");
        long articleId = Long.parseLong(request.getParameter("articleId"));
        OutputStream out = response.getOutputStream();

        String path = ARTICLE_IMAGE_REPO + "\\" + articleId + "\\" + imageFileName;
        File imageFile = new File(path);

        response.setHeader("Cache-Control","no-cache");
        response.addHeader("Content-disposition","attachment;fileName="+imageFileName);
        FileInputStream in = new FileInputStream(imageFile);
        byte[] buffer = new byte[(int)in.getChannel().size()];
        while (true) {
            int count = in.read(buffer);
            if(count == -1)
                break;
            out.write(buffer,0,count);
        }
        in.close();
        out.close();
    }
}
