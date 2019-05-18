package myqueryServices;

import myquerymodel.NoteExam;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Interface_Note_Exam {
    @POST("EnregistrerNoteExam.php/")
    Call<NoteExam> registerExam(@Body NoteExam exam_notes);
}
