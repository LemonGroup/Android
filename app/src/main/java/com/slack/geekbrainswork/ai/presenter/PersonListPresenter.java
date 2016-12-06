package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.PersonsDtoToPersonsMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Person;
import com.slack.geekbrainswork.ai.view.fragments.PersonListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class PersonListPresenter extends BasePresenter {

    private static String BUNDLE_PERSON_LIST_KEY = "BUNDLE_PERSON_LIST_KEY";

    private PersonListView personView;
    private List<Person> personList;
    private PersonsDtoToPersonsMapper personListMapper = new PersonsDtoToPersonsMapper();

    public PersonListPresenter(PersonListView personListView) {
        personView = personListView;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            personList = (List<Person>) savedInstanceState.getSerializable(BUNDLE_PERSON_LIST_KEY);
        }

        if (!isPersonListEmpty()) {
            personView.showPersonList(personList);
        } else {
            loadData();
        }
    }

    private void loadData() {
        Subscription subscription = personRepository.getPersons()
                .map(personListMapper)
                .subscribe(new Observer<List<Person>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        personView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Person> persons) {
                        personView.showPersonList(persons);
                    }
                });

        addSubscription(subscription);
    }

    public void onClickSite(Person person) {
        personView.navigateToUpdatePersonView(person);
    }

    public void onAddButtonClick() {
        personView.navigateToAddPersonView();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isPersonListEmpty()) {
            outState.putSerializable(BUNDLE_PERSON_LIST_KEY, new ArrayList<>(personList));
        }
    }

    private boolean isPersonListEmpty() {
        return personList == null || personList.isEmpty();
    }

    public void onActivityResult() {
        loadData();
    }

    public void onLongClickSite(Person person) {
        personView.showRemovePersonDialog(person);
    }

    public void onClickRemoveButton(Person person) {
        Subscription subscription = personRepository.removePerson(person.getId())
                .flatMap(new Func1<Void, Observable<List<PersonDTO>>>() {
                    @Override
                    public Observable<List<PersonDTO>> call(Void aVoid) {
                        return personRepository.getPersons();
                    }
                })
                .map(personListMapper)
                .subscribe(new Observer<List<Person>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Person> persons) {
                        personView.showPersonList(persons);
                    }
                });

        addSubscription(subscription);
    }

}
