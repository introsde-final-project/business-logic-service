# Business Logic Service

Business Logic Service implements all the logics that are used in this application. It obtains data from Storage Service through JSON object in RESTful APIs, manipulates them to obtain meaningful response and pass it to the User Interface Service as a JSON object in RESTful API. The chief logic that is implemented in this service is to automatically recommend appropriate Goal, Activities, Food with respect to Blood Pressure and BMI of the user.

Further information on this service can be found in its [Wiki Page](https://github.com/introsde-final-project/business-logic-service/wiki).

[Click here to access this service in Heroku.](http://business-logic-service.herokuapp.com/business/user)
