package com.slack.geekbrainswork.ai.view.activities;

import com.slack.geekbrainswork.ai.presenter.vo.Person;
import com.slack.geekbrainswork.ai.view.View;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public interface PersonView extends View {

    String getPersonNameTextViewText();

    void onClose(Person person);

}
