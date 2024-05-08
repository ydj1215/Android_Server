package android.servlet;

import android.dto.MemoDTO;
import android.repository.MemoRepository;
import android.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import util.DatabaseConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebServlet(name = "memoInsertServlet", urlPatterns = "/addMemo")
public class MemoInsertServlet extends HttpServlet {

    private MemoService memoService;

    // 의존성 주입 : but, 스프링 MVC 패턴이 아니라 서블릿 환경이기 때문에 수동으로 주입
    @Override
    public void init() throws ServletException {
        super.init(); // 상위 클래스, 즉 HttpServlet 의 init 메서드를 호출하는 것, 일반적으로는 없어도 아무 문제가 없지만, 명시적으로 호출해주는 습관을 가지라고 한다.
        DatabaseConfig config = new DatabaseConfig();
        memoService = new MemoService(new MemoRepository(config.jdbcTemplate(config.dataSource())));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        // 로깅 : 모든 헤더
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            log.warn("Header: " + headerName + " = " + headerValue);
        }

        // 로깅 : 모든 파라미터
        Enumeration<String> paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = req.getParameter(paramName);
            log.warn("Parameter: " + paramName + " = " + paramValue);
        }
         */

        String content = req.getParameter("memo");
        log.warn("zzzz : " + content);
        MemoDTO memo = new MemoDTO(content);
        memoService.addMemo(memo);
    }
}
