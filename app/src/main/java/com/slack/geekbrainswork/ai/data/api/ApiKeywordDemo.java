package com.slack.geekbrainswork.ai.data.api;

import android.util.Log;

import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class ApiKeywordDemo {

    private static ApiKeywordDemo keywordApi;
    private static List<KeywordDTO> keywordDTOList = new ArrayList<>();

    private ApiKeywordDemo() {
        keywordDTOList.add(new KeywordDTO(1, "Путин"));
        keywordDTOList.add(new KeywordDTO(2, "Медведев"));
    }

    public static ApiKeywordDemo getkeywordApi() {
        if (keywordApi == null) {
            keywordApi = new ApiKeywordDemo();
        }
        return keywordApi;
    }

    public List<KeywordDTO> getKeywordDTOList() {
        Log.d("GET ", Thread.currentThread().getName());
        return keywordDTOList;
    }

    public KeywordDTO updateKeywordDTO(Keyword keyword) {
        Log.d("UPDATE ", Thread.currentThread().getName());
        for (int i = 0; i < keywordDTOList.size(); i++) {
            if (keywordDTOList.get(i).getId().intValue() == keyword.getId().intValue()) {
                keywordDTOList.get(i).setKeyword(keyword.getKeyword());
                return keywordDTOList.get(i);
            }
        }
        return null;
    }

    public KeywordDTO createKeywordDTO(String KeywordName) {
        Log.d("CREATE ", Thread.currentThread().getName());
        KeywordDTO KeywordDTO = new KeywordDTO(getMaxId() + 1, KeywordName);
        keywordDTOList.add(KeywordDTO);
        return KeywordDTO;
    }

    private Integer getMaxId() {
        Integer maxId = 0;
        Integer curId;
        for (KeywordDTO KeywordDTO : keywordDTOList) {
            curId = KeywordDTO.getId();
            if (curId > maxId) {
                maxId = curId;
            }
        }
        return maxId;
    }

    public List<KeywordDTO> removeKeywordDTO(Keyword Keyword) {
        Log.d("REMOVE ", Thread.currentThread().getName());
        for (Iterator<KeywordDTO> iterator = keywordDTOList.iterator(); iterator.hasNext(); ) {
            KeywordDTO siteDTO = iterator.next();
            if (siteDTO.getId().intValue() == Keyword.getId().intValue()) {
                iterator.remove();
                return keywordDTOList;
            }
        }
        return keywordDTOList;
    }

}
