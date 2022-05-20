# Medical System

**This Application is a Hospital System that helps facilitate and improve communication between Hospital staff to follow up on patients .**

![group340](https://user-images.githubusercontent.com/99625111/169147935-73940f1b-2040-4929-a315-26d3f879ed1e.png)

![AGPL License](https://img.shields.io/badge/AndroidStudio-blue.svg) 
![AGPL License](https://img.shields.io/badge/Java-blue.svg) 
[![GPLv3 License](https://img.shields.io/badge/minSdk-21-green.svg)](https://opensource.org/licenses/)
[![GPLv3 License](https://img.shields.io/badge/targetSdk-32-yellow.svg)](https://opensource.org/licenses/)


## Features

- The Application supports English and Arabic languages
- The Project is written in Java
- The Application can be used to organize the Hospital Administration


## Table of Contents

- Dependency used in project
- How can Hospital staff start using the application
- Screenshots and explain of the HR's use of the application
- Screenshots and explain of the Receptionist's use of the application
- Screenshots and explain of the Doctor's use of the application
- Screenshots and explain of the Nurse's use of the application
- Screenshots and explain of the Analysis's use of the application
- Screenshots and explain of the Manger's use of the application
- Creation of employee tasks by the Manager
- Create an employee report
- Recording the employee's attendance and departure from the Hospital
- Download Apk And Contact


## Dependency used in project

- Navigation Component

```css
implementation 'androidx.navigation:navigation-fragment:2.4.2'

implementation 'androidx.navigation:navigation-ui:2.4.2'
```

- Sdp

```css
implementation 'com.intuit.sdp:sdp-android:1.0.6'
```

- Ssp

```css
`implementation 'com.intuit.ssp:ssp-android:1.0.6'`
```

- CardView

```css
implementation "androidx.cardview:cardview:1.0.0"
```

- RecycleView

```css
implementation "androidx.recyclerview:recyclerview:1.2.1"
implementation "androidx.recyclerview:recyclerview-selection:1.1.0"
```

- Dagger hilt

```css
implementation "com.google.dagger:hilt-android:2.38.1"
annotationProcessor "com.google.dagger:hilt-compiler:2.38.1"
```

- RxJava

```css
implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
```

- Retrofit

```css
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation "com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0"
```

- Glide

```css
implementation 'com.github.bumptech.glide:glide:4.11.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
```

- Firebase

```css
implementation platform('com.google.firebase:firebase-bom:29.2.1')
implementation 'com.google.firebase:firebase-analytics'
```

- Firebase Cloud Messaging

```css
implementation 'com.google.firebase:firebase-messaging:23.0.2'
```

- Gson

```css
implementation 'com.google.code.gson:gson:2.8.8'
```

- Toasty

```css
implementation 'com.github.GrenderG:Toasty:1.5.2'
```

## How can Hospital staff start using the application

- After creating the accounts through the HR's employee, The hospital staff can log in to the application with their email and password in the first interface of the application .


![splash](https://user-images.githubusercontent.com/99625111/169163751-aa0a5bb2-1320-4550-92a1-7f162a87cf0e.png)
![login](https://user-images.githubusercontent.com/99625111/169163724-882315fc-6ac3-4203-ad41-c91ffce450e4.png)


## Screenshots and explain of the HR's use of the application

- HR's Interface

![hr](https://user-images.githubusercontent.com/99625111/169166425-6ba51c36-31cd-4a0f-a6a6-ab5a40ec426d.png)


- HR's Profile

![profile](https://user-images.githubusercontent.com/99625111/169166416-2a024c9c-8ef4-44d5-812b-6b7e58cf622e.png)

- Show All Employees In Hospital System

![employeehr](https://user-images.githubusercontent.com/99625111/169166421-ee1e84f5-7402-417f-a957-40581c1cbe57.png)

- Employee's Profile

![employeeprofile](https://user-images.githubusercontent.com/99625111/169167749-d545e18a-0413-4af8-9386-a99bd515fd17.png)

- Create New User To Hospital System

![new user](https://user-images.githubusercontent.com/99625111/169166412-68fb95a7-5270-4149-8fd0-29b6a7d62c26.png)



## Screenshots and explain of the Receptionist's use of the application

- receptionist's interface

![rec](https://user-images.githubusercontent.com/99625111/169171666-c3b07a63-acf2-4282-a382-fe7f3c8ea2d6.png)

- receptionist's profile

![rec profile](https://user-images.githubusercontent.com/99625111/169171663-ea8fe38c-620f-4d0d-b9ae-9b3580722a14.png)

- Calls that receptionist added With Doctor to Follow up patient

![calls rec](https://user-images.githubusercontent.com/99625111/169188650-bd8508a0-cc2c-4a79-aa14-f11ade74dffe.png)

- Create Call

![create call rec](https://user-images.githubusercontent.com/99625111/169171655-3d79ddb7-4763-4ac3-814a-c161e15ef3d6.png)

- Select Doctor to Follow up patient

![select doc rec](https://user-images.githubusercontent.com/99625111/169171667-bab57e5c-4bae-4b4e-a421-5cc80788c376.png)

- Case or Call Details

![case details rec](https://user-images.githubusercontent.com/99625111/169188586-2b0b2e86-7a4d-4820-b422-79b71ebd6305.png)


![case details2 rec](https://user-images.githubusercontent.com/99625111/169171648-d27b0775-dabb-4311-9d75-6afb74fb1562.png)


## Screenshots and explain of the Doctor's use of the application

- Doctor's interface

![doctor](https://user-images.githubusercontent.com/99625111/169188914-d3fa8537-df55-4ef4-bca1-c39117f4d7f6.png)

- Doctor's Profile

![doctor profile](https://user-images.githubusercontent.com/99625111/169188920-3a80d3f4-e58f-41b2-8fa9-b856353d330e.png)

- Accept Calls or Reject them

![calls doc](https://user-images.githubusercontent.com/99625111/169188937-33603098-e713-406c-a97e-44385fa9b7a1.png)

- Cases or Calls That Doctor Accepted

![cases doc](https://user-images.githubusercontent.com/99625111/169188929-e201bfae-5915-4110-98df-537e38477399.png)

- Case Details

![case details doc](https://user-images.githubusercontent.com/99625111/169188932-faebb43d-9b7c-4a15-a0f5-aa27646dd8ec.png)

- Select Nurse to Case

![select nurse doc](https://user-images.githubusercontent.com/99625111/169188939-5cae6dae-d676-4b6e-bd1d-6e40483ad2c7.png)

- Here Doctor Can Write Note And Send Requests To Nurse And Analysis Employees 

![case details request doc](https://user-images.githubusercontent.com/99625111/169188930-3a6efa7e-a741-4b18-86c5-326888c0d356.png)


- Choose Medical Record Requests That Doctor need them from Analysis

![medical record request doc](https://user-images.githubusercontent.com/99625111/169188946-45c26130-cd4a-498a-836a-f9946ea9b47d.png)

- Choose Medical Measurement Requests That Doctor need them from Nurse

![medical measurement request doc](https://user-images.githubusercontent.com/99625111/169188905-7fe7c7eb-5d64-4acb-8027-f26ab884f430.png)

- Select Analysis Employee

![select analysis doc](https://user-images.githubusercontent.com/99625111/169188943-80b17d8f-76ef-4266-b73c-3e8bb7bfb694.png)

- If Doctor need to End Case

![end case doc](https://user-images.githubusercontent.com/99625111/169188911-8e0efa57-b4e9-43c2-827c-c9ca04d3f534.png)

## Screenshots and explain of the Nurse's use of the application

- Nurse's interface

![nurse](https://user-images.githubusercontent.com/99625111/169370628-9f835854-d529-4031-9d81-3ac50c1903bf.jpg)

- Nurse's profile

![nurse profile](https://user-images.githubusercontent.com/99625111/169370622-e48809ed-dc95-4a90-b79a-f3c1363a4316.jpg)

- Nurse's Cases

![cases](https://user-images.githubusercontent.com/99625111/169370607-5102a934-0a3b-4d74-85ee-6216f6c7c1ce.jpg)

- Case Details

![case details](https://user-images.githubusercontent.com/99625111/169370602-6efc6f03-96b5-44d2-88c1-edf4de8ebf76.jpg)

![case details 2](https://user-images.githubusercontent.com/99625111/169370587-ea3a0682-6020-4005-a1d4-1fe6e26625c5.jpg)


- Add Measurements

![add measurement](https://user-images.githubusercontent.com/99625111/169370635-ab1f545e-17cb-4153-bdbd-d599140dfdfd.jpg)


## Screenshots and explain of the Analysis's use of the application

- Analysis's Interface

![analysis](https://user-images.githubusercontent.com/99625111/169387793-19e81747-1d7d-4d2f-8f19-173a85f0db89.jpg)

- Analysis's Profile

![analysis profile](https://user-images.githubusercontent.com/99625111/169387817-b949428a-977b-4520-8224-9e4279c667f1.jpg)


- Analysis's Cases

![cases analysis](https://user-images.githubusercontent.com/99625111/169387806-744ad431-8a0a-4413-9818-9dfb5646f46d.jpg)


- Case Details

![case details analysis](https://user-images.githubusercontent.com/99625111/169387804-4753e4a8-4e88-4b02-89b8-2a9a237ee922.jpg)

![case details 2 analysis](https://user-images.githubusercontent.com/99625111/169387799-24c7a63d-48d3-436a-9d25-42062d6b9ed1.jpg)


- Add Medical Record

![add medical record](https://user-images.githubusercontent.com/99625111/169387810-71e21cfb-212d-4922-bc4b-225694c6a5e5.jpg)


## Screenshots and explain of the Manger's use of the application

- Manager's interface

![manger](https://user-images.githubusercontent.com/99625111/169424742-0a7fa29f-14e7-49ca-bb5b-73db350f43cd.jpg)

- Manger's Profile

![profile](https://user-images.githubusercontent.com/99625111/169424745-b36d64e4-9306-4e74-9925-1acb6f74a0d2.jpg)

- Show All Employees In Hospital System

![employee manager](https://user-images.githubusercontent.com/99625111/169424737-580954b2-9cf7-45ca-a13c-023404710723.jpg)

- Employee's Profile

![employeeprofile](https://user-images.githubusercontent.com/99625111/169167749-d545e18a-0413-4af8-9386-a99bd515fd17.png)

- Create New User Like What HR Does

![new user manager](https://user-images.githubusercontent.com/99625111/169424744-6ae3348a-f825-445a-a747-951964dfb81d.jpg)

- Show All Cases

![cases manager](https://user-images.githubusercontent.com/99625111/169424735-03e894dd-c698-4e18-8f2a-5c7b4fc6db0d.jpg)

- Case Details

![case details](https://user-images.githubusercontent.com/99625111/169424731-f045681a-527b-4433-ab04-69cb57dcbb05.jpg)

![case details 2](https://user-images.githubusercontent.com/99625111/169424726-67cc4c64-78e2-4a98-905d-831e497e4e61.jpg)

![case details 3](https://user-images.githubusercontent.com/99625111/169424728-2b05bb55-ae77-4df9-8854-d706404083b5.jpg)



## Creation of employee tasks by the Manager

- Show All Tasks 

![tasks manger](https://user-images.githubusercontent.com/99625111/169426799-fb9c38dc-58ec-422b-8070-999348803ad6.jpg)

- Create Task

![create task manager](https://user-images.githubusercontent.com/99625111/169426782-e9779094-1000-4ba5-ad80-f1f4bb879248.png)

- Select Employee That Manger Need To Do Tasks

![select employee](https://user-images.githubusercontent.com/99625111/169426786-a54ecde0-d0d1-4ea9-b852-e688be042e07.png)

- Task Details 

![Task Details manger](https://user-images.githubusercontent.com/99625111/169426796-422ecda7-7a84-40cd-95c6-981dd0d05970.png)

- Tasks Appears with Employee

![Tasks Doctor](https://user-images.githubusercontent.com/99625111/169426801-6c328b3c-5e7b-4266-827e-c7f32d7ce6b7.jpg)

- Task Details Appears With Employee
 
![Task details from Doctor](https://user-images.githubusercontent.com/99625111/169426793-f339e260-acca-43c1-aaf9-5871da7cacb4.png)

![Task Details From Doctor 2](https://user-images.githubusercontent.com/99625111/169426790-df0db954-1f9b-4f36-8721-0b237c23cd45.png)


## Create an employee report

- Employee's Reports

![reports employee](https://user-images.githubusercontent.com/99625111/169431340-f4873778-c8ab-4800-a7fc-92c8993ba60a.png)

- Create Report

![create report employee](https://user-images.githubusercontent.com/99625111/169431326-9c3e5966-7b68-4e5f-ab59-df00fd398d19.png)

- Show All Reports To Manger

![reports manger](https://user-images.githubusercontent.com/99625111/169431343-94bfdfef-a754-4c84-91ce-623e6f153053.png)

- Manager That Can Finish Report To Employee

![report details manager](https://user-images.githubusercontent.com/99625111/169431331-ffac35b4-5b58-4b14-b070-cfc5dfd228be.png)

- Report Details 

![report details employee and manger](https://user-images.githubusercontent.com/99625111/169431329-009456f6-6721-486b-ad4a-516cfc2a9495.png)

![report details2 manget and employee](https://user-images.githubusercontent.com/99625111/169431338-1dc639d2-03f3-4fb9-827e-cbcbe369333f.png)


## Recording the employee's attendance and departure from the Hospital


![attendane and leaving](https://user-images.githubusercontent.com/99625111/169434220-a1f816c9-8291-48c0-8b25-077fa53f9a54.png)

![attendance](https://user-images.githubusercontent.com/99625111/169434230-584d5a35-b556-44da-97e8-031702457191.png)

![leaving](https://user-images.githubusercontent.com/99625111/169434224-4270716f-9aad-4425-8b73-64c1555188f0.png)


## Download Apk And Contact

- Downioad Apk : https://www.mediafire.com/file/75cdof6800w7doy/Medical+System.apk/file

- Contact : joussefahmed2012@gmail.com
