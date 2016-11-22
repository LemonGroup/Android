package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.presenter.mappers.PersonDtoToPersonMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Person;
import com.slack.geekbrainswork.ai.view.activities.PersonView;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Prilepishev Vadim on 21.11.2016.
 */

public class UpdatePersonPresenter extends BasePresenter {
    private static String BUNDLE_PERSON_KEY = "BUNDLE_PERSON_KEY";

    private Person person;
    private PersonView personView;
    private PersonDtoToPersonMapper personMapper = new PersonDtoToPersonMapper();

    public UpdatePersonPresenter(PersonView view) {
        this.personView = view;
    }

    public void onCreate(Bundle savedInstanceState, Person person) {
        if (person != null) {
            this.person = person;
        }

        if (savedInstanceState != null) {
            this.person = (Person) savedInstanceState.getSerializable(BUNDLE_PERSON_KEY);
        }
    }

    public void onSaveButtonClick() {
        String siteName = personView.getPersonNameTextViewText();
        if (siteName == null || siteName.isEmpty()) {
            personView.showError("Название личности не должно быть пустым");
            return;
        }

        person.setName(siteName);

        Subscription subscription = personRepository.updatePerson(person)
                .map(personMapper)
                .subscribe(new Observer<Person>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        personView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Person person) {
                        personView.onClose(person);
                    }
                });

        addSubscription(subscription);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (person != null) {
            outState.putSerializable(BUNDLE_PERSON_KEY, person);
        }
    }

}
