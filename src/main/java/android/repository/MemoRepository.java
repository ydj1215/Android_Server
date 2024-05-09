package android.repository;

import android.dto.MemoDTO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MemoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(MemoDTO memo) {
        String sql = "INSERT INTO memos (content) VALUES (?)";
        jdbcTemplate.update(sql, memo.getContent());
    }

    public List<MemoDTO> findAll() {
        String sql = "SELECT content FROM memos ORDER BY ID DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new MemoDTO(rs.getString("content")));
    }
}
