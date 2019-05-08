package myqueryServices;

import myquerymodel.Othersrequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OthersrequestInterface {
    @POST("EnregistrerAutreReq.php/")
    Call<Othersrequest> sendOthers(@Body Othersrequest othersrequest);
}
