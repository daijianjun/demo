package demo.dai.com.retrofitdemoforid.retrofitinterface;

import demo.dai.com.retrofitdemoforid.model.IdData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by dai on 16/11/24.
 */

public interface IdCheckService {
    @GET("/apistore/idservice/id")
    Call<IdData> getIdResponse(@Header("apikey") String apiKey, @Query("id") String id);
}
