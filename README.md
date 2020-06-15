# Hotel Reservation System

**Option 20**

The client fills out the Application, indicating the number of seats in the room, class of apartments and time of stay.   
The administrator views the received Application, selects the most suitable of the Available Numbers, 
after which the system issues an Invoice to the Client.

**Вариант 20**

Клиент заполняет Заявку, указывая количество мест в номере, класс апартаментов и время пребывания.  
Администратор просматривает поступившую Заявку, выделяет наиболее подходящий из доступных Номеров, после чего система выставляет Счет Клиенту. 

## Installation and Running

**_Requirements:_**
* JDK 1.8
* Apache Maven
* PostgreSql

**_Running the project:_**
1. Clone this project to your local repository
2. Change _spring.jpa.hibernate.ddl-auto_ in _application.properties_ from _update_ to _create_
3. Update database login and password in _application.properties_
4. Run **-mvn spring-boot:run** from project root folder
5. The prokect will start on 8080 port. Use http://localhost:8080/ to view web application


