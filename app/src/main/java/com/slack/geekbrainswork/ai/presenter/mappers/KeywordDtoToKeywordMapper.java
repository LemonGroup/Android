package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import rx.functions.Func1;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class KeywordDtoToKeywordMapper implements Func1<KeywordDTO,Keyword> {

    @Override
    public Keyword call(KeywordDTO keywordDTO) {
        return new Keyword(keywordDTO.getId(),keywordDTO.getKeyword());
    }
}
