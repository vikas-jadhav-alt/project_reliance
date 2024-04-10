

#Title 
This is a account management service providing you various features like
.... deposit and withdrawing money,..etc


# Tools used
- Spring Boot
- MySQL 8
- Java 8

>> Make sure you have db with below name in mysql:
>> >> 'project-rel-db1'


# API Testing
- For documentation, we have used Swagger after restarting the API.
go to below link to go swagger dashboard:
>> http://localhost:8080/swagger-ui/index.html

## API : To Check if Service is Running or not
curl -X 'GET' \
  'http://localhost:8080/api/v1/account/service-status' \
  -H 'accept: */*'
  
  
## API: To create a new account:
 POST http://localhost:8080/api/v1/account/create
 
 Request Body:
 {
  "accHolderName": "<Any Account Holder Name>"
}
>> A new account will be created and you will get account id in the response.

## API : Get Details of the Account Created by Account ID

GET http://localhost:8080/api/v1/account/{{AccountID}}
.....

## API : To update account balance: to withdraw and deposit ammount:
POST http://localhost:8080/api/v1/account/update/{{AccountID}}

ResquestBody:
{
  "mode": "DEPOSIT",
  "amount": 20000
}

//Note: mode = DEPOSIT|WITHDRAW


 
