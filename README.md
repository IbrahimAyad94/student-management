# student-management 
  student management system where users can register, login, view courses

# technologies 
  1- programming languge: java 8 
  2- DBMS: postgress
  2- Cache server: Redis 
  3- JWT: jjwt-0.9.1
  4- Frameworks: srping(boot2.5.5/mvc/security/datajpa)
  5- ORM: Hibernate 
  6- Mapping Lib(from Dto to Entity and vice versa): mapstruct
  7- PDF Generator: jasperreports-6.15.0
  8- View Layer: JSP 
  
# steps to run the app 
  1- import app to your IDE
  2- Change postgress info yours properties
  3- Change Redis properties to yours properties
  3- Run App with tomcat 
  
  
# apis
1- Register 
  -URL: 
    POST {{serverIpAndPort}}/student-management/api/v1/auth/register
    
  -Auth: 
    No Auth
    
  -Body: 
    {
      "email": "api@api.com",
      "mobileNumber": "01063819472",
      "name": "Ibrahim",
      "password":"12345"
    }
    
  -Response: 
    {
      "id": 2,
      "email": "api@api.com",
      "mobileNumber": "01063819472",
      "name": "Ibrahim"
    }

2- Login 
  -URL: 
      POST {{serverIpAndPort}}/student-management/api/v1/auth/login
      
    -Auth: 
      No Auth
      
    -Body: 
      {
        "email": "api@api.com",
        "password":"12345"
      }
    -Response: 
      {
      "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGlAYXBpLmNvbSIsImV4cCI6MTYzNTIzNjUxMCwidXNlcklkIjoyLCJpYXQiOjE2MzUyMzYyMTB9.75uuu5bJFMW7Ya",
      "student": {
          "id": 2,
          "email": "api@api.com",
          "mobileNumber": "01063819472",
          "name": "Ibrahim"
        }
      }
      
      
    3- View Courses Api 
    -URL: 
      GET {{serverIpAndPort}}/student-management/api/v1/course/view-courses
      
    -Auth: 
      Bearer Token
      
    -Body: 
      
      
    -Response: 
      [
        {
            "id": 1,
            "name": "Java",
            "startDate": "3921-11-22T22:00:00.000+00:00",
            "endDate": "3922-01-22T22:00:00.000+00:00"
        },
        {
            "id": 2,
            "name": "PHP",
            "startDate": "3921-10-22T22:00:00.000+00:00",
            "endDate": "3922-01-22T22:00:00.000+00:00"
        },
        {
            "id": 3,
            "name": "Paython",
            "startDate": "3921-12-22T22:00:00.000+00:00",
            "endDate": "3921-04-22T22:00:00.000+00:00"
        }
      ]
      
      
    4- Export Course Scedule To PDF(Base64)
    -URL: 
      GET {{serverIpAndPort}}/student-management/api/v1/course/{courseId}/export-schedule
      
    -Auth: 
      Bearer Token
      
    -Body: 
      
      
    -Response: 
      Base64 String like (JVBERi0xLjUKJeLjz9MKNSAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDU1MD4.... )
      
      
# web 
1- Register Page 
  -URL: 
    {{serverIpAndPort}}/student-management/register
    After Register Redirect To Login Page 
2- Login  Page 
  -URL: 
    {{serverIpAndPort}}/student-management/login
     After Login Redirect To View Courses Page 
3- View Courses Page 
  -URL:
    {{serverIpAndPort}}/student-management/pages/course/view-courses
    - List Courses In Table And Every Row Has Link To View Course Schedule If Clicked Open View Course Schedule Page 
    - You Can Logout And Redirect To Login Page Again 
4- View Course Schedule Page 
    -URL: 
      {{serverIpAndPort}}/student-management/pages/course/{courseId}/export-schedule
      Preview Course Schedule As PDF In Browser 
