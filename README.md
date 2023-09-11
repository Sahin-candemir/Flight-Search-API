# Flight-Search-API
Amadeus Case Study 

## Kullanılan Teknolojiler
* Java
* Maven
* Spring Boot (Version : 2.7.4)
* Postqresql
* Dependencies
==> Spring Web
-- Spring Boot DevTools
-- Lombok
-- Spring Data JPA
-- Validation
--Spring Security
--Jwt Authontication

## Run
- Project > Run As> Maven Build  ``spring-boot:run``

### Flight Controller

Method   |   Path   |   Description|Request Parameters
---------|----------|--------------|----------
POST     |/api/flight | Bir uçuşu kaydeder| FlightDto(Json Body)
DELETE   |/api/flight/{id}|Uçuş bilgilerini siler|integer (id)
PUT      |/api/flight/{id}| Uçuş bilgilerini günceller|integer (id) <p/> UpdateFlightDto(Json body)
GET      |/api/flight| Tüm Uçuş bilgilerini alır|
GET      |/api/flight/{id} | id bilgisine sahip uçuşun bilgilerini alır|integer(id)

### Airport Controller

Method   |   Path   |   Description|Request Parameters
---------|----------|--------------|----------
POST     |/api/airport | Havalanını veritabanına kaydeder |AirportDto(Json Body)
DELETE   |/api/airport/{id}|Havaalanı bilgilerini siler|integer (id)
PUT      |/api/airport/{id}| Havaalanı bilgilerini günceller|integer (id) <p/> AirportDto(Json body)
GET      |/api/airport| Tüm Havaalanı bilgilerini alır|
GET      |/api/airport/{id} | id bilgisine sahip havaalanının bilgilerini alır|integer(id)
### Search Controller
*``http://localhost:8080/api/search?from=ankara&to=istanbul&departure=2023-10-01&arrival=2023-10-03``
*@RequestParam ile path içerisindeki arama yapılacak uçuş bilgileri için gerekli parameteri alır

Method   |   Path   |   Description|Request Parameters
---------|----------|--------------|----------
GET      |/api/search| tek yada çift yönlü uçuş bilgilerini alır|requestParam 

### Auth Controller
* Register ve Login işlemelerinin gerçekleştiği kısımdır

Method   |   Path   |   Description|Request Parameters| Response
---------|----------|--------------|----------
POST     |/api/auth/login | Sisteme giriş yapar  |LoginDto(Json Body)|Bearer token
POST     |/api/auth/register | Sisteme kayıt yapar  |RegisterDto(Json Body)|String

 
