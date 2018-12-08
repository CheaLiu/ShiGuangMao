package com.yizhipin.base.data.net

import com.yizhipin.base.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class RetrofitFactoryGet {

    companion object {

        private fun initLogInterceptor(): Interceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        private val mInterceptor: Interceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("charset", "utf-8")
                    .get()
                    .build()
            chain.proceed(request)
        }

        private val mRetrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVICE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()

        private fun initClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(mInterceptor)
                    .addInterceptor(initLogInterceptor())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()
        }

        fun <T> create(service: Class<T>): T {
            return mRetrofit.create(service)
        }
    }
}