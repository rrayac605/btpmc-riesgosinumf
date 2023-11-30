#Introducción
Batch encargado de asignar a la OOAD y subdelegación del registro patronal del NSS, 
los riesgos de trabajo en donde no tiene asignada UMF de adscripción, OOAD de adscripción y Subdelegación de adscripción.

--->Prueba pull request<---
/*************************/

#Variables de entorno

============== AMBIENTE DE QA ==============

Para que este batch pueda ser ejecutado deberán de existir las siguietnes variables de entorno en el SO anfitrión donde se ejecute el jar

- portRiesgoSinUmf -> 9020
- authenticationdatabaseMongo -> PMCQA01
- usrMongoCifras -> pmcbatch
- pwMongoCifras -> pmcbatch0
- databaseMongo -> PMCQA01
- portMongo -> 27017
- hostMongo -> 10.100.8.78
- fileLogRiesgoSinUmf -> /weblogic/pmc/logs/btpmc-riesgosinumf.log
- cron.expression.riesgoSinUmf -> 0 0 23 ? * 6L

============== AMBIENTE DE UAT ==============

Para que este microservicio pueda ser ejecutado deberán de existir las siguietnes variables de entorno en el SO annfitrión donde se ejecute el jar

- portRiesgoSinUmf -> 9020
- authenticationdatabaseMongo -> PMCUAT01
- usrMongoCifras -> pmcmodifica
- pwMongoCifras -> pmcmodifica0
- databaseMongo -> PMCUAT01
- portMongo -> 27017
- hostMongo -> 10.100.8.80
- fileLogRiesgoSinUmf -> /weblogic/pmc/logs/btpmc-riesgosinumf.log
- cron.expression.riesgoSinUmf -> 0 0 23 ? * 6L

============== AMBIENTE DE PROD ==============

Para que este microservicio pueda ser ejecutado deberán de existir las siguietnes variables de entorno en el SO annfitrión donde se ejecute el jar

- portRiesgoSinUmf -> 9020
- authenticationdatabaseMongo -> PMC
- usrMongoCifras -> pmcbatch
- pwMongoCifras -> 	pmCb4tch%%
- databaseMongo -> PMC
- portMongo -> 27017
- hostMongo -> 10.100.6.172
- fileLogRiesgoSinUmf -> /weblogic/pmc/logs/btpmc-riesgosinumf.log
- cron.expression.riesgoSinUmf -> 0 0 23 ? * 6L