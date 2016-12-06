package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.LemonStateAdminApp;
import com.slack.geekbrainswork.ai.data.api.ApiClient;
import com.slack.geekbrainswork.ai.data.api.ApiInterface;
import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.local.PrefHelper;
import com.slack.geekbrainswork.ai.data.local.PreferencesHelper;
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
    private ApiInterface apiInterface = ApiClient.getApiInterface();
    private ApiInterface loginApiInterface;
    private PrefHelper helper = new PreferencesHelper(LemonStateAdminApp.getContext());

    public PersonRepositoryImpl() {
        schedulersTransformer = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
        loginApiInterface = ApiClient.getApiInterface();
        apiInterface = ApiClient.getApiInterface(getTokenFromStorage());
    }

    @Override
    public Observable<TokenResponse> auth(String login, String password) {
        return loginApiInterface.auth(login, password)
                .compose(this.<TokenResponse>applySchedulers());
    }

    @Override
    public String getTokenFromStorage() {
        return helper.getFromPref();
    }

    @Override
    public Observable<List<PersonDTO>> getPersons() {
        return apiInterface.getPersons()
                .compose(this.<List<PersonDTO>>applySchedulers());
    }

    @Override
    public Observable<PersonDTO> updatePerson(PersonDTO person) {
        return apiInterface.updatePerson(person)
                .compose(this.<PersonDTO>applySchedulers());
    }

    @Override
    public Observable<PersonDTO> createPerson(final PersonDTO person) {
        return apiInterface.createPerson(person)
                .compose(this.<PersonDTO>applySchedulers());
    }

    @Override
    public Observable<Void> removePerson(int id) {
        return apiInterface.removePerson(id)
                .compose(this.<Void>applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
