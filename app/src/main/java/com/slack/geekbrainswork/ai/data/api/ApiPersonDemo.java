package com.slack.geekbrainswork.ai.data.api;

import android.util.Log;

import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class ApiPersonDemo {

    private static ApiPersonDemo personApi;
    private static List<PersonDTO> personDTOList = new ArrayList<>();

    private ApiPersonDemo() {
        personDTOList.add(new PersonDTO(1, "Путин"));
        personDTOList.add(new PersonDTO(2, "Медведев"));
    }

    public static ApiPersonDemo getPersonApi() {
        if (personApi == null) {
            personApi = new ApiPersonDemo();
        }
        return personApi;
    }

    public List<PersonDTO> getPersonDTOList() {
        Log.d("GET ", Thread.currentThread().getName());
        return personDTOList;
    }

    public PersonDTO updatePersonDTO(Person person) {
        Log.d("UPDATE ", Thread.currentThread().getName());
        for (int i = 0; i < personDTOList.size(); i++) {
            if (personDTOList.get(i).getId().intValue() == person.getId().intValue()) {
                personDTOList.get(i).setName(person.getName());
                return personDTOList.get(i);
            }
        }
        return null;
    }

    public PersonDTO createPersonDTO(String personName) {
        Log.d("CREATE ", Thread.currentThread().getName());
        PersonDTO personDTO = new PersonDTO(getMaxId() + 1, personName);
        personDTOList.add(personDTO);
        return personDTO;
    }

    private Integer getMaxId() {
        Integer maxId = 0;
        Integer curId;
        for (PersonDTO personDTO : personDTOList) {
            curId = personDTO.getId();
            if (curId > maxId) {
                maxId = curId;
            }
        }
        return maxId;
    }

    public List<PersonDTO> removePersonDTO(Person person) {
        Log.d("REMOVE ", Thread.currentThread().getName());
        for (Iterator<PersonDTO> iterator = personDTOList.iterator(); iterator.hasNext(); ) {
            PersonDTO siteDTO = iterator.next();
            if (siteDTO.getId().intValue() == person.getId().intValue()) {
                iterator.remove();
                return personDTOList;
            }
        }
        return personDTOList;
    }

}
