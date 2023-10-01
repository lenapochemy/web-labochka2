import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    @Override void doGet(HttpServletRequest request, HttpServletResponse response){
        throws ServletException, IOException{
            try{
                var x = Double.parseDouble(request.getParameter("x"));
                var y = Double.parseDouble(request.getParameter("y"));
                var r = Double.parseDouble(request.getParameter("r"));
                Dot newDot = new Dot(x, y, r);
                boolean


            }
        }
    }



}
