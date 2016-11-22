package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiClient;
import com.slack.geekbrainswork.ai.data.api.ApiPersonInterface;
import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class PersonRepositoryImpl implements PersonRepository {

    private final Observable.Transformer schedulersTransformer;
    private ApiPersonInterface apiPersonInterface = ApiClient.getPersonApiInterface();

    public PersonRepositoryImpl() {
        schedulersTransformer = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public Observable<List<PersonDTO>> getPersons() {
        //ToDo getPersons from Rest
        return apiPersonInterface.getPersons()
                .compose(this.<List<PersonDTO>>applySchedulers());
    }

    @Override
    public Observable<PersonDTO> updatePerson(final Person person) {
        //ToDo updatePerson by Rest
        return apiPersonInterface.updatePerson(person.getId(), person.getName())
                .compose(this.<PersonDTO>applySchedulers());
    }

    @Override
    public Observable<PersonDTO> createPerson(final String personName) {
        //ToDo createPerson by Rest
        return apiPersonInterface.createPerson(personName)
                .compose(this.<PersonDTO>applySchedulers());
    }

    @Override
    public Observable<List<PersonDTO>> removePerson(final Person person) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
