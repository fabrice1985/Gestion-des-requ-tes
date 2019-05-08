package myqueryServices;

import myquerymodel.DataDES;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ParamDES {
    @POST("EnregistrerDES.php/")
    Call<DataDES> registerDES (@Body DataDES dataDES);
}
