package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.PersonDtoToPersonMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Person;
import com.slack.geekbrainswork.ai.view.activities.PersonView;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class AddPersonPresenter extends BasePresenter {

    private static String BUNDLE_PERSON_KEY = "BUNDLE_PERSON_KEY";

    private Person person;
    private PersonView personView;
    private PersonDtoToPersonMapper mapper = new PersonDtoToPersonMapper();

    public AddPersonPresenter(PersonView view) {
        this.personView = view;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.person = (Person) savedInstanceState.getSerializable(BUNDLE_PERSON_KEY);
        }
    }

    public void onSaveButtonClick() {

        String personName = personView.getPersonNameTextViewText();
        PersonDTO personDTO = new PersonDTO(personName);
        if (personName == null || personName.isEmpty()) {
            personView.showError("Имя не должно быть пустым");
            return;
        }

        Subscription subscription = personRepository.createPerson(personDTO)
                .map(mapper)
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
