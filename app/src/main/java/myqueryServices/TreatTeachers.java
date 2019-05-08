package myqueryServices;

import myquerymodel.Teachers;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TreatTeachers {
    @POST("TraiterNote.php/")
    Call<Teachers> teachersTreat(@Body Teachers teachers);
}
