package admin;

import files.HomePageConfigFile;
import util.ContextOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet("/admin/homepage-editor")
public class HomepageEditor extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomePageConfigFile file = new HomePageConfigFile(request.getServletContext());
        request.setAttribute("file", file);
        request.getRequestDispatcher("/WEB-INF/admin/homepage-editor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, String> configuration = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            configuration.put(paramName, paramValues[0]);
        }
        String pathToConfig = ContextOperations.getPathToRoot(getServletContext().getRealPath("")) + "\\src\\main\\webapp\\WEB-INF\\admin\\homepage-config.xml";
        HomePageConfigFile file = new HomePageConfigFile(request.getServletContext());
        file.updateConfigFile(getServletContext(), configuration, pathToConfig);
        doGet(request, response);
    }
}
