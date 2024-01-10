package ok;

import com.google.gson.Gson;
import model.Point;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@WebServlet(value = "/areaCheck")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        double x, y, r;
        try {
            x = Double.parseDouble(req.getParameter("x"));
            y = Double.parseDouble(req.getParameter("y"));
            r = Double.parseDouble(req.getParameter("r"));
            if (!validateParam(x, y, r)) {
                resp.setStatus(400);
                return;
            }
            long start = System.nanoTime();
            String flag = check(x, y, r) ? "TRUE" : "FALSE";
            TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.setTimeZone(timeZone);
            String time = dateFormat.format(new Date());
            String scriptTime = String.format("%.3f", (double) (System.nanoTime() - start) * 0.0001);
            HttpSession session = req.getSession(true);
            List<Point> sessionList = (List<Point>) session.getAttribute("sessionList");
            if (sessionList == null) {
                sessionList = new ArrayList<>();
                session.setAttribute("sessionList", sessionList);
            }
            Point sessionData = new Point(x, y, r, flag, time, scriptTime);
            sessionList.add(sessionData);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().write(e.getMessage());
        }
    }
    private boolean validateParam(double x, double y, double r) {
        return x >= -5 && x <= 3 && y >= -3 && y <= 5 && r >= 1 && r <= 3;
    }
    public boolean circle(double x, double y, double r) {
        return (x <= 0 && y <= 0 && (x * x + y * y) <= -(r) * -(r));
    }
    public boolean square(double x, double y, double r) {
        return (x >= 0 && y <= 0 && x <= r && y >= -(r / 2));
    }
    public boolean triangle(double x, double y, double r) {
        return (x <= 0 && y >= 0 && x >= -r && y <= r);
    }
    public boolean check(double x, double y, double r) {
        return circle(x, y, r) || triangle(x, y, r) || square(x, y, r);
    }
}
