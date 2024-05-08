package android.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.dto.MemoDTO;
import android.repository.MemoRepository;
import android.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import util.DatabaseConfig;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

@Slf4j
@WebServlet(name = "MemoLoadServlet", urlPatterns = "/loadMemo")
public class MemoLoadServlet extends HttpServlet {

    private MemoService memoService;

    @Override
    public void init() throws ServletException {
        super.init();
        DatabaseConfig config = new DatabaseConfig();
        memoService = new MemoService(new MemoRepository(config.jdbcTemplate(config.dataSource())));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<MemoDTO> memoList = memoService.getMemos();
            Gson gson = new Gson();

            // 메모 목록을 JSON 형태로 변환
            String json = gson.toJson(memoList);

            // JSON 응답 보내기
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } catch (Exception e) {
            log.error("Memo loading failed", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
