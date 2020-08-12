# Getting Started

### The issue: 
When rest controller calls a service that updates and returns an entity from database with initialized transition field
within this service - the returned entity has initialized transition filed.

When the same service is called from cron-based service the returned entity has  transition filed eq `null`

So the same service method  behaves differently if it's call from rest controller and if it's called cron service.
#
#### preparetion work


* Go to `deploy/mariadb` and build the database. It has all needed ddl and dml scripts.
* Start newly created mariadb on port `3310` 
#
### Enough words show me code 
The app has two integration tests that proves odd behaviour of entity manager called within cron and rest controller.
I would recommend to run them via Idea so you see the output logsgradle test -i
```
gradle test --tests com.oe.spring.test.crontest.CronTriggerServiceTest 
gradle test --tests com.oe.spring.test.crontest.RestControllerTriggerTest
```
The above commands will run tests with output logs that will show the state of transition filed during calls from cron 
service and from rest controller. Grep for logs that have `StudentEntity` word
#
We can also run the app and do the same check.
* Run the app. validate that cron job was executed on the start up. You should see next log in output 
```$xslt
2020-08-12 18:08:47.777  INFO 31206 --- [   scheduling-1] c.o.s.t.c.s.impl.StudentServiceImpl      : new entity before saving: StudentEntity(id=3, firstName=updated_first_name, lastName=test, isFresher=true)
2020-08-12 18:08:47.790  INFO 31206 --- [   scheduling-1] c.o.s.t.c.s.impl.StudentServiceImpl      : saved entity: StudentEntity(id=3, firstName=updated_first_name, lastName=test, isFresher=null) from: CronTriggerService 
```
Lets call the same service but from rest controller: 
```bash
curl -X POST localhost:8080/trigger
```
The expected output should be next: 
```
2020-08-12 18:11:40.857  INFO 31709 --- [nio-8080-exec-2] c.o.s.t.c.s.impl.StudentServiceImpl      : new entity before saving: StudentEntity(id=3, firstName=updated_first_name, lastName=test, isFresher=true)
2020-08-12 18:11:40.858  INFO 31709 --- [nio-8080-exec-2] c.o.s.t.c.s.impl.StudentServiceImpl      : saved entity: StudentEntity(id=3, firstName=updated_first_name, lastName=test, isFresher=true) from: ServiceTriggerRestController 
```


