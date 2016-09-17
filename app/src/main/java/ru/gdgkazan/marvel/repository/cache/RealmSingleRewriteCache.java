package ru.gdgkazan.marvel.repository.cache;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Aydar Farrakhov
 */
public class RealmSingleRewriteCache<T extends RealmModel> implements Func1<T, Observable<T>> {

    private final Class<T> mClass;

    public RealmSingleRewriteCache(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Observable<T> call(T element) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(mClass);
            realm.insert(element);
        });
        return Observable.just(element);
    }
}
