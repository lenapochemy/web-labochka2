package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            if(request.getParameter("x") == null ||
                    request.getParameter("y") == null ||
                    request.getParameter("r") == null ||
                    request.getParameter("x").isEmpty() ||
                    request.getParameter("y").isEmpty() ||
                    request.getParameter("r").isEmpty()
            ){
                error("incorrect data", response);
                return;
            }

            response.sendRedirect("./areaCheck?" + request.getQueryString());

        } catch (Exception e){
            error(e.toString(), response);
        }
    }


    private void error(String message, HttpServletResponse response) throws IOException {
        var json = new Gson();
        Map<String, Object> jsonResponse = new HashMap<>() {{
            put("error", message);
            put("status", "UNPROCESSABLE_ENTITY");
        }};

        response.setContentType("application/json");
        response.getWriter().write(json.toJson(jsonResponse));
        response.setStatus(400);
    }
}