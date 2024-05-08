package android.service;

import android.dto.MemoDTO;
import android.repository.MemoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class MemoService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final MemoRepository memoRepository;
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public void addMemo(MemoDTO memo) {
        memoRepository.insert(memo);
    }

    public List<MemoDTO> getMemos() throws JsonProcessingException {
        List<MemoDTO> temp = memoRepository.findAll();
        String json = objectMapper.writeValueAsString(temp);
        log.warn("zzzz" + json);
        return temp;
    }
}
