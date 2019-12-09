package admin;

import files.GeneralConfigFile;
import util.ContextOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet("/admin/general-conf")
public class GeneralConf extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GeneralConfigFile file = new GeneralConfigFile(request.getServletContext());
        request.setAttribute("file", file);
        request.getRequestDispatcher("/WEB-INF/admin/general-conf.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HashMap<String, String> configuration = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
//            System.out.println(paramName + " " + paramValues[0]);
            configuration.put(paramName, paramValues[0]);
        }
        String pathToConfig = ContextOperations.getPathToRoot(getServletContext().getRealPath("")) + "\\src\\main\\webapp\\WEB-INF\\admin\\config.xml";
        GeneralConfigFile file = new GeneralConfigFile(request.getServletContext());
        file.updateConfigFile(getServletContext(), configuration, pathToConfig);
        doGet(request, response);
    }
}
