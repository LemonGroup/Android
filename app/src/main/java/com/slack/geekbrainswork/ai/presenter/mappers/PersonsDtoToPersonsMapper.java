package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class PersonsDtoToPersonsMapper implements Func1<List<PersonDTO>,List<Person>> {
    @Override
    public List<Person> call(List<PersonDTO> personDTOs) {
        return Observable.from(personDTOs)
                .map(new PersonDtoToPersonMapper())
                .toList()
                .toBlocking()
                .first();
    }
}
