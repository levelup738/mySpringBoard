## LoginInterceptor

![loginInterceptor1](https://user-images.githubusercontent.com/47135267/97112981-b6ed4380-172a-11eb-9aca-f1dc4fcfeaa9.PNG)

servlet.xml에 설정

![loginInterceptor2](https://user-images.githubusercontent.com/47135267/100746435-0b6b9900-3424-11eb-9d0b-6254d5a360f3.PNG)

HandlerInterceptor를 상속받아서 사용

## HandlerInterceptor ?

내가 설정한 특정 URI 호출을 가로채는 역할

컨트롤러에 들어오기 전에 필터링이 가능하다.

### HandlerInterceptor 메소드

#### ▶ preHandle(request, response, handler)

지정된 컨트롤러의 동작 이전에 수행할 동작 (사전 제어).

#### ▶ postHandle(request, response, handler, modelAndView)

지정된 컨트롤러의 동작 이후에 처리할 동작 (사후 제어).
Spring MVC의 Dispatcher Servlet이 화면을 처리하기 전에 동작.

#### ▶ afterCompletion(request, reponse, handler, exception)

Dispatcher Servlet의 화면 처리가 완료된 이후 처리할 동작.



