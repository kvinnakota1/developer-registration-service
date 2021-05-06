
MicroService: developer-registration-service

1. How to build?

    mvn clean install

2. How to run the application?

    mvn spring-boot:run


3. What Database is used?

    Currently this micro service is using in-memory H2 DB for testing

4. This microservice is running on port 9091 in embedded tomcat server

5. Schema
        Table Name: DEVELOPER_REGISTRATION
        Columns:
           ID VARCHAR2(255) Primary Key
           REGISTERED_AT Date Not Null
           FIRST_NAME VARCHAR2(255) Not Null
           LAST_NAME VARCHAR2(255) Not Null
           EMAIL_ID VARCHAR2(255) Unique and Not Null
           INSTAGRAM_USER_NAME VARCHAR2(255)
           TWITTER_USER_NAME VARCHAR2(255)
           PRODUCT_ID NUMBER(4) Not Null
           DEVELOPER_ENVIRONMENT VARCHAR2(255) Not Null
           LOCATION VARCHAR2(255) Not Null
           INDUSTRY VARCHAR2(255) Not Null



6. Following end points has been exposed to manage developer's registrations

    i) http://localhost:9091/developerregistrations - POST method

        purpose: Saves the developer registration information
        Request: DeveloperRegistration DTO

            {
                "firstName" : "Kiran",
                "lastName" : "Vinnakota",
                "emailId" : "testk@test.com",
                "registeredAt" : "2019-07-10T11:00:01-05:00",
                "instagramUserName" : "testkInstagram",
                "twitterUserName" : "testkTwitter",
                "productId" : 100,
                "developerEnvironment" : "Java",
                "location" : "San Ramon, CA",
                "industry" : "IoT"
            }

       Response:  DeveloperRegistration DTO with status code 201


            {
                "id": "5ff23b93-68bc-433c-8d5c-1398f9b30323",
                "registeredAt": "2019-07-10T16:00:01.000+00:00",
                "firstName": "Kiran",
                "lastName": "Vinnakota",
                "emailId": "testk@test.com",
                "instagramUserName": "testkInstagram",
                "twitterUserName": "testkTwitter",
                "productId": 100,
                "productName": "Cisco Webex",
                "developerEnvironment": "Java",
                "location": "San Ramon, CA",
                "industry": "IoT"
            }

     ii)  http://localhost:9091/developerregistrations  - GET methods

           Purpose: Gets all the registrations from entire system

           Response: Returns all registrations as list of DeveloperRegistration DTO with status code as 200

           [
               {
                   "id": "5ff23b93-68bc-433c-8d5c-1398f9b30323",
                   "registeredAt": "2019-07-10T16:00:01.000+00:00",
                   "firstName": "Kiran",
                   "lastName": "Vinnakota",
                   "emailId": "testk@test.com",
                   "instagramUserName": "testkInstagram",
                   "twitterUserName": "testkTwitter",
                   "productId": 100,
                   "productName": "Cisco Webex",
                   "developerEnvironment": "Java",
                   "location": "San Ramon, CA",
                   "industry": "IoT"
               },
               {
                   "id": "3b0fee56-5206-4f4e-a80a-3d90eaa2e3e8",
                   "registeredAt": "2019-07-10T16:00:01.000+00:00",
                   "firstName": "John",
                   "lastName": "Miller",
                   "emailId": "testj@test.com",
                   "instagramUserName": "testjInstagram",
                   "twitterUserName": "testjTwitter",
                   "productId": 100,
                   "productName": "Cisco Webex",
                   "developerEnvironment": "Java",
                   "location": "San Ramon, CA",
                   "industry": "IoT"
               },
               {
                   "id": "ed4a461d-aeaf-4c30-9b4a-6ab7aa69044c",
                   "registeredAt": "2019-07-10T16:00:01.000+00:00",
                   "firstName": "Ravi",
                   "lastName": "Chopra",
                   "emailId": "testr@test.com",
                   "instagramUserName": "testrInstagram",
                   "twitterUserName": "testrTwitter",
                   "productId": 101,
                   "productName": "Cisco Systems",
                   "developerEnvironment": "Java",
                   "location": "Los Angles, CA",
                   "industry": "Retail"
               },
               {
                   "id": "e59c6dad-f054-4900-b462-295217a17039",
                   "registeredAt": "2019-07-10T16:00:01.000+00:00",
                   "firstName": "Harish",
                   "lastName": "Kumar",
                   "emailId": "testhk@test.com",
                   "instagramUserName": "testhkInstagram",
                   "twitterUserName": "testhkTwitter",
                   "productId": 101,
                   "productName": "Cisco Systems",
                   "developerEnvironment": "Java",
                   "location": "Los Angles, CA",
                   "industry": "Retail"
               }
           ]

     iii) http://localhost:9091/developerregistrations/5ff23b93-68bc-433c-8d5c-1398f9b30323  - GET Method

          Purpose: Gets the Registration object by registration id
          Request: Send id as part of url path
          Response: Returns DeveloperRegistration Object with status code as 200

          {
              "id": "5ff23b93-68bc-433c-8d5c-1398f9b30323",
              "registeredAt": "2019-07-10T16:00:01.000+00:00",
              "firstName": "Kiran",
              "lastName": "Vinnakota",
              "emailId": "testk@test.com",
              "instagramUserName": "testkInstagram",
              "twitterUserName": "testkTwitter",
              "productId": 100,
              "productName": "Cisco Webex",
              "developerEnvironment": "Java",
              "location": "San Ramon, CA",
              "industry": "IoT"
          }

     iv) http://localhost:9091/developerregistrations   - PUT Method

         Purpose: This method will update the registration details
         Request: DeveloperRegistration DTO

         {
             "id": "5ff23b93-68bc-433c-8d5c-1398f9b30323",
             "registeredAt": "2019-07-10T16:00:01.000+00:00",
             "firstName": "Kiran",
             "lastName": "Vinnakota",
             "emailId": "testk@test.com",
             "instagramUserName": "testkInstagram",
             "twitterUserName": "testkTwitter",
             "productId": 100,
             "productName": "Cisco Webex",
             "developerEnvironment": "Java",
             "location": "San Ramon, CA",
             "industry": "IoT"
         }

         Response: Returns updated DeveloperRegistration DTO with status code as 201

        {
            "id": "5ff23b93-68bc-433c-8d5c-1398f9b30323",
            "registeredAt": "2019-07-10T16:00:01.000+00:00",
            "firstName": "Kiran",
            "lastName": "Vinnakota",
            "emailId": "testk@test.com",
            "instagramUserName": "testkInstagram",
            "twitterUserName": "testkTwitter",
            "productId": 100,
            "productName": "Cisco Webex",
            "developerEnvironment": "Java",
            "location": "San Ramon, CA",
            "industry": "IoT"
        }

     v) http://localhost:9091/developerregistrations/groupbyIndustryAndLocation  - GET Method

        Purpose: This methods will get all the registrations grouping by Industry and Location
        Response: This will return list of RegistrationsGroupByLocationAndIndustry DTOs with group by industry and location

        [
            {
                "industry": "IoT",
                "location": "San Ramon, CA",
                "developerRegistrations": [
                    {
                        "id": "5ff23b93-68bc-433c-8d5c-1398f9b30323",
                        "registeredAt": "2019-07-10T16:00:01.000+00:00",
                        "firstName": "Kiran",
                        "lastName": "Vinnakota",
                        "emailId": "testk@test.com",
                        "instagramUserName": "testkInstagram",
                        "twitterUserName": "testkTwitter",
                        "productId": 100,
                        "productName": "Cisco Webex",
                        "developerEnvironment": "Java",
                        "location": "San Ramon, CA",
                        "industry": "IoT"
                    },
                    {
                        "id": "3b0fee56-5206-4f4e-a80a-3d90eaa2e3e8",
                        "registeredAt": "2019-07-10T16:00:01.000+00:00",
                        "firstName": "John",
                        "lastName": "Miller",
                        "emailId": "testj@test.com",
                        "instagramUserName": "testjInstagram",
                        "twitterUserName": "testjTwitter",
                        "productId": 100,
                        "productName": "Cisco Webex",
                        "developerEnvironment": "Java",
                        "location": "San Ramon, CA",
                        "industry": "IoT"
                    }
                ]
            },
            {
                "industry": "Retail",
                "location": "Los Angles, CA",
                "developerRegistrations": [
                    {
                        "id": "ed4a461d-aeaf-4c30-9b4a-6ab7aa69044c",
                        "registeredAt": "2019-07-10T16:00:01.000+00:00",
                        "firstName": "Ravi",
                        "lastName": "Chopra",
                        "emailId": "testr@test.com",
                        "instagramUserName": "testrInstagram",
                        "twitterUserName": "testrTwitter",
                        "productId": 101,
                        "productName": "Cisco Systems",
                        "developerEnvironment": "Java",
                        "location": "Los Angles, CA",
                        "industry": "Retail"
                    },
                    {
                        "id": "e59c6dad-f054-4900-b462-295217a17039",
                        "registeredAt": "2019-07-10T16:00:01.000+00:00",
                        "firstName": "Harish",
                        "lastName": "Kumar",
                        "emailId": "testhk@test.com",
                        "instagramUserName": "testhkInstagram",
                        "twitterUserName": "testhkTwitter",
                        "productId": 101,
                        "productName": "Cisco Systems",
                        "developerEnvironment": "Java",
                        "location": "Los Angles, CA",
                        "industry": "Retail"
                    }
                ]
            }
        ]


     vi) http://localhost:9091/developerregistrations/35e90046-e9a1-4bc4-86a2-3eb073defe52  - DELETE method

         Purpose: This method will delete the registration by registration id

         Response: This will return no content with status code as 204