package android.service;

import android.dto.MemoDTO;
import android.repository.MemoRepository;

public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public void addMemo(MemoDTO memo) {
        memoRepository.insert(memo);
    }
}
