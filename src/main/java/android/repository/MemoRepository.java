package android.repository;

import android.dto.MemoDTO;
import org.springframework.jdbc.core.JdbcTemplate;

public class MemoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(MemoDTO memo) {
        String sql = "INSERT INTO memos (content) VALUES (?)";
        jdbcTemplate.update(sql, memo.getContent());
    }
}
