package com.example.medicalsystem.apiui;



import com.example.medicalsystem.pojo.addnurse.AddNurseModleRequest;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsResponse;
import com.example.medicalsystem.pojo.calls.createcall.CreateCallRequestModel;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.calls.showcall.ShowCallResponse;
import com.example.medicalsystem.pojo.cases.CasesResponseModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseModel;
import com.example.medicalsystem.pojo.login.LoginRequestModel;
import com.example.medicalsystem.pojo.login.LoginResponseModel;
import com.example.medicalsystem.pojo.measurement.MeasurementRequestModel;

import com.example.medicalsystem.pojo.register.RegisterRequestModel;
import com.example.medicalsystem.pojo.report.ReportResponseModel;
import com.example.medicalsystem.pojo.report.showreport.ShowReportResponse;
import com.example.medicalsystem.pojo.request.AddRequestModel;
import com.example.medicalsystem.pojo.showprofile.ShowProfileResponse;
import com.example.medicalsystem.pojo.task.TasksResponseModel;
import com.example.medicalsystem.pojo.task.showtask.ShowTaskResponse;
import com.example.medicalsystem.pojo.usersbytype.UsersResponseModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;


import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MyApiInterface {


    //Send Login Request And Return Response of LoginData
    @POST("login")
    Single<LoginResponseModel> getLoginResponse (@Body LoginRequestModel loginRequestModel);

    //Send Register Request And Return Response of LoginData
    @POST("register")
    Single<LoginResponseModel> getRegisterResponse (@Body RegisterRequestModel registerRequestModel);

    //Get Calls By Date
    @GET("calls")
    Single<CallsResponse> getCallsByDateResponse (@Query("date") String date , @Header("Authorization") String token );

    //Get Employees By Type
    @GET("doctors")
    Single<UsersResponseModel> getUsersByTypeResponse (@Query("type") String type , @Header("Authorization") String token );

    //Send Create Call Request And Return Response of Message
    @POST("calls")
    Single<MessageResponseModel> getCreateCallResponse(@Body CreateCallRequestModel createCallRequestModel , @Header("Authorization") String token);

    //Get All Calls
    @GET("calls")
    Single<CallsResponse> getAllCallsResponse (@Header("Authorization") String token );

    //Get Data of Call By id
    @GET("calls/{id}")
    Single<ShowCallResponse> getShowCallResponse (@Path("id") int id, @Header("Authorization") String token );

    //Update Status of Call By id
    @PUT("calls/{id}")
    Single<MessageResponseModel> getLogoutCallResponse (@Path("id") int id, @Header("Authorization") String token );


    //Get Reports By date
    @GET("reports")
    Single<ReportResponseModel> getReportsByDateResponse (@Query("date") String date , @Header("Authorization") String token );

    //Get All Cases
    @GET("case")
    Single<CasesResponseModel> getAllCasesResponse (@Header("Authorization") String token );

    //Get Data of Case by id
    @GET("case/{id}")
    Single<ShowCaseResponseModel> getShowCaseResponse (@Path("id") int id, @Header("Authorization") String token );

    //Send Status of Attendance Or Leaving and Return Response of Message
    @POST("attendance")
    Single<MessageResponseModel> getAttendanceResponse (@Query("status") String status , @Header("Authorization") String token);

    //Update Call Status Accept or busy By id of Call
    @PUT("calls-accept/{id}")
    Single<MessageResponseModel> getAcceptOrRejectCallResponse(@Path("id") int id , @Query("status") String status , @Header("Authorization") String token);

    //Send Nuse id and Call id And Return Response of Message
    @POST("add-nurse")
    Single<MessageResponseModel> getAddNurseResponse(@Body AddNurseModleRequest addNurseModleRequest , @Header("Authorization") String token);

    //Send Requests to Analysis and Nurse And Return Response of Message
    @POST("make-request")
    Single<MessageResponseModel> getMakeRequestResponse(@Body AddRequestModel addRequestModel , @Header("Authorization") String token);

    //Send Mesurements And Return Response of Message
    @POST("measurement")
    Single<MessageResponseModel> getSendMeasurementResponse(@Body MeasurementRequestModel measurementRequestModel , @Header("Authorization") String token);

    //Send Medical Record And Return Response of Message
    @Multipart
    @POST("medical-record")
    Single<MessageResponseModel> getSendMedicalRecordResponse(@Part("call_id") RequestBody callid, @Part MultipartBody.Part image,@Part("note") RequestBody note,@Part("status") RequestBody status, @Header("Authorization") String token);

    //Send id of Employee and Return Response of User Data
    @POST("show-profile")
    Single<ShowProfileResponse> getShowProfileResponse(@Query("user_id") int userId , @Header("Authorization") String token);

    //Get Tasks By Date
    @GET("tasks")
    Single<TasksResponseModel> getTasksByDateResponse (@Query("date") String date , @Header("Authorization") String token );

    //Create Task And Return Response of Message
    @Multipart
    @POST("tasks")
    Single<MessageResponseModel> getCreateTaskResponse(@Part("user_id") RequestBody userId, @Part("task_name") RequestBody taskName , @Part MultipartBody.Part image, @Part("description") RequestBody description, @Part("todos[]") List<String> todos , @Header("Authorization") String token);

    //Get Data of Task By id
    @GET("tasks/{id}")
    Single<ShowTaskResponse> getShowTaskResponse (@Path("id") int id, @Header("Authorization") String token );

    //Execution Task By id And Return Response of Message
    @PUT("tasks/{id}")
    Single<MessageResponseModel> getExecutionResponse (@Path("id") int id, @Header("Authorization") String token );

    //Create Report And Return Response of Message
    @Multipart
    @POST("reports")
    Single<MessageResponseModel> getCreateReportResponse(@Part("report_name") RequestBody reportName, @Part("description") RequestBody description , @Part MultipartBody.Part image, @Header("Authorization") String token);

    //Get Data of Report By id
    @GET("reports/{id}")
    Single<ShowReportResponse> getShowReportResponse (@Path("id") int id, @Header("Authorization") String token );

    //Finish Report by id And Return Response of Message
    @PUT("reports/{id}")
    Single<MessageResponseModel> getFinishReportResponse (@Path("id") int id, @Header("Authorization") String token );

    //Delete Report by id And Return Response of Message
    @DELETE("reports/{id}")
    Single<MessageResponseModel> getEndReportResponse (@Path("id") int id, @Header("Authorization") String token );
}
