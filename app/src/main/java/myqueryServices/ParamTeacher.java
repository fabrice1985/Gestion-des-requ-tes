package myqueryServices;

import myquerymodel.DataTeacher;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ParamTeacher {
    @POST("EnregistrerEnseignant.php/")
    Call<DataTeacher> registerTeacher(@Body DataTeacher dataTeacher);
}
