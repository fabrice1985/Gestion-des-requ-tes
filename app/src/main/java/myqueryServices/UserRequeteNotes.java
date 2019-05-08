package myqueryServices;

import myquerymodel.Notes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRequeteNotes {
    @POST("EnregistrerRequete.php/")
    Call<Notes> queryNotes(@Body Notes notes);
}
