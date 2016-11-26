package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class KeywordsDtoToKeywordsMapper implements Func1<List<KeywordDTO>,List<Keyword>> {
    @Override
    public List<Keyword> call(List<KeywordDTO> keywordDTOs) {
        return Observable.from(keywordDTOs)
                .map(new KeywordDtoToKeywordMapper())
                .toList()
                .toBlocking()
                .first();
    }
}
