package com.example.acer.myapplication.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface HttpGetService {

    @GET("recommend")
    Observable<ResponseBody> getRecommendData();

    @GET("category")
    Observable<ResponseBody> getCategoryData();

    @GET("top")
    Observable<ResponseBody> getTopData();

    @GET("categorydata/tool")
    Observable<ResponseBody> getCategoryToolData();

    @GET("categorydata/subject")
    Observable<ResponseBody> getCategorySubjectData();

    @GET("categorydata/subscribe")
    Observable<ResponseBody> getCategorySubscribeData();

    @GET("categorydata/new")
    Observable<ResponseBody> getCategoryNewData();

    @GET("categorydata/necessary")
    Observable<ResponseBody> getCategoryNecessaryData();

    @GET("app/recommend")
    Observable<ResponseBody> getAppRecommendData(@Query("packageName") String packageName);

    @GET("app/{type}")
    Observable<ResponseBody> getAppMoreRecommendData(@Path("type") String type, @Query("packageName") String packageName);

    @GET("{packageName}/introduce")
    Observable<ResponseBody> getAppDetailData(@Path("packageName") String packageName);

    @GET("app/comment")
    Observable<ResponseBody> getAppCommentData(@Query("packageName") String packageName);
}
