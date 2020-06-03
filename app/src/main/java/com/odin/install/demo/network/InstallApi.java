package com.odin.install.demo.network;

import com.odin.install.demo.data.ChannelData;
import com.odin.install.demo.data.ChannelRequest;
import com.odin.install.demo.data.UserData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InstallApi {

    /**
     * 用户关系
     *
     * @param odinKey odinKey
     * @param userId  userId
     * @return 用户关系
     */
    @GET("api/channel/odin/channel-app/query-demo-lower-user-id")
    Observable<UserData> getUserRelation(@Query("odinKey") String odinKey, @Query("userId") String userId);

    @POST("api/channel/odin/channel-report/query-channel-report")
    Observable<ChannelData> getChannelData(@Body ChannelRequest request);
}
