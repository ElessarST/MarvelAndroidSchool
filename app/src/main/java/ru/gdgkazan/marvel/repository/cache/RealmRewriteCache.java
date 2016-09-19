package ru.gdgkazan.marvel.repository.cache;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Aydar Farrakhov
 */
public class RealmRewriteCache<T extends RealmModel> implements Func1<List<T>, Observable<List<T>>> {

    private final Class<T> mClass;

    public RealmRewriteCache(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Observable<List<T>> call(List<T> elements) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(mClass);
            realm.insert(elements);
        });
        return Observable.just(elements);
    }
}
