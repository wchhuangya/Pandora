package com.ch.wchhuangya.lib.rxandroid;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wchya on 2016-11-26 17:50
 */

public class RxandroidUtil {

    private static final Observable.Transformer schedulerTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> Observable.Transformer<T, T> applySchedulers () {
        return (Observable.Transformer<T, T>) schedulerTransformer;
    }
}
