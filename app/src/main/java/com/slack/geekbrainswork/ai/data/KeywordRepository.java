package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import java.util.List;

import rx.Observable;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public interface KeywordRepository {

    Observable<List<KeywordDTO>> getKeywords();

    Observable<KeywordDTO> updateKeyword(Keyword keyword);

    Observable<KeywordDTO> createKeyword(String keywordName);

    Observable<List<KeywordDTO>> removeKeyword(Keyword keyword);

}
