package ru.gdgkazan.marvel.repository.cache;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Aydar Farrakhov
 */
public class RealmCacheErrorHandler<T extends RealmModel> implements Func1<Throwable, Observable<List<T>>> {

    private final Class<T> mClass;

    public RealmCacheErrorHandler(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Observable<List<T>> call(Throwable throwable) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<T> results = realm.where(mClass).findAll();
        return Observable.just(realm.copyFromRealm(results));
    }
}
