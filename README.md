# Spring boot With Coroutines

Simple spring boot app with a controller and a service that accesses another "microservice". 
The controller and service methods are suspend functions. The retrofit call is awaited on in 
a non-blocking manner.

Output using `retrofitClient.posts().awaitResponse()`:
```
2020-11-06 13:29:03.219  INFO 10960 --- [or-http-epoll-2] n.t.springbootcoroutines.Controller      : invoking service
2020-11-06 13:29:03.219  INFO 10960 --- [or-http-epoll-2] org.springframework.stereotype.Service   : executing json placeholder
2020-11-06 13:29:03.580  INFO 10960 --- [ypicode.com/...] org.springframework.stereotype.Service   : done executing json placeholder OkHttp https://jsonplaceholder.typicode.com/...
2020-11-06 13:29:03.582  INFO 10960 --- [ypicode.com/...] n.t.springbootcoroutines.Controller      : done invoking service
```

Output using `retrofitClient.postsSuspend()`:
```
2020-11-06 13:30:21.855  INFO 10960 --- [or-http-epoll-2] n.t.springbootcoroutines.Controller      : invoking service suspend
2020-11-06 13:30:21.855  INFO 10960 --- [or-http-epoll-2] org.springframework.stereotype.Service   : executing json placeholder suspend
2020-11-06 13:30:21.898  INFO 10960 --- [ypicode.com/...] org.springframework.stereotype.Service   : done executing json placeholder suspend OkHttp https://jsonplaceholder.typicode.com/...
2020-11-06 13:30:21.898  INFO 10960 --- [ypicode.com/...] n.t.springbootcoroutines.Controller      : done invoking service suspend
```

TODO: add grpc? 
TODO: reactive db