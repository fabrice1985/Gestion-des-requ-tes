package myqueryServices;

import myquerymodel.ParamConnexion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Connexion {

    @POST("Connection.php/")
    Call<ParamConnexion> createConnexion(@Body ParamConnexion paramconnexion);

}
