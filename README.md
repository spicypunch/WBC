## When is the bus coming

WBC는 When is the bus coming의 약자로 버스가 언제 도착하는지 확인할 수 있는 앱입니다.

갤럭시 워치 앱: https://github.com/spicypunch/WBC_for_Watch

<br>
<br>
<br>

### 순서도
----------
#### 로그인 / 회원가입 / 즐겨찾기 추가
![image](https://user-images.githubusercontent.com/72846127/235492675-2e5012cc-2ee6-4a2a-b343-d33b645b7f11.png)

<br>

#### 검색어 저장 및 삭제
![image](https://user-images.githubusercontent.com/72846127/235492755-b777da3d-1d03-4de3-bee4-46231b22f8e9.png)

<br>

#### 카카오맵 키워드 검색 / 버스 정보
![image](https://user-images.githubusercontent.com/72846127/235492887-9554650d-d7d3-4e8c-baf8-db95258de1b7.png)



<br>
<br>
<br>

### 주요 기술
---------
- Retrofit을 통한 카카오맵 API와 버스 정류소 현황 API, 버스 도착 정보 API를 사용하였습니다.
- Room 라이브러리를 이용하여 내가 검색한 주소 정보를 기록, 삭제합니다.
- MVVM 패턴으로 작성되었습니다.
  - LiveData를 이용해 데이터가 변경될 때 뷰에 알립니다.
  - AAC ViewModel를 이용해 데이터를 보존합니다.
  - Databindig을 이용해 선언적으로 데이터와 뷰를 바인딩합니다.
- Repository Pattern을 통해 로컬 데이터베이스와 원격 서버에서 데이터를 가져오는 방식을 추상화 하여 가독성과 유지보수성을 높였습니다.
- Hilt를 통해 의존성을 주입하였습니다.
- Coroutine을 이용해 비동기 처리를 하였습니다.
- TabLayout으로 프래그먼트를 쉽게 다루고 ViewPager2로 스와이프를 통해 화면 이동을 지원합니다.
- Firebase를 통해 즐겨찾기에 추가한 버스 정보를 저장합니다.
- Glide로 이미지 관련 작업을 처리하였습니다.
- Kakaomap을 사용하여 지도를 구성하였습니다.
<br>
<br>
<br>

### UI
--------

#### 주소 검색
![Screen_Recording_20230430_205129_ _1](https://user-images.githubusercontent.com/72846127/235352145-dc2b8508-af54-48dc-8da3-6e596aa12b3c.gif)

- 카카오맵 API에서 지원하는 키워드 검색을 통해 내 위치 주변 시설을 검색합니다.

#### 정류장에 도착 예정 버스 확인
![Screen_Recording_20230430_205129_ _2](https://user-images.githubusercontent.com/72846127/235352147-4da115a6-d97e-49e5-8d5c-7f14de367e64.gif) ![Screen_Recording_20230430_205129_ _3](https://user-images.githubusercontent.com/72846127/235352149-1a93c4b6-4929-4ac4-8345-d2a7c431e3c2.gif)



- 검색한 시설 주변 정류장에서 내가 북마크에 추가하고싶은 버스를 선택합니다.
- 현재 로그인이 되어있지 않아 로그인을 진행해주세요 라는 Toast 메시지가 나옵니다. 

#### 계정 생성 및 로그인
![Screen_Recording_20230430_205555_ _1](https://user-images.githubusercontent.com/72846127/235352134-8b159454-2fed-4f1a-adf4-1267996fe4a8.gif) ![Screen_Recording_20230430_205555_ _2](https://user-images.githubusercontent.com/72846127/235352137-6786924a-86dd-4d36-87a0-c5e81f338c99.gif) ![Screen_Recording_20230430_205555_ _3](https://user-images.githubusercontent.com/72846127/235352138-e75cf307-e685-4eda-b8d4-238718a1dd6e.gif)

- 프로필 사진을 등록할 수 있지만 원치 않으면 등록하지 않아도 됩니다. 카메라와 내부 스토리지를 통해 이미지를 가져옵니다.
- 이메일이 중복되었는지, 기입한 비밀번호가 일치하지 않은지 검사 후 회원 가입을 진행합니다. 
- 생성한 계정을 통해 로그인을 진행하고 프로필 사진에 방금 등록한 사진이 나오는 걸 확인할 수 있습니다.

#### 원하는 버스 추가
![Screen_Recording_20230430_205821_ _1](https://user-images.githubusercontent.com/72846127/235352141-73dc1d22-b882-45d7-a8dc-8f0e1ac16ad2.gif) ![Screen_Recording_20230430_205821_ _2](https://user-images.githubusercontent.com/72846127/235352142-e9c4dd88-2034-440d-b2b4-cb7df8621b16.gif)

- 검색 기록을 통해 다시 한번 전에 확인했던 정류소의 버스 도착 목록 화면에 들어갑니다.
- 원하는 버스를 즐겨찾기에 추가합니다.

#### 즐겨찾기 확인
![Screen_Recording_20230430_205821_ _3](https://user-images.githubusercontent.com/72846127/235352143-b76ad7ad-b4e4-4067-abb4-5d73590e4fb8.gif)

- 즐겨찾기 탭으로 들어가 내가 등록한 버스 정보가 나오는지 확인합니다.

<br>
<br>
<br>

### 기능 업데이트
--------
#### 즐겨찾기 페이지
![Screen_Recording_20230508_165423_ _1](https://user-images.githubusercontent.com/72846127/236789975-d3e60074-c32b-42b7-b233-8f3053cbd106.gif)
- 비로그인 상태에서 즐겨찾기 페이지로 접근 시 NPE으로 앱이 종료되는 현상 발생
- 로그인 여부 검사 후 로그인이 안 되어있는 경우에 로그인을 하라는 Toast 메시지 추가

<br>

![Screen_Recording_20230508_165647_ _1](https://user-images.githubusercontent.com/72846127/236789983-bae071fc-7890-425a-bcc7-cd7d547e9c01.gif)
- 새로고침 버튼을 눌렀을 경우 버스 도착 정보 갱신

<br>

![Screen_Recording_20230508_183500_ _1](https://user-images.githubusercontent.com/72846127/236791203-3cf259d6-6a47-4f84-a9db-bacbec0e5753.gif)
- 삭제 이미지를 눌렀을 경우 데이터 삭제

<br>

#### 버스 정보 
![Screen_Recording_20230516_151714_ _1](https://github.com/spicypunch/WBC/assets/72846127/0c4543d4-23fc-4ada-9f2e-986c2ab79a72)
- 즐겨찾기 버튼을 눌렀을 경우 이미지 변경



