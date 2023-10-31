package servlets;

import com.google.gson.Gson;
import dots.Dot;
import dots.DotsStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/areaCheck")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        requestHandler(request, response);
    }

    public void requestHandler(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            var x = Double.parseDouble(request.getParameter("x"));
            var y = Double.parseDouble(request.getParameter("y"));
            var r = Double.parseDouble(request.getParameter("r"));
            var newDot = new Dot(x, y, r);
            newDot.setHit(isInArea(newDot));

            var session = request.getSession();

            var dots = (DotsStorage) session.getAttribute("dots");
            if(dots == null) {
                dots = new DotsStorage();
                session.setAttribute("dots", dots);
            }
            dots.addDot(newDot);

            var action = request.getParameter("action");
            if(action.equals("checkParam")){
                request.setAttribute("x", x);
                request.setAttribute("y", y);
                request.setAttribute("r", r);
                request.setAttribute("result", newDot.isHit());
                request.setAttribute("time", newDot.getTime());

                response.setContentType("text/html");

                ServletContext servletContext = getServletContext();
                servletContext.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);


            } else if(action.equals("checkDot")){
                var gson = new Gson();
                Map<String, Object> json = new HashMap<>();
                json.put("x", x);
                json.put("y", y);
                json.put("r", r);
                json.put("result", newDot.isHit());
                json.put("time", newDot.getTime());


                var message = gson.toJson(json);


                response.setContentType("application/json");
                response.getWriter().write(message);

            }

        } catch (Exception e){
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        }

    }

    private boolean isInArea(Dot dot){
        Double x = dot.getX();
        Double y = dot.getY();
        Double r = dot.getR();
        return (-r <= x && x <= 0 && 0 <= y && y <= r) ||
                (y >= -(x / 2) - (r / 2) && y <= 0 && x <= 0) ||
                (x >= 0 && y <= 0 && (x * x + y * y <= r * r / 4));
    }

}
