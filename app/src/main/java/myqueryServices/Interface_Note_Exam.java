package myqueryServices;

import myquerymodel.Exam_Notes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Interface_Note_Exam {
    @POST("EnregistrerNoteExam.php/")
    Call<Exam_Notes> registerExam(@Body Exam_Notes exam_notes);
}
