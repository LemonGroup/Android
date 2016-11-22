package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiPersonDemo;
import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class RepositoryPersonDemo implements PersonRepository {

    private final Observable.Transformer schedulersTransformer;
    private ApiPersonDemo personApi = ApiPersonDemo.getPersonApi();

    public RepositoryPersonDemo() {
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
        return Observable.defer(new Func0<Observable<List<PersonDTO>>>() {
            @Override
            public Observable<List<PersonDTO>> call() {
                return Observable.just(personApi.getPersonDTOList());
            }
        })
                .compose(this.<List<PersonDTO>>applySchedulers());
    }

    @Override
    public Observable<PersonDTO> updatePerson(final Person person) {
        return Observable.defer(new Func0<Observable<PersonDTO>>() {
            @Override
            public Observable<PersonDTO> call() {
                return Observable.just(personApi.updatePersonDTO(person));
            }
        })
                .compose(this.<PersonDTO>applySchedulers());
    }

    @Override
    public Observable<PersonDTO> createPerson(final String personName) {
        return Observable.defer(new Func0<Observable<PersonDTO>>() {
            @Override
            public Observable<PersonDTO> call() {
                return Observable.just(personApi.createPersonDTO(personName));
            }
        })
                .compose(this.<PersonDTO>applySchedulers());
    }

    @Override
    public Observable<List<PersonDTO>> removePerson(final Person person) {
        return Observable.defer(new Func0<Observable<List<PersonDTO>>>() {
            @Override
            public Observable<List<PersonDTO>> call() {
                return Observable.just(personApi.removePersonDTO(person));
            }
        })
                .compose(this.<List<PersonDTO>>applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
