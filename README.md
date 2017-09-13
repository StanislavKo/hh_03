# hh_03

The project contains JAX-RS endpoints, Spring integration (Service for JSON manipulation). 

No WAR creation is used because you pointed you would like to test the project by executing main java application, not regular application server. So embedded server grizzle2 is used. Main class is com.cv.jasonp01.main.RunJasonP01.

There is no metadata JSON file because imho there is no functionality where it can be used.

Account has AccountBuilder implemented. ObjectMapper is used for JSON processing. AccountService contains few Java 8 lambdas, Optional, stream.

Tests for RESTful endpoints and service are included.

