package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import rx.functions.Func1;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class PersonDtoToPersonMapper implements Func1<PersonDTO,Person> {

    @Override
    public Person call(PersonDTO personDTO) {
        return new Person(personDTO.getId(),personDTO.getName());
    }
}
