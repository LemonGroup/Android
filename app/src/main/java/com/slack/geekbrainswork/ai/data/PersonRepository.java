package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import java.util.List;

import rx.Observable;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public interface PersonRepository {

    Observable<List<PersonDTO>> getPersons();

    Observable<PersonDTO> updatePerson(Person person);

    Observable<PersonDTO> createPerson(String personName);

    Observable<List<PersonDTO>> removePerson(Person person);

}
