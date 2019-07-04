# Java-servlet-basic
# 5장 서블릿 이해하기

### 서블릿이란?

- 서버 쪽에서 실행되면서 클라이언트의 요청에 따라 동적으로 서비스를 제공하는 자바 클래스이다. 서블릿은 자바로 작성되어 있으므로 자바의 일반적인 특징을 모두 가집니다.하지만 서블릿은 일반 자바 프로그램과 다르게 독자적으로 실행되지 못하고 톰캣과 같은 JSP/Servelt컨테이너에서 실행된다는 점에서 차이가 있다.

![](/Users/janghyeonjun/Desktop/스크린샷 2019-06-25 오후 7.44.51.png)

- 특징
  - 서버 쪽에서 실행되면서 기능을 수행합니다.
  - 기존의 정적인 웹 프로그램의 문제점을 보완하여 동적인 여러가지 기능을 제공합니다.
  - 스레드 방식으로 실행됩니다.
  - 자바로 만들어져 자바의 특징을 가집니다.
  - 컨테이너에서 실행됩니다.
  - 컨테이너 종류에 상관없이 실행됩니다.(플랫폼 독립적).
  - 보안 기능을 적용하기 쉽습니다.
  - 웹 브라우저에서 요청 시기능을 수행합니다.

------

### 서블릿API 계층 구조와 기능

![스크린샷 2019-06-25 오후 7.53.05](/Users/janghyeonjun/Desktop/스크린샷 2019-06-25 오후 7.53.05.png)

- 서블릿 API는 Servlet과 ServletConfig 인터페이스를 구현해 제공하며 GenericServlet 추상 클래스가 이 두 인터페이스의 추상 메서드를 구현합니다. 그리고 이 GenericServlet을 다시 HttpServlet이 상속받습니다.
- HttpServlet은 이름에서 알 수 있듯이 HTTP 프로토콜을 사용하는 서블릿 기능을 구현하는 클래스이다.

------

### 서블릿의 생명주기 메서드

- 서블릿 생명주기 메서드: 서브릿 실행 단계마다 호출되어 기능을 수행하는 콜백 메서드를 말한다.

| 생명주기 단계 | 호출 메서드        | 특징                                                         |
| ------------- | ------------------ | ------------------------------------------------------------ |
| 초기화        | Init()             | - 서블릿 요청 시 맨 처음 한 번만 호출됩니다. <br>- 서블릿 생성 시 초기화 작업을 주로 수행합니다. |
| 작업 수행     | doGet() , doPost() | - 서블릿 요청 시 매번 호출됩니다.<br>- 실제로 클라이언트가 요청하는 작업을 수행합니다. |
| 종료          | Destroy()          | - 서블릿이 기능을 수행하고 메모리에서 소멸될 때 호출됩니다.<br>- 서블릿의 마무리 작업을 주로 수행합니다. |

-------------
# 6장 서블릿 기초

#### 서블릿 기본 기능 수행과정

1. 클라이언트로부터 요청을 받습니다.
2. 데이터베이스 연동과 같은 비즈니스 로직을 처리합니다.
3. 처리된 결과를 클라이언트에 돌려줍니다.

------

#### 서블릿 응답과 요청 수행 API 기능

**요청이나 응답과 관련된 API는 모두 javax.servlet.http 패키지에 있다**

- 요청과 관련된 API : javax.servelt.http.HttpServletRequest 클래스
- 응답과 관련된 API : javax.servlet.http.HttpServletResponse 클래스



클라이언트가 서블릿에 요청을 하면 먼저 톰캣 컨테이너가 받는다. 그런 다음 사용자의 요청이나 응답에 대한 HttpServletRequest 객체와 HttpServletResponse객체를 만들고 서블릿의 doGet()이나 doPost()메서드를 호출하면서 이 객체들을 전달한다.

------

form 태그 이용해 서블릿에 요청하기

- 서로 연동하여 동작합니다. 특히 사용자의 요청은 HTML의 form태그나 자바스크립트로부터 전송 받아서 처리합니다.

<form name "frmLogin" method="get" action="login" enType="UTR-8">
  아이디 : <input type="text" name="user_id"><br>
  비밀번호 : <input type="password" name="user_pw"><br>
  <input type="submit" value="로그인">
  <input type="reset" value="다시 입력">
</form>

form name "frmLogin" method="get" action="login" enType="UTR-8"
  아이디 : <input type="text" name="user_id"><br>
  비밀번호 : <input type="password" name="user_pw"><br>
  <input type="submit" value="로그인">
  <input type="reset" value="다시 입력">
form

- 로그인창에서 로그인을 클릭했을 때 실제로 데이터가 전송되는 과정입니다.

| name    | value |
| ------- | ----- |
| User_id | lee   |
| User_pw | 1234  |

<input> 태그의 name속성긔 값과 실제 입력한 데이터가 쌍으로 전송됩니다.

| 속성   | 기능                                                         |
| ------ | ------------------------------------------------------------ |
| name   | - <form>태그의 이름을 지정합니다.<br>- 여러 개의 form이 존재할 경우 구분하는 역할을 합니다.<br>- 자바스크립트에서 <form>태그에 접근할 때 자주 사용합니다. |
| method | - <form>태그 안에서 데이터를 전송할 때 전송 방법을 지정합니다.<br>- GET or POST로 지정합니다.(아무것도 지정하지 않으면 GET입니다.) |
| action | - <form>태그에서 데이터를 전송할 서블릿이나 JSP를 지정합니다.<br/>- 서블릿으로 전송할 때는 매핑이름을 사용합니다. |
| enType | - <form> 태그에서 전송할 데이터의 encoding 타입을 지정합니다.<br/>- 파일을 업로드 할 때는 multipart/form-data로 지정합니다. |

------

#### 서블릿에서 클라이언트의 요청을 얻는 방법

- HttpServletRequest 클래스에서 <form> 태그로 전송된 데이터를 받아오는 데 사용하는 메서드로는 아래와 같다. 이 중에서 가장 많이 사용되는 것이 getParameter()메서드이고, 만약 같은 name으로 여러 개의 값이 전송되었을 때는 배열 형태로 값을  반환하는 getParameterValues()메서드를 이용한다.

| 메서드                                   | 기능                                                         |
| ---------------------------------------- | ------------------------------------------------------------ |
| String getParameter(String name)         | name의 값을 알고 있을 때 그리고 name에 대한 전송된 값을 받아오는 데 사용한다. |
| String[] getParameterValues(String name) | 같은 name에 대한 여러 개의 값을 얻을 때 사용합니다.          |
| Enumeration getParameterNames()          | name값을 모를 때 사용합니다.                                 |


# 6-2 서블릿의 응답 처리 방법

### 서블릿에서 응답을 처리하는 방법

- doGet()이나 doPost() 메서드 안에서 처리합니다
- javax.servlet.http.HttpServletResponse 객체를 이용합니다
- setContentType()을 이용해 클라이언트에게 전송할 데이터 종류(MIME-TYPE)를 지정합니다.
- 클라이언트(웹 브라우저)와 서블릿의 통신은 자바I/O의 스트립을 이용합니다.

**서블릿의 응답 처리는 doGet()이나 doPost() 메서드의 두 번째 매개변수인 HttpServletResponse객체를 이용하여 처리합니다.그리고 웹 브라우저와 서블릿의 응답 과정은 자바I/O의 기능인 스트림을 이용하여 이루어집니다.**

------

### MIME-TYPE

우리가 배우는 웹 애플리케이션은 클라이언트에 해당하는 웹 브라우저와 서버에 해당하는 서블릿이 서로 데이터를 주고받으면서 실행합니다. 웹 브라우저가 네트워크를 통해 서블릿에 데이터를 보내는 경우 서블릿은 네트워크로부터 데이터를 입력 받습니다. 반대로 서블릿이 웹 브라우저로 데이터를 전송하는 경우에는 네트워크로 데이터를 출력합니다.  

서버(서블릿)에서 웹 브라우저로 데이터를 전송할 때는 어떤 종류의 데이터를 전송하는지 웹 브라우저에 알려줘야 합니다. 그 이유는 웹 브라우저가 전송 받을 데이터의 종류를 미리 알고 있으면 더빠르게 처리할 수 있기 때문이다. 따라서 서버(서블릿)에서 웹 브라우저로 데이터를 전송할 때 는 톰캣 컨테이너에서 미리 제공하는 여러가지 전송 데이터 종류 중 하나를 지정해서 웹 브라우저로 전송합니다. 이처럼 톰캣 컨테이너에서 미리 설정해 놓은 데이터 종류들을 MIME-TYPE이라고 합니다.

#### MIME-TYPE으로 지정하는 예

- HTML로 전송 시 : text/html
- 일반 텍스트로 전송 시 : text/plain
- XML 데이터로 전송 시 :  application/xml

웹 브라우저는 기본적으로 HTML만 인식하므로 서블릿에서 전송하는 대부분의 데이터는 MIME-TYPE을 text/html로 지정한다.

##### 서블릿이 클라이언트(웹 브라우저)에 응답하는 과정은 다음과 같습니다

1.setContentType()을 이용하여 MIME-TYPE을 지정합니다.

2.데이터를 출력할 PrintWriter 객체를 생성합니다.

3.출력 데이터를 HTML형식으로 만듭니다.

4.PrintWriter의 print()나 println()을 이용해 데이터를 출력합니다.

**아래 예시**

```
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8"); //웹 브라우저에서 전송된 데이터의 인코딩을 설정합니다.
    resp.setContentType("text/html;charset=utf-8");  //1번 setContentType()을 이용해 응답할 데이터 종류가 HTML임을 설정한다.
    PrintWriter out = resp.getWriter(); //2번 HttpServletResponse 객체의 getWriter()를 이용해 출력 스트림 PrintWriter 객체를 받아 옵니다.
    String id = req.getParameter("user_id");
    String pw = req.getParameter("user_pw");

    String data = "<html>"; //3번
    data += "<body>";
    data += "아이디 : " + id;
    data += "<br>";
    data += "패스워드 :"+ pw;
    data += "</body>";
    data += "</html>";
    out.print(data); //4번
}
```
