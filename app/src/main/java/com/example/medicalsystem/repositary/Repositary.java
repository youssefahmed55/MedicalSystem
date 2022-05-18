package com.example.medicalsystem.repositary;




import android.content.SharedPreferences;

import com.example.medicalsystem.apiui.MyApiInterface;
import com.example.medicalsystem.pojo.addnurse.AddNurseModleRequest;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsResponse;
import com.example.medicalsystem.pojo.calls.createcall.CreateCallRequestModel;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.calls.showcall.ShowCallResponse;
import com.example.medicalsystem.pojo.cases.CasesResponseModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseModel;
import com.example.medicalsystem.pojo.login.LoginData;
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
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class Repositary implements @NonNull Action {
    private static final String TAG = "Repositary";
    private MyApiInterface myApiInterface;
    private SharedPreferences sharedPreferences;



    @Inject
    public Repositary(MyApiInterface myApiInterface , SharedPreferences sharedPreferences) {
        this.myApiInterface = myApiInterface;
        this.sharedPreferences = sharedPreferences;

    }

    //Get login data from sharedPreferences
   public LoginData getLoginDataFromSharedPrefrences() {
       Gson gson = new Gson();
       String json = sharedPreferences.getString("loginModel", "");

       if(!json.equals("")){
           LoginData loginDataa = gson.fromJson(json, LoginData.class);
           return loginDataa;
       }
       return null;
   }
    //Clear Login Data in SharedPerference
    public void clearDateOfLogin(){
        sharedPreferences.edit().putString("loginModel", "").apply();
    }

    //Save Login Data in SharedPerference
    public void saveLoginData(LoginData loginData){
        Gson gson = new Gson();
        String json = gson.toJson(loginData);
        sharedPreferences.edit().putString("loginModel", json).apply();
    }

    //Save Date in SharedPerference
    public void saveDate(String date){
        sharedPreferences.edit().putString("date",date).apply();
    }
    //Get Date from sharedPreferences
    public String getDate(){
        return sharedPreferences.getString("date","") ;
    }
    //Clear Date from sharedPreferences
    public void clearDate(){
        sharedPreferences.edit().putString("date", "").apply();
    }

    //Apis
    public Single<LoginResponseModel> getobservabledataapiLogin(LoginRequestModel loginRequestModel){
        return myApiInterface.getLoginResponse(loginRequestModel);
    }

    public Single<LoginResponseModel> getobservabledataapiRegister(RegisterRequestModel registerRequestModel){
        return myApiInterface.getRegisterResponse(registerRequestModel);
    }

    public Single<CallsResponse> getObservableDataApiCallsByDate(String date , String token){
        return myApiInterface.getCallsByDateResponse(date,token);

    }
    public Single<CallsResponse> getObservableDataApiAllCalls(String token){
        return myApiInterface.getAllCallsResponse(token);

    }

    public Single<UsersResponseModel> getObservableDataApiUsersByType(String type , String token){
        return myApiInterface.getUsersByTypeResponse(type,token);

    }

    public Single<MessageResponseModel> getObservableDataApiCreateCall(CreateCallRequestModel createCallRequestModel , String token){
        return myApiInterface.getCreateCallResponse(createCallRequestModel,token);

    }
    public Single<ShowCallResponse> getObservableDataApiShowCalls(int id , String token){
        return myApiInterface.getShowCallResponse(id,token);

    }

    public Single<MessageResponseModel> getObservableDataApiLogOutCall(int id , String token){
        return myApiInterface.getLogoutCallResponse(id,token);

    }


    public Single<ReportResponseModel> getObservableDataApiReportsByDate(String date,String token){
        return myApiInterface.getReportsByDateResponse(date,token);

    }
    public Single<CasesResponseModel> getObservableDataApiAllCases(String token){
        return myApiInterface.getAllCasesResponse(token);

    }

    public Single<ShowCaseResponseModel> getObservableDataApiShowCase(int id , String token){
        return myApiInterface.getShowCaseResponse(id,token);

    }

    public Single<MessageResponseModel> getObsevableDataApiAttendance(String status , String token){
        return myApiInterface.getAttendanceResponse(status,token);
    }

    public Single<MessageResponseModel> getObsevableDataApiAcceptORRejectCall(int id ,String status , String token){
        return myApiInterface.getAcceptOrRejectCallResponse(id,status,token);
    }

    public Single<MessageResponseModel> getObsevableDataApiAddNurse(AddNurseModleRequest addNurseModleRequest , String token){
        return myApiInterface.getAddNurseResponse(addNurseModleRequest,token);
    }
    public Single<MessageResponseModel> getObsevableDataApiMakeRequest(AddRequestModel addRequestModel, String token){
        return myApiInterface.getMakeRequestResponse(addRequestModel,token);
    }

    public Single<MessageResponseModel> getObsevableDataApiSendMeasurement(MeasurementRequestModel measurementRequestModel, String token){
        return myApiInterface.getSendMeasurementResponse(measurementRequestModel,token);
    }

    public Single<MessageResponseModel> getObsevableDataApiSendMedicalRecord(RequestBody callId , MultipartBody.Part body,RequestBody note,RequestBody status, String token){
        return myApiInterface.getSendMedicalRecordResponse(callId,body,note,status,token);
    }
    public Single<ShowProfileResponse> getObsevableDataApiShowProfile(int userId, String token){
        return myApiInterface.getShowProfileResponse(userId,token);
    }

    public Single<TasksResponseModel> getObservableDataApiTasksByDate(String date, String token){
        return myApiInterface.getTasksByDateResponse(date,token);

    }
    public Single<MessageResponseModel> getObsevableDataApiCreateTask(RequestBody userId , RequestBody taskName, MultipartBody.Part body, RequestBody description, List<String> todos, String token){
        return myApiInterface.getCreateTaskResponse(userId,taskName,body,description,todos,token);
    }

    public Single<ShowTaskResponse> getObservableDataApiShowTask(int id , String token){
        return myApiInterface.getShowTaskResponse(id,token);

    }

    public Single<MessageResponseModel> getObservableDataApiExecution(int id , String token){
        return myApiInterface.getExecutionResponse(id,token);

    }

    public Single<MessageResponseModel> getObsevableDataApiCreateReport(RequestBody reportName, RequestBody description , MultipartBody.Part body, String token){
        return myApiInterface.getCreateReportResponse(reportName,description,body,token);
    }

    public Single<ShowReportResponse> getObservableDataApiShowReport(int id , String token){
        return myApiInterface.getShowReportResponse(id,token);

    }

    public Single<MessageResponseModel> getObservableDataApiFinishReport(int id , String token){
        return myApiInterface.getFinishReportResponse(id,token);
    }

    public Single<MessageResponseModel> getObservableDataApiEndReport(int id , String token){
        return myApiInterface.getEndReportResponse(id,token);
    }

    @Override
    public void run() throws Throwable {

    }
}
