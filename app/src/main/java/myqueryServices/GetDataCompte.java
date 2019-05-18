package myqueryServices;

import myquerymodel.CompteList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataCompte {
    @GET("ConsulterComptes/")
    Call<CompteList> getAllCompte();
}
