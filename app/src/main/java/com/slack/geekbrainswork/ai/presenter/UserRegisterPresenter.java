package com.slack.geekbrainswork.ai.presenter;

import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.UserMapper;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.activities.UserView;

import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

public class UserRegisterPresenter extends UserAddPresenter {

    public UserRegisterPresenter(UserView view) {
        super(view);
    }

    @Override
    protected void tryCreateUser(final String login, final String email, final String pass, final Integer privilege) {
        view.showProgressBar(true);

        Subscription loginExistSubscription = repository.checkLogin(login)
                .flatMap(new Func1<Response<Void>, Observable<Response<Void>>>() {
                    @Override
                    public Observable<Response<Void>> call(Response<Void> response) {
                        if (response.code() == 409) {
                            return Observable.error(new Exception("Пользователь с таким логином уже существует"));
                        }
                        return repository.checkEmail(email);
                    }
                })
                .flatMap(new Func1<Response<Void>, Observable<UserDTO>>() {
                    @Override
                    public Observable<UserDTO> call(Response<Void> response) {
                        if (response.code() == 409) {
                            return Observable.error(new Exception("Пользователь с таким e-mail уже существует"));
                        }
                        return repository.regUser(new UserDTO(login, email, pass, privilege));
                    }
                })
                .map(mapper)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showProgressBar(false);
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        view.showProgressBar(false);
                        view.onClose();
                    }
                });

        addSubscription(loginExistSubscription);
    }
}
