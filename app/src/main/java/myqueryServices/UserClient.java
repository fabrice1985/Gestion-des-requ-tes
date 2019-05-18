package myqueryServices;

import myquerymodel.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

@POST("Inscription/")
Call<User> createAccount(@Body User user);
}
