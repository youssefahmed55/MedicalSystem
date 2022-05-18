package com.example.medicalsystem.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.medicalsystem.pojo.addnurse.AddNurseModleRequest;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsResponse;
import com.example.medicalsystem.pojo.calls.createcall.CreateCallRequestModel;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.calls.showcall.ShowCallData;
import com.example.medicalsystem.pojo.cases.CasesResponseModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;
import com.example.medicalsystem.pojo.login.LoginData;
import com.example.medicalsystem.pojo.measurement.MeasurementRequestModel;
import com.example.medicalsystem.pojo.register.RegisterRequestModel;
import com.example.medicalsystem.pojo.report.ReportResponseModel;
import com.example.medicalsystem.pojo.report.showreport.ShowReportData;
import com.example.medicalsystem.pojo.request.AddRequestModel;
import com.example.medicalsystem.pojo.showprofile.ShowProfileData;
import com.example.medicalsystem.pojo.task.TasksResponseModel;
import com.example.medicalsystem.pojo.task.showtask.ShowTaskData;
import com.example.medicalsystem.pojo.usersbytype.UserItem;
import com.example.medicalsystem.pojo.usersbytype.UsersResponseModel;
import com.example.medicalsystem.singleliveevent.Event;
import com.example.medicalsystem.pojo.login.LoginRequestModel;
import com.example.medicalsystem.pojo.login.LoginResponseModel;
import com.example.medicalsystem.singleliveevent.SingleLiveEvent;
import com.example.medicalsystem.repositary.Repositary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@HiltViewModel
public class MedicalSystemViewModel extends ViewModel {
    private static final String TAG = "MedicalSystemViewModel";
    private Repositary repositary;
    private MutableLiveData<LoginData> loginDataMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> MutableLiveDataDate = new MutableLiveData<>();
    private SingleLiveEvent<Event<LoginResponseModel>> SingleLiveDataLogin = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<LoginResponseModel>> SingleLiveDataRegister = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<CallsResponse>> singleLiveEventCalls = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<ArrayList<UserItem>>> singleLiveEventUsers = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventCreateCall = new SingleLiveEvent<>();
    private MutableLiveData<ShowCallData> MutableLiveDataShowCalls = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> SingleLiveEventLogoutCall = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<ReportResponseModel>> singleLiveEventReports = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<TasksResponseModel>> singleLiveEventTasks = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<CasesResponseModel>> singleLiveEventAllCases = new SingleLiveEvent<>();
    private MutableLiveData<ShowCaseResponseData> MutableLiveDataShowCase = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventAttendance = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventAcceptorRejectCall = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventAddNurse = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventMakeRequest = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventSendMeasurement = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventSendMedicalRecord = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<String>> singleLiveEventMedicalRecordImage = new SingleLiveEvent<>();
    private MutableLiveData<ShowProfileData> MutableLiveEventShowProfile = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventCreateTask = new SingleLiveEvent<>();
    private MutableLiveData<ShowTaskData> MutableLiveDataShowTask = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventExecution = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventCreateReport = new SingleLiveEvent<>();
    private MutableLiveData<ShowReportData> MutableLiveDataShowReport = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventFinishReport = new SingleLiveEvent<>();
    private SingleLiveEvent<Event<MessageResponseModel>> singleLiveEventEndReport = new SingleLiveEvent<>();


    CompositeDisposable compositeDisposablelogin = new CompositeDisposable();
    CompositeDisposable compositeDisposableRegister = new CompositeDisposable();
    CompositeDisposable compositeDisposableCallsByDate = new CompositeDisposable();
    CompositeDisposable compositeDisposableAllCalls = new CompositeDisposable();
    CompositeDisposable compositeDisposableUsers = new CompositeDisposable();
    CompositeDisposable compositeDisposableCreateCall = new CompositeDisposable();
    CompositeDisposable compositeDisposableShowCalls = new CompositeDisposable();
    CompositeDisposable compositeDisposableLogoutCall = new CompositeDisposable();
    CompositeDisposable compositeDisposableTasksByDate = new CompositeDisposable();
    CompositeDisposable compositeDisposableReportsByDate = new CompositeDisposable();
    CompositeDisposable compositeDisposableAllCases = new CompositeDisposable();
    CompositeDisposable compositeDisposableShowCase = new CompositeDisposable();
    CompositeDisposable compositeDisposableAttendance = new CompositeDisposable();
    CompositeDisposable compositeDisposableAcceptorRejectCall = new CompositeDisposable();
    CompositeDisposable compositeDisposableAddNurse = new CompositeDisposable();
    CompositeDisposable compositeDisposableMakeRequest = new CompositeDisposable();
    CompositeDisposable compositeDisposableSendMeasurement = new CompositeDisposable();
    CompositeDisposable compositeDisposableSendMedicalRecord = new CompositeDisposable();
    CompositeDisposable compositeDisposableMedicalRecordImage = new CompositeDisposable();
    CompositeDisposable compositeDisposableShowProfile = new CompositeDisposable();
    CompositeDisposable compositeDisposableCreateTask = new CompositeDisposable();
    CompositeDisposable compositeDisposableShowTask = new CompositeDisposable();
    CompositeDisposable compositeDisposableExecution = new CompositeDisposable();
    CompositeDisposable compositeDisposableCreateReport = new CompositeDisposable();
    CompositeDisposable compositeDisposableShowReport = new CompositeDisposable();
    CompositeDisposable compositeDisposableFinishReport = new CompositeDisposable();
    CompositeDisposable compositeDisposableEndReport = new CompositeDisposable();

    @Inject
    public MedicalSystemViewModel(Repositary repositary) {
        this.repositary = repositary;
    }

    public MutableLiveData<LoginData> getLoginDataMutableLiveData() {
        return loginDataMutableLiveData;
    }

    public SingleLiveEvent<Event<LoginResponseModel>> getSingleLiveDataLogin() {
        return SingleLiveDataLogin;
    }

    public SingleLiveEvent<Event<LoginResponseModel>> getSingleLiveDataRegister() {
        return SingleLiveDataRegister;
    }

    public SingleLiveEvent<Event<CallsResponse>> getSingleLiveEventCalls() {
        return singleLiveEventCalls;
    }

    public SingleLiveEvent<Event<ArrayList<UserItem>>> getSingleLiveEventUsers() {
        return singleLiveEventUsers;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventCreateCall() {
        return singleLiveEventCreateCall;
    }

    public MutableLiveData<ShowCallData> getMutableLiveDataShowCalls() {
        return MutableLiveDataShowCalls;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventLogoutCall() {
        return SingleLiveEventLogoutCall;
    }

    public SingleLiveEvent<Event<ReportResponseModel>> getSingleLiveEventReports() {
        return singleLiveEventReports;
    }

    public SingleLiveEvent<Event<CasesResponseModel>> getSingleLiveEventAllCases() {
        return singleLiveEventAllCases;
    }

    public MutableLiveData<ShowCaseResponseData> getMutableLiveDataShowCase() {
        return MutableLiveDataShowCase;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventAttendance() {
        return singleLiveEventAttendance;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventAcceptorRejectCall() {
        return singleLiveEventAcceptorRejectCall;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventAddNurse() {
        return singleLiveEventAddNurse;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventMakeRequest() {
        return singleLiveEventMakeRequest;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventSendMeasurement() {
        return singleLiveEventSendMeasurement;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventSendMedicalRecord() {
        return singleLiveEventSendMedicalRecord;
    }

    public SingleLiveEvent<Event<String>> getSingleLiveEventMedicalRecordImage() {
        return singleLiveEventMedicalRecordImage;
    }

    public MutableLiveData<ShowProfileData> getMutableLiveEventShowProfile() {
        return MutableLiveEventShowProfile;
    }

    public MutableLiveData<String> getMutableLiveDataDate() {
        return MutableLiveDataDate;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventCreateTask() {
        return singleLiveEventCreateTask;
    }

    public SingleLiveEvent<Event<TasksResponseModel>> getSingleLiveEventTasks() {
        return singleLiveEventTasks;
    }
    public MutableLiveData<ShowTaskData> getMutableLiveDataShowTask() {
        return MutableLiveDataShowTask;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventExecution() {
        return singleLiveEventExecution;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventCreateReport() {
        return singleLiveEventCreateReport;
    }

    public MutableLiveData<ShowReportData> getMutableLiveDataShowReport() {
        return MutableLiveDataShowReport;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventFinishReport() {
        return singleLiveEventFinishReport;
    }

    public SingleLiveEvent<Event<MessageResponseModel>> getSingleLiveEventEndReport() {
        return singleLiveEventEndReport;
    }

    public void getLoginData(){
        loginDataMutableLiveData.setValue(repositary.getLoginDataFromSharedPrefrences());
    }
    public void deleteDataOfLogin(){
        repositary.clearDateOfLogin();
    }

    public void saveLoginData(LoginData loginData){
        repositary.saveLoginData(loginData);
    }

    public void deleteDate(){
        repositary.clearDate();
    }

    public void getDate(){
        MutableLiveDataDate.setValue(repositary.getDate());
    }
    public void saveDate(String date){
        repositary.saveDate(date);
    }


    public void getMedicalRecoredImage(int id){
        compositeDisposableMedicalRecordImage.add(repositary.getObservableDataApiShowCase(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventMedicalRecordImage.setValue(new Event<>(o.getData().getImage())),e-> Log.d(TAG,e.getMessage())));
    }
    public void getDatafromLoginResponse(LoginRequestModel loginRequestModel){

        compositeDisposablelogin.add(repositary.getobservabledataapiLogin(loginRequestModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o-> SingleLiveDataLogin.setValue(new Event<>(o)),e-> Log.d(TAG, "getDatafromLoginResponse: " +e.getMessage())));



    }

    public void getDatafromRegisterResponse(RegisterRequestModel registerRequestModel){


        compositeDisposableRegister.add(repositary.getobservabledataapiRegister(registerRequestModel).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(o->SingleLiveDataRegister.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));


    }

    public void getDatafromAllCallsResponse(){

        compositeDisposableAllCalls.add(repositary.getObservableDataApiAllCalls("Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventCalls.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromCallsByDateResponse(String date){


        compositeDisposableCallsByDate.add(repositary.getObservableDataApiCallsByDate(date , "Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventCalls.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromUsersResponseByType(String type ){


        compositeDisposableUsers.add(repositary.getObservableDataApiUsersByType(type , "Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .map(new Function<UsersResponseModel, ArrayList<UserItem>>() {
                    @Override
                    public ArrayList<UserItem> apply(UsersResponseModel usersResponseModel) throws Throwable {
                        ArrayList<UserItem> arrayList = usersResponseModel.getData();
                        for(UserItem userItem : arrayList){
                            userItem.setAvatar("https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg");
                        }
                        return arrayList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventUsers.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromCreateCallsResponse(CreateCallRequestModel createCallRequestModel){


        compositeDisposableCreateCall.add(repositary.getObservableDataApiCreateCall(createCallRequestModel , "Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventCreateCall.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromShowCallsResponse(int id){


        compositeDisposableShowCalls.add(repositary.getObservableDataApiShowCalls(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->MutableLiveDataShowCalls.setValue(o.getData()),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromLogOutCallResponse(int id){


        compositeDisposableLogoutCall.add(repositary.getObservableDataApiLogOutCall(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->SingleLiveEventLogoutCall.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromReportsByDateResponse(String date){
        compositeDisposableReportsByDate.add(repositary.getObservableDataApiReportsByDate(date,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventReports.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromAllCasesResponse(){
        compositeDisposableAllCases.add(repositary.getObservableDataApiAllCases("Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventAllCases.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromShowCaseResponse(int id){


        compositeDisposableShowCase.add(repositary.getObservableDataApiShowCase(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->MutableLiveDataShowCase.setValue(o.getData()),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDataFromAttendanceResponse(String status){
        compositeDisposableAttendance.add(repositary.getObsevableDataApiAttendance(status,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventAttendance.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));

    }

    public void getDataFromAcceptOrRejectCallResponse(int id,String status){
        compositeDisposableAcceptorRejectCall.add(repositary.getObsevableDataApiAcceptORRejectCall(id,status,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventAcceptorRejectCall.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDataFromAddNurseResponse(AddNurseModleRequest addNurseModleRequest){
        compositeDisposableAddNurse.add(repositary.getObsevableDataApiAddNurse(addNurseModleRequest,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventAddNurse.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }
    public void getDataFromMakeRequestResponse(AddRequestModel addRequestModel){
        compositeDisposableMakeRequest.add(repositary.getObsevableDataApiMakeRequest(addRequestModel,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventMakeRequest.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDataFromSendMeasurementResponse(MeasurementRequestModel measurementRequestModel){
        compositeDisposableSendMeasurement.add(repositary.getObsevableDataApiSendMeasurement(measurementRequestModel,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventSendMeasurement.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }
    public void getDataFromSendMedicalRecordResponse(RequestBody callId , MultipartBody.Part body, RequestBody note, RequestBody status){
        compositeDisposableSendMedicalRecord.add(repositary.getObsevableDataApiSendMedicalRecord(callId,body,note,status,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventSendMedicalRecord.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }
    public void getDataFromShowProfileResponse(int userId){
        compositeDisposableShowProfile.add(repositary.getObsevableDataApiShowProfile(userId,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->MutableLiveEventShowProfile.setValue(o.getData()),e-> Log.d(TAG,e.getMessage())));
    }
    public void getDatafromTasksByDateResponse(String date){


        compositeDisposableTasksByDate.add(repositary.getObservableDataApiTasksByDate(date , "Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventTasks.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }
    public void getDataFromCreateTaskResponse(RequestBody userId , RequestBody taskName, MultipartBody.Part body, RequestBody description, List<String> todos){
        compositeDisposableCreateTask.add(repositary.getObsevableDataApiCreateTask(userId,taskName,body,description,todos,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventCreateTask.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }


    public void getDatafromShowTaskResponse(int id){


        compositeDisposableShowTask.add(repositary.getObservableDataApiShowTask(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->MutableLiveDataShowTask.setValue(o.getData()),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromExecutionResponse(int id){
        compositeDisposableExecution.add(repositary.getObservableDataApiExecution(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventExecution.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDataFromCreateReportResponse(RequestBody reportName, RequestBody description , MultipartBody.Part body){
        compositeDisposableCreateReport.add(repositary.getObsevableDataApiCreateReport(reportName,description,body,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventCreateReport.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }
    public void getDatafromShowReportResponse(int id){

        compositeDisposableFinishReport.add(repositary.getObservableDataApiShowReport(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->MutableLiveDataShowReport.setValue(o.getData()),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromFinishReportResponse(int id){

        compositeDisposableShowReport.add(repositary.getObservableDataApiFinishReport(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventFinishReport.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }

    public void getDatafromEndReportResponse(int id){

        compositeDisposableEndReport.add(repositary.getObservableDataApiEndReport(id,"Bearer " + repositary.getLoginDataFromSharedPrefrences().getAccessToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->singleLiveEventEndReport.setValue(new Event<>(o)),e-> Log.d(TAG,e.getMessage())));
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposablelogin.clear();
        compositeDisposableRegister.clear();
        compositeDisposableAllCalls.clear();
        compositeDisposableCallsByDate.clear();
        compositeDisposableUsers.clear();
        compositeDisposableCreateCall.clear();
        compositeDisposableShowCalls.clear();
        compositeDisposableLogoutCall.clear();
        compositeDisposableReportsByDate.clear();
        compositeDisposableAllCases.clear();
        compositeDisposableShowCase.clear();
        compositeDisposableAttendance.clear();
        compositeDisposableAcceptorRejectCall.clear();
        compositeDisposableAddNurse.clear();
        compositeDisposableMakeRequest.clear();
        compositeDisposableSendMeasurement.clear();
        compositeDisposableSendMedicalRecord.clear();
        compositeDisposableMedicalRecordImage.clear();
        compositeDisposableShowProfile.clear();
        compositeDisposableTasksByDate.clear();
        compositeDisposableCreateTask.clear();
        compositeDisposableShowTask.clear();
        compositeDisposableExecution.clear();
        compositeDisposableCreateReport.clear();
        compositeDisposableShowReport.clear();
        compositeDisposableFinishReport.clear();
        compositeDisposableEndReport.clear();
    }




}
