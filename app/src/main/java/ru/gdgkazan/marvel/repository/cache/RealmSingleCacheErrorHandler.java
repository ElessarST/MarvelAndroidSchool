package ru.gdgkazan.marvel.repository.cache;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Aydar Farrakhov
 */
public class RealmSingleCacheErrorHandler<T extends RealmModel> implements Func1<Throwable, Observable<T>> {

    private final Class<T> mClass;

    public RealmSingleCacheErrorHandler(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Observable<T> call(Throwable throwable) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<T> results = realm.where(mClass).findAll();
        return Observable.just(realm.copyFromRealm(results.first()));
    }
}
