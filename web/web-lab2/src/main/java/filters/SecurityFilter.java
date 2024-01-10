package filters;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/areaCheck", "/clearSessionHistory", "/controller", "/sessionHistory"})
public class SecurityFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        String referer = httpReq.getHeader("Referer");
        if (referer == null || !referer.contains("a")) {
            httpResp.setContentType("text/html; charset=UTF-8");
            httpResp.setCharacterEncoding("UTF-8");

            String message = "<div style='text-align:center; color:red;'>Доступ запрещен!!!</div>";
            httpResp.getWriter().write(message);

            return;
        }
        chain.doFilter(req, resp);
    }
}
