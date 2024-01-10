package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final String SECRET = "f12e13";
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
//        String header = httpReq.getHeader("Authorization");
        String header = SECRET;
        if (header != null && header.equals(SECRET)) {
            chain.doFilter(req, resp);
        } else {
            httpResp.setStatus(401);
        }
    }
}