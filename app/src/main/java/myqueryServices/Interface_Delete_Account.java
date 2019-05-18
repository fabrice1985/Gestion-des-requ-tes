package myqueryServices;

import myquerymodel.ParamDelete;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Interface_Delete_Account {
    @POST("SupprimerUnCompte.php/")
    Call<ParamDelete> deleteAccount (@Body ParamDelete paramDelete);
}
