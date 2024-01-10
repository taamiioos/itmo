package ok;

import com.google.gson.Gson;
import model.Point;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/sessionHistory")
public class SessionHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            List<Point> sessionList = (List<Point>) session.getAttribute("sessionList");
            Gson gson = new Gson();
            String json = gson.toJson(sessionList);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else {
            response.setStatus(404);
        }
    }
}
