package ru.gdgkazan.marvel.api;

import android.support.annotation.NonNull;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.gdgkazan.marvel.BuildConfig;

/**
 * @author Aydar Farrakhov
 */
public final class ApiKeyInterceptor implements Interceptor {

    private final static String API_KEY_PARAM = "apikey";
    private final static String TIMESTAMP_PARAM = "ts";
    private final static String HASH_PARAM = "hash";
    private final static String MD5_ALGO = "MD5";

    private ApiKeyInterceptor() {
    }

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();


        Request request = original.newBuilder()
                .url(getUrl(original)).build();
        return chain.proceed(request);
    }

    private HttpUrl getUrl(Request request){
        String key = BuildConfig.API_KEY;
        String privateKey = BuildConfig.PRIVATE_KEY;
        Long ts = new Date().getTime();
        String hash = getHash(key, privateKey, ts);

        return request.url().newBuilder()
                .addQueryParameter(API_KEY_PARAM, key)
                .addQueryParameter(TIMESTAMP_PARAM, String.valueOf(ts))
                .addQueryParameter(HASH_PARAM, hash)
                .build();
    }

    private String getHash(String key, String privateKey, Long ts) {
        return new String(Hex.encodeHex(DigestUtils.md5(ts + privateKey + key)));
    }
}
