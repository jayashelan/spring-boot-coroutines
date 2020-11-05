# Spring boot With Coroutines

Simple spring boot app with a controller and a service that accesses another "microservice". 
The controller and service methods are suspend functions. The retrofit call is awaited on in 
a non-blocking manner.

TODO: add grpc? 
TODO: reactive db