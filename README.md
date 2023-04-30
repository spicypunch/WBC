## When is the bus coming

WBC는 When is the bus coming의 약자로 버스가 언제 도착하는지 확인할 수 있는 앱입니다.
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
  - Databindig: xml 파일을 통해 선언적으로 데이터와 뷰를 바인딩합니다.
- Repository Pattern을 통해 로컬 데이터베이스와 원격 서버에서 데이터를 가져오는 방식을 추상화 하여 가독성과 유지보수성을 높였습니다.
- Hilt를 통해 의존성을 주입하였습니다.
- Coroutine을 이용해 비동기 처리를 하였습니다.
- TabLayout으로 프래그먼트를 쉽게 다루고 ViewPager2로 스와이프를 통해 화면 이동을 지원합니다.
- Glide로 이미지 관련 작업을 처리하였습니다.
- Kakaomap을 사용하여 지도를 구성하였습니다.
<br>
<br>
<br>
### UI
--------
![Screen_Recording_20230430_205555_ _1](https://user-images.githubusercontent.com/72846127/235352134-8b159454-2fed-4f1a-adf4-1267996fe4a8.gif)
![Screen_Recording_20230430_205555_ _2](https://user-images.githubusercontent.com/72846127/235352137-6786924a-86dd-4d36-87a0-c5e81f338c99.gif)
![Screen_Recording_20230430_205555_ _3](https://user-images.githubusercontent.com/72846127/235352138-e75cf307-e685-4eda-b8d4-238718a1dd6e.gif)
![Screen_Recording_20230430_205821_ _1](https://user-images.githubusercontent.com/72846127/235352141-73dc1d22-b882-45d7-a8dc-8f0e1ac16ad2.gif)
![Screen_Recording_20230430_205821_ _2](https://user-images.githubusercontent.com/72846127/235352142-e9c4dd88-2034-440d-b2b4-cb7df8621b16.gif)
![Screen_Recording_20230430_205821_ _3](https://user-images.githubusercontent.com/72846127/235352143-b76ad7ad-b4e4-4067-abb4-5d73590e4fb8.gif)
![Screen_Recording_20230430_205821_ _4](https://user-images.githubusercontent.com/72846127/235352144-498638e5-7112-4471-ad00-e3f9fecb04c5.gif)
![Screen_Recording_20230430_205129_ _1](https://user-images.githubusercontent.com/72846127/235352145-dc2b8508-af54-48dc-8da3-6e596aa12b3c.gif)
![Screen_Recording_20230430_205129_ _2](https://user-images.githubusercontent.com/72846127/235352147-4da115a6-d97e-49e5-8d5c-7f14de367e64.gif)
![Screen_Recording_20230430_205129_ _3](https://user-images.githubusercontent.com/72846127/235352149-1a93c4b6-4929-4ac4-8345-d2a7c431e3c2.gif)
