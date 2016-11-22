package com.slack.geekbrainswork.ai.view.fragments;

import com.slack.geekbrainswork.ai.presenter.vo.Person;
import com.slack.geekbrainswork.ai.view.View;

import java.util.List;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public interface PersonListView extends View {

    void navigateToUpdatePersonView(Person person);

    void showPersonList(List<Person> personList);

    void navigateToAddPersonView();

    void showRemovePersonDialog(Person person);

}
