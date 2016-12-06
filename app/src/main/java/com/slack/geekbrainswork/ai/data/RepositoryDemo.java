package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiDemo;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.data.local.PrefHelper;
import com.slack.geekbrainswork.ai.data.local.PreferencesDemo;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class RepositoryDemo implements Repository {

    private final Observable.Transformer schedulersTransformer;
    private ApiDemo api = ApiDemo.getApi();
    private PrefHelper helper = PreferencesDemo.getPreferences();

    public RepositoryDemo() {
        schedulersTransformer = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public String getTokenFromStorage() {
        return helper.getFromPref();
    }

    @Override
    public void updateToken(final String token) {
        helper.writeToPref(token);
    }

    @Override
    public Observable<List<SiteDTO>> getSites() {
        return Observable.defer(new Func0<Observable<List<SiteDTO>>>() {
            @Override
            public Observable<List<SiteDTO>> call() {
                return Observable.just(api.getSiteDTOList());
            }
        })
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(final SiteDTO siteDTO) {
        return Observable.defer(new Func0<Observable<SiteDTO>>() {
            @Override
            public Observable<SiteDTO> call() {
                return Observable.just(api.updateSiteDTO(siteDTO));
            }
        })
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> createSite(final SiteDTO siteDTO) {
        return Observable.defer(new Func0<Observable<SiteDTO>>() {
            @Override
            public Observable<SiteDTO> call() {
                return Observable.just(api.createSiteDTO(siteDTO));
            }
        })
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<Void> deleteSite(final int id) {
        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                return Observable.just(api.deleteSiteDTO(id));
            }
        })
                .compose(this.<Void>applySchedulers());
    }

    @Override
    public Observable<TokenResponse> auth(final String login, final String password) {
        return Observable.defer(new Func0<Observable<TokenResponse>>() {
            @Override
            public Observable<TokenResponse> call() {
                return Observable.just(api.auth(login, password));
            }
        })
                .compose(this.<TokenResponse>applySchedulers());
    }

    @Override
    public Observable<List<UserDTO>> getUsers() {
        return Observable.defer(new Func0<Observable<List<UserDTO>>>() {
            @Override
            public Observable<List<UserDTO>> call() {
                return Observable.just(api.getUserDTOList());
            }
        })
                .compose(this.<List<UserDTO>>applySchedulers());
    }

    @Override
    public Observable<Response<Void>> changePassword(final UserDTO userDTO) {
        return Observable.defer(new Func0<Observable<Response<Void>>>() {
            @Override
            public Observable<Response<Void>> call() {
                return Observable.just(api.changePassword(userDTO));
            }
        })
                .compose(this.<Response<Void>>applySchedulers());
    }

    @Override
    public Observable<Void> deleteUser(final int id) {
        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                return Observable.just(api.deleteUserDTO(id));
            }
        })
                .compose(this.<Void>applySchedulers());
    }

    @Override
    public Observable<Response<Void>> checkLogin(final String login) {
        return Observable.defer(new Func0<Observable<Response<Void>>>() {
            @Override
            public Observable<Response<Void>> call() {
                return Observable.just(api.checkLogin(login));
            }
        })
                .compose(this.<Response<Void>>applySchedulers())
                ;
    }

    @Override
    public Observable<Response<Void>> checkEmail(final String email) {
        return Observable.defer(new Func0<Observable<Response<Void>>>() {
            @Override
            public Observable<Response<Void>> call() {
                return Observable.just(api.checkEmail(email));
            }
        })
                .compose(this.<Response<Void>>applySchedulers());
    }

    @Override
    public Observable<UserDTO> createUser(final UserDTO userDTO) {
        return Observable.defer(new Func0<Observable<UserDTO>>() {
            @Override
            public Observable<UserDTO> call() {
                return Observable.just(api.createUserDTO(userDTO));
            }
        })
                .compose(this.<UserDTO>applySchedulers())
                ;
    }

    @Override
    public Observable<UserDTO> regUser(UserDTO userDTO) {
        return createUser(userDTO);
    }

    @Override
    public Observable<Response<Void>> resetPassword(UserDTO userDTO) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}