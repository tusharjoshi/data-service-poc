# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)


### Local testing ###

* Use following end points for local testing of Device domain rest end points. Calls are route through data service only which acts as proxy.

 
* curl -X GET http://localhost:8080/internal/devices/returnall
* curl -X POST http://localhost:8080/internal/devices/create -H  "Content-Type: application/json"  -d "{\"name\":\"Device 5\"}"


* Use following end points for local testing of Machine domain rest end points. 


* curl -X GET http://localhost:8080/internal/machines/returnall
* curl -X POST http://localhost:8080/internal/machines/create -H  "Content-Type: application/json"  -d "{\"name\":\"Machine 3\"}"


### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact