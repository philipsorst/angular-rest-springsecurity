angular-rest-springsecurity with Restangular
============================================

This is a fork from https://github.com/philipsorst/angular-rest-springsecurity. I needed this exact functionality but with Restangular
For this fork to work you need to download Restangular and underscore.

An example AngularJS Application that uses a Spring Security protected Jersey REST backend based on Hibernate/JPA.

About
-----

The projects aim is to demonstrate the Java implementation of a simple REST interface which is used by an AngularJS application. The following topics are covered:

* A relational database that holds news entries and users.
* A REST service that exposes the data in the database.
* Authentication and authorization against the REST service.
* A Simple AngularJS application that allows users to view or edit news entries depending on their role.
* A responsive design.
 
This project is just meant to be a demonstration, therefore it is neither well documented nor well tested. Use it to learn about the technologies used, but do not use it for productive applications.

Any feedback is welcome, and I will incorporate useful pull requests.

Technologies
------------

* [AngularJS](http://angularjs.org/) 
* [Restangular](https://github.com/mgonto/restangular)
* [Bootstrap](http://getbootstrap.com/)
* [Jersey](https://jersey.java.net/)
* [Spring Security](http://projects.spring.io/spring-security/)
* [Hibernate](http://hibernate.org/)

Running
-------

Make sure [Maven](http://maven.apache.org/) >= 2.2.1 is installed on your system. Go into the project dir and type `mvn jetty:run`, then point your browser to `http://localhost:8080`.

License
-------

[The Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)