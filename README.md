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

# 6-3 웹 브라우저에서 서블릿으로 데이터 전송하기

### GET/POST 전송방식

- 웹 브라우저에서 서블릿으로 전송하는 방법은 크게 GET/POST방식이 있습니다.
- 주소창에서 물음표(?) 두이에 입력된 값,외화 종루 그리고 요청을 나타내는 문자열이 서블릿 매핑 이름 뒤에 붙어서 전송된다. 이러한 방식이 GET방식인데 GET방식은 데이터를 전송할 경우에는 전송하는 데이터가 노출되므로 보안에 취약합니다.

``http://localhost:8090/calc?won=345000&operator=dollar&command=calculate``

- 반면에 POST방식은 전송하는 데이터를 숨겨서 전송하므로 보안성이 좋다.

즉,GET방식은 보안과 관련 없는 간단한 데이터를 쉽게 전송할 수 있는 반면,POST 방식은 보안과 관련된 데이터를 전송하는 데 많이 사용된다고 기억해 두면 됩니다. 

| GET 방식                                                     | POST 방식                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| - 서블릿에 데이터를 전송할 떄는 데이터가 URL뒤에<br>name=value형태로 전송됩니다.<br/>- 여러 개의 데이터를 전송할 때는 '&'로 구분해서 전송됩니다.<br/>- 보안이 취약하다<br/>- 전송할 수 있는 데이터는 최대 255자이다.<br/>- 기본 전송 방식이고 사용이 쉽다.<br/>- 웹 브라우저에 직접 입력해서 전송할 수도 있습니다<br/>- 서블릿에서는 doGet()으로 전송된 데이터를 처리합니다. | - 서블릿에 데이터를 전송할 때는 TCP/IP 프로토콜 데이터의 HEAD영역에 숨겨진 채 전송됩니다.<br/>- 보안에 유리합니다.<br/>- 전송 데이터 용량이 무제한 입니다.<br/>- 전송 시 서블릿에서는 또다시 가져오는 작업을 해야 하므로 처리 속도가 GET방식보다는 느리다.<br/>- 서블릿에서는 doPost()를 이용해 데이터를 처리합니다. |

------

### GET 방식으로 서블릿에 요청

- 로그인창 입력 예제에서 <form> 태그의 method 속성이 get으로 설정되어 있습니다. 이는 '서블릿에 GET 방식으로 데이터를 전송하겠다' 라는 의미 입니다.

```
<form name="frmLogin" method="get" action="login2" enctype="UTF-8">
    아이디 : <input type="text" name="user_id"><br>
    비밀번호 : <input type="password" name="user_pw"><br>
    <input type="submit" value="로그인"> <input type="reset" value="다시입력">
</form>
```

마찬가지로 서블릿에서도 GET 방식으로 전송된 데이터를 doGET()메서드를 이용해서 처리합니다.

------

### POST 방식으로 서블릿에 요청

![스크린샷 2019-07-05 오후 10.37.11](/Users/janghyeonjun/Desktop/스크린샷 2019-07-05 오후 10.37.11.png)

웹 브라우저에서 전송되는 데이터는 TCP/IP의 헤더에 숨겨진 채 전송되므로 브라우저의 주송창을 보면 URL뒤에는 아무것도 표시되지 않습니다.

---------

# 6.4 자바스크립트로 서블릿에  요청하기

웹 사이트에 로그인 할 때 ID나 비밀번호를 입력하지 않고 로그인 하면 오류 메시지가 출력된다.<form> 태그에서 바로 서블릿으로 데이터를 전송했지만 전송 전에 로그인하면 ID와 비밀번호 입력 유무 체크하기처럼 전송 데이터에 대해 유효성 검사를 하는 경우가 있다. 이런 기능은 자바스크립트로 구현하므로 이번에는 자바스크립트로 서블릿에 요청하는 방법을 배운다!

**pro05/Web/login2.html**

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript">
        function fn_validate() {
            var frmLogin = document.frmLogin;
            var user_id = frmLogin.user_id.value;
            var user_pw = frmLogin.user_pw.value;

            if((user_id.length == 0 || user_id == "") || (user_pw.length == 0 || user_pw == "")){
                alert("아이디와 비밀번호는 필수입니다.")
            }else {
                frmLogin.method = "post";
                frmLogin.action = "login5";
                frmLogin.submit();
            }
        }
    </script>
    <title>자바스크립트 구현(로그인 창)</title>
</head>
<body>
    <form name="frmLogin" method="post" action="login" enctype="UTF-8">
        아이디: <input type="text" name="user_id"><br>
        비밀번호:<input type="password" name="user_pw"><br>
        <input type="button" onclick="fn_validate()" value="로그인">
        <input type="reset" value="다시 입력">
        <input type="hidden" name="user_address" value="서울시 성북구"/>
    </form>
</body>
</html>
```

**pro05/sec03/ex03/LoginServlet5.java**

```java
package sec03.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_pw");
        String address = req.getParameter("user_address"); // login2.html에서 hidden값으로 전송된 값을 받아 옵니다.
        System.out.println("id : "+ id);
        System.out.println("password : "+pw);

        String data = "<html>";
        data+="<body>";
        data+="id : " +id;
        data+="<br>";
        data+="password : "+pw;
        data+="<br>";
        data+="address : "+address;
        data+="</body>";
        data+="</html>";
        out.print(data);
    }

    @Override
    public void destroy() {
        System.out.println("destroy method");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init method");
    }
}

```

# 7-1 서블릿의 비즈니스 로직 처리 방법

- 서블릿 비즈니스 처리 작업이란?

  - 서블릿이 클라이언트로부터 요청을 받으면 그 요청에 대해 작업을 수행하는 것을 의미 합니다.
  - 서블릿의 비즈니스 작업 예
    - 웹 사이트 회원 등록 요청 처리 작업
    - 웹 사이트 로그인 요청 처리 작업
    - 쇼핑몰 상품 주문 처리 작업

- 처리과정

  1.클라이언트로부터 요청을 받습니다.

  2.데이터베이스 연동과 같은 비즈니스 로직을 처리합니다.

  3.처리 결과를 클라이언트에게 돌려줍니다.

------

# 7-2 서블릿의 데이터베이스 연동하기

#### 7-2-1 서블릿으로 회원 정보 테이블의 회원 정보 조회

**1.웹 브라우저가 서블릿에게 회원 정보를 요청합니다.**

**2.MemberServlet은 요청을 받은 후 MemberDAO 객체를 생성하여 listMembers()메서드를 호출합니다.**

**3.listMembers()에서 다시  connDB()메서드를 호출하여 데이터베이스와 연결한 후 SQL문을 실행해 회원 정보를 조회합니다.**

**4.조회된 회원 정보를 MemberVO속성에 설정한 후 다시 ArrayList에 저장합니다.**

**5.ArrayList를 다시 메서드를 호출한 MemberServlet으로 반환한 후 ArrayList의 MeberVO를 차례대로 가져와 회원 정보를 HTML 태그의 문자열로 만듭니다.**

**6.만들어진 HTML 태그를 웹 브라우저로 전송해서 회원 정보를 출력합니다.**

------

# PrepareStatement를 이용한 회원 정보 실습

- Statement를 이용해서 데이터베이스와 연동할 경우에는 연동할 때 마다 DBMS에서 다시 SQL문을 컴파일해야 하므로 속도가 느리다는 단점이 있다.
- 이럴 경우 PrepareStatment 인터페이스를 사용하면 SQL문을 미리 컴파일해서 재사용하므로 Statement인터페이스보다 훨씬 빠르게 데이터베이스 작업을 수행할 수 있다.

#### PrepareStatement특징

1. PrepareStatement 인터페이스는 Statement 인터페이스를  상속하므로 지금까지 사용한 메서드를 그대로 사용합니다.
2. Statement 인터페이스가 DBMS에 전달하는 SQL문은 단순한 문자열이므로 DBMS는 이 문자열을 DBMS가 이해할 수 있도록 컴파일하고 실행한다. 반면에 PrepareStatement 인터페이스는 컴파일된 SQL문을 DBMS에 전달하여 성능을 향상시킵니다.
3. PreparedStatement 인터페이스에서는 실행하려는 SQL문에 '?'를 넣을 수 있습니다.따라서 '?'의 값만 바꾸어 손쉽게 설정할 수 있어 Statement보다 SQL문 작성하기가 더 간단하다.

------

# 7-3 DataSource 이용해 데이터베이스 연동하기

**필요할 때마다 미리 연결해 놓은 상태를 이용해 빠르게 데이터베이스와 연동하여 작업을 합니다. 이렇게 미리 데이터베이스와 연결시킨 상태를 유지하는 기술을 _커넥션풀_ 이라고 한다.**



### 기존 데이터베이스 연동 방법의 문제점

![스크린샷 2019-07-23 오후 8.36.24](/Users/janghyeonjun/Desktop/스크린샷 2019-07-23 오후 8.36.24.png)

------

# 7-3-1 커넥션풀 동작 과정

1.톰캣 컨테이너를 실행한 후 응용 프로그램을 실행합니다.

2.톰캣 컨테이너 실행 시 ConnectionPool 객체를 생성합니다.

3.생성된 커넥션 객체는 DBMS와 연결합니다.

4.데이터베이스와의 연동 작업이 필요한 경우 응용 프로그램은 ConnectionPool에서 제공하는 메서드를 호출하여 연동한다.

![스크린샷 2019-07-23 오후 8.44.55](/Users/janghyeonjun/Desktop/스크린샷 2019-07-23 오후 8.44.55.png)

------

# JNDI

- 실제 웹 애플리케이션에서 ConnectionPool  객체를 구현할 때는 Java SE에서 제공하는 javax.sql.DataSource클래스를 이용합니다. 그리고 웹 애플리케이션 실행 시 톰캣이 만들어 놓은 ConnectionPool 객체를 접근할 때는  JNDI를 이용한다.

### JNDI(java naming and directory interface)란 ?

- 필요한 자원을 키/값 쌍으로 저장한 후 필요할 때 키를 이용해 값을 얻는 방법입니다. 즉, 미리 접근할 자원에 키를 지정한 후 애플리케이션이 실행 중일 때 이 키를 이용해 자원에 접근해서 작업을 하는 것이다.

### JNDI사용예

- 웹 브라우저에서 name/value 쌍으로 전송한 후 서블릿에서 getParameter(name)로 값을 가져올 때
- 해시맵이나 해시테이블에 키/값으로 저장한 후 키를 이용해 값을 가져올 때
- 웹 브라우저에서 도메인 네임으로 DNS  서버에 요청할 경우 도메인 네임에 대한 IP주소를 가져올 때

**톰캣 컨테이너가 ConnectionPool객체를 생성하면 이 객체에 대한 JNDI이름(key)을 미리 설정해 놓는다. 그러면 웹 애플리케이션에서 데이터베이스와 연동 작업을 할 때 이 JNDI이름(key)으로 접근하여 작업한다.**

------

# 7-3-3 톰캣의 DataSource설정 및 사용 방법

1.JDBC  드라이버를 /WEB-INF/lib폴더에 설치합니다.

2.ConncetionPool 기능 관련 jar 파일을 /WEB-INF/lib폴더에 설치합니다.

3.CATALINA_HOME/context.xml에 Connection 객체 생성 시 연결 할 데이터베이스 정보를 JNDI로 설정한다.

4.DAO클래스에서 데이터베이스와 연동 시 미리 설정한 JNDI라는 이름으로 데이터베이스와 연결해서 작업합니다.
---------
# 8.1 서블릿 포워드 기능 사용하기

#### 포워드,바인딩,애너테이션 설명

_**포워드**_

예를 들어 쇼핑몰의 경우 상품 관리 서블릿과 조회된 상품을 화면에 표시하는 JSP는 각각 따로 조재합니다. 따라서 사용자가 상품 조회를 요청하면 상품 관리 서블릿은 데이터베이스에서 상품 정보를 조회한 후 다시 JSP에게 전달하여 상품 정보를 표시합니다.

이처럼 하나의 서블릿에서 다른 서블릿이나 JSP와 연동하는 방법을 포워드(forward)라고 합니다. 포워드 기능이 사용되는 용도는 여러 가지이다.

- 요청에 대한 추가 작업을 다른 서블릿에게 수행하게 합니다.
- 요청(request)에 포함된 정보를 다른 서블릿이나 JSP와 공유할 수 있습니다.
- 요청(request)에 정보를 포함시켜 다른 서블릿에 전달할 수 있습니다.
- 모델2 개발 시 서블릿에서 JSP로 데이터를 전달하는 데 사용됩니다.

**한마디로 포워드 기능은 서블릿에서 다른 서블릿이나 JSP로 요청을 전달하는 역할을 합니다.**

**그리고 이 요청(request)을 전달할 때 추가 데이터를 포함시켜서 전달할 수도 있습니다. 모델2 개발 방식으로 웹 애플리케이션을 개발할 경우 서블릿에서 JSP로 데이터를 전달할 때 주로 사용됩니다.**

------

# 8.2서블릿의 여러 가지 포워드 방법

1. redirect 방법

   - HttpServletResponse 객체의 sendRedirect()메서드를 이용합니다.
   - 웹 브라우저에 재요청하는 방식입니다.
   - **형식: sendRedirect("포워드할 서블릿 또는 JSP");**

2. Refresh방법

   - HttpServletResponse 객체의 addHeader()메서드를 이용합니다.
   - 웹 브라우저에 재요청하는 방식입니다.
   - **형식: response.addHeader("Refresh","경과시간(초);url=요청할 서블릿 또는 JSP");**

3. location방법

   - 자바스크립트 location객체의 href 속성을 이용합니다.
   - 자바스크립트에서 재용청하는 방식입니다.
   - **형식:location.href='요청할 서블릿 또는 JSP';**

4. dispatch방법

   - 일반적인 포워딩 기능을 지칭합니다.

   - 서블릿이 직접 요청하는 방법입니다.

   - RequestDispatcher 클래스의 forward()메서드를 이용합니다.

   - **형식:RequestDispatcher dis= request.getRequestDispatcher("포워드할 서블릿 또는 JSP");**

     **dis.forward(request,response);**

Redirect,refresh,location 방법은 서블릿이 웹 브라우저를 거쳐 다른 서블릿이나 JSP에게 요청하는 방법이다. 반면에 dispatch 방법은 서블릿에서 클라이언트를 거치지 않고 바로 다른 서블릿에게 요청하는 방법이다. 



#### 8.2.1 redirect를 이용한 포워딩

- 이 방법은 서블릿의 요청이 클라이언트의 웹 브라우저를 다시 거쳐 요청되는 방식이다.

1. 클라이언트 -> FirstServlet :클라이너트의 웹 브라우저에서 첫 번째 서블릿에 요청.
2. FirstServlet->클라이언트 :첫 번째 서블릿은 sendRedirect() 메서드를 이용해 두 번째 서블릿을 웹 브라우저를 통해서 요청
3. 클라이언트->SecondServlet :웹 브라우저 sendRedirect() 메서드가 지정한 두 번째 서블릿을 다시 요청합니다.



#### 8.2.3 refresh를 이용한 포워딩

- refresh를 이용한 포워딩 역시 redirect처럼 웹 브라우저를 거쳐서 요청을 수행합니다.

1. 클라이언트의 웹 브라우저에서 첫 번째 서블릿에 요청합니다.
2. 첫 번째 서블릿은 addHeader()메서드를 이용해 두 번째 서블릿을 웹 브라우저를 통해서 요청합니다.
3. 웹 브라우저 addHeader()메서드가 지정한 두 번째 서블릿을 다시 요청합니다.


#### 8.2.5 location을 이용한 포워딩

생략

------

# 8.3 dispatch 를 이용한 포워드 방법

#### 8.3.1 dispatch를 이용한 포워딩 과정

dispatch를 이용한 포워딩 과정이 redirect방법과 다른 점은 클라이언트의 웹 브라우저를 거치지 않고 바로 서버에서 포워딩이 진행된다는 것입니다. 따라서 웹 브라우저 주소창의 URL이 변경되지 않는다. 즉 , 클라이언트 측에서는 포워드가 진행되었는지 알 수 없다.

1. 클라이언트의 웹 브라우저에서 첫 번째 서블릿에 요청합니다.
2. 첫 번째 서블릿은 RequestDispatcher를 이용해 두 번째 서블릿으로 포워드 합니다.
-----------
# 8.4바인딩

**서블릿에서 다른 서블릿으로 포워딩할 때 GET방식으로 데이터를 전달하는 방법을 알아봤다. 전달하는 데이터 양이 적을 때는 이 방법이 편리하다. 그러나 서블릿에서 조회한 대량의 상품 정보를 JSP로 전달할 때는 GET방식이 불편하다. 따라서 서블릿에서 다른 서블릿 또 JSP로 대량의 데이터를 공유하거나 전달하고 싶을 때는 바인딩(binding) 기능을 사용한다.**

#### 8.4.3 두 서블릿 간 회원 정보 조회 바인딩 실습

이번에는 DB에서 조회된 회원 정보를 화면 기능을 담당하는 서블릿에 전달해서 웹 브라우저에 출력해 보겠다.

MemberDAO.java , MemberVO.java는 7장에서 실습한걸로 대체!!!

- MemberServlet.java
- MemberServlet.java 클래스를 다음과 같이 작성합니다.첫 번째 서블릿에서 조회한 회원정보를 List에 저장한 후 다시 바인딩하여 두 번째 서블릿으로 전달한다.

```java
package sec10.ex01;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }
    private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        MemberDAO dao = new MemberDAO();

        List memberList = dao.listmembers();
        req.setAttribute("membersList",memberList);//조회된 회원 정보를 ArrayList 객체에 저장한 후 request에 바인딩합니다.
        /*
         * 바인딩한 request를 viewMembers 서블릿으로 포워딩합니다.
         */
        RequestDispatcher dispatch = req.getRequestDispatcher("viewMembers");
        dispatch.forward(req,resp);


    }
}

```

- ViewServlet.java 
- ViewServlet 클래스를 다음과 같이 작성한다. getAttribute() 메서드를 이용해 첫 번째 서블릿에서 바인딩한 회원 정보를  List로 가져옵니다.

```java
package sec10.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        List membersList = (List)req.getAttribute("membersList");//MemberServlet.java에서 바인딩해서 넝어온 request에서 회원정보를 가져옵니다.
        out.print("<html><body>");
        out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
        out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td>");
        for(int i= 0 ;i<membersList.size();i++){
            MemberVO memberVO = (MemberVO)membersList.get(i);
            String id = memberVO.getId();
            String passwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();
            Date joinDate = memberVO.getJoinDate();
            out.print("<tr><td>"+id+"</td><td>"+passwd+"</td><td>"+name+"</td><td>"+email+"</td><td>"+joinDate+"</td><td>"
            +"<a href='member3?command=delMember&id"+id+"'>삭제</a></td></tr>");
        }
        out.print("</table></body></html>");
        out.print("<a href='/memberForm.html'>새 회원 등록하기</a>");
    }
}

```



-------
# 8.5 ServletContext와 ServletConfig 사용법

#### 8.5.1 ServletContext클래스

- ServletContext 클래스는 톰캣 컨테이너 실해시 각 컨텍스트(웹 애플리케이션)마다 한 개의 ServletContext객체를 생성합니다.그리고 톰캣 컨테이너가 종료하면 ServletContext 객체 역시 소멸됩니다.ServletContext 객체는 웹애플리케이션이 실해되면서 애플리케이션 전체의 공통 자원이나 정보를 미리 바인딩해서 서블릿들이 공유하여 사용한다.
- ServletContext 클래스의 특징은 다음과 같다
  - Javax.servlet.ServletContext로 정의되어 있다.
  - 서블릿과 컨테이너 간의 연동을 위해 사용합니다.
  - 컨텍스트(웹 애플리케이션)마다 하나의 ServletContext가 생성됨
  - 서블릿끼리 자원(데이터)을 공유하는 데 사용한다.
  - 컨테이너 실행 시 생성되고 컨테이너 종료 시 소멸된다.
- ServletContext가 제공하는 기능
  - 서블릿에서 파일 접근 기능
  - 자원 바인딩 기능
  - 로그 파일 기능
  - 컨텍스트에서 제공하는 설정 정보 제공 기능

**아래 그림은 톰캣 컨테이너를 실행할 때 각 애플리케이션에서 생성되는 ServletContext와 ServletConfig 객체를 나타내었습니다. ServletContext는 컨텍스트당 생성되는 반면에 ServletConfig는 각 서블릿에 대해 생성된다.**

![스크린샷 2019-08-28 오후 7.23.07](/Users/janghyeonjun/Desktop/스크린샷 2019-08-28 오후 7.23.07.png)


ServletContext에서 제공하는 여러 가지 메서드

| 메서드                                      | 기능                                                         |
| ------------------------------------------- | ------------------------------------------------------------ |
| getAttribute(String name)                   | - 주어진 name을 이용해 바인딩된 value를 가져옵니다.<br>- name이 존재하지 않으면 null을 반환한다. |
| getAttributeNames()                         | - 바인딩된 속성들의 name을 반환합니다.                       |
| getContext(String uripath)                  | 지정한 uripath에 해당되는 객체를 반환합니다.                 |
| getInitParameter(String name)               | - name에 해당되는 매개변수의 초기화 값을 반환한다.<br>- name에 해당되는 매개변수가 존재하지 않으면 null을 반환한다. |
| getInitParameterNames()                     | - 컨텍스트의 초기화 관련 매개변수들의 이름들을 String 객체가 저장된 Enumeration 타입으로 반환한다.<br>- 매개변수가 존재하지 않으면 null을 반환한다. |
| getMajorVersion()                           | - 서블릿 컨테이너가 지원하는 주요 서블릿 API버전을 반환한다. |
| getRealPath(String path)                    | - 지정한 path에 해당되는 실제 경로를 반환한다.               |
| getResource(String path)                    | - 지정한 path에 해당되는 Resource를 반환한다.                |
| getServletContextName()                     | - 현재 서블릿이 실행되고 있는 서블릿 컨테이너의 이름과 버전을 반환한다. |
| getServletContextName()                     | - 해당 애플리케이션의 배치관리자가 지정한 ServletContext에 대한 해당 웹 애플리케이션의 이름을 반환한다. |
| log(String msg)                             | - 로그 파일에 로그를 기록한다.                               |
| removeAttribute(String name)                | - 해당 name으로 ServletContext에 바인딩된 객체를 제거한다.   |
| setAttribute(String name,Object object)     | - 해당 name으로 객체를 ServletContext에 바인딩한다.          |
| setInitParameter(String name, String value) | - 주어진 name으로 value를 컨텍스트 초기화 매개변수로 설정한다. |



#### 8.5.2 ServletContext 바인딩 기능

1. GetServletContext, SetServletContext 클래스 파일을 준비한다.

2. getServletContext() 메서드를 이용해 ServletContext 객체에 접근한 다음 ArrayList에 이름과 나이를 저장한 후 다시 ServletContext 객체에  setAttribute() 메서드를 이용해 바인딩 한다.

   SetServletContext.java

   ```java
   @WebServlet("/cset")
   public class SetServletContext extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           resp.setContentType("text/html;charset=utf-8");
           PrintWriter out = resp.getWriter();
           ServletContext context = getServletContext(); // ServletContext 객체를 가져온다.
           List member = new ArrayList();
           member.add("이순신");
           member.add(30);
           context.setAttribute("member",member);//ServletContext 객체에 데이터를 바인딩 한다.
           out.print("<html><body>");
           out.print("이순신과 30 설정");
           out.print("</body></html>");
       }
   }
   ```

   

  3.GetServletContext 클래스를 작성한다. getServletContext()메서드를 이용해 ServletContext 객체에 접근한다. 그리고 getAttribute() 메서드를 이요해 다른 서블릿에서 바인딩한 ArrayList를 가져와 회원 정보를 출력한다.

```java
@WebServlet("/cget")
public class GetServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ServletContext context = getServletContext();//ServletContext객체를 가져온다.
        List member = (ArrayList)context.getAttribute("member");
        String name = (String)member.get(0);
        int age = (Integer) member.get(1); //아마 자동 언박싱 되서 age변수에 들어갈듯??

        out.print("<html><body>");
        out.print("name:"+name+", age:"+age);
        out.print("</body></html>");
    }
}
```


#### 8.5.3 ServletContext의 매개변수 설정 기능

대부분의 웹 애플리케이션에서 메뉴는 공통으로 사용하는 기능이다. 따라서 web.xml에 설정해 놓고 프로그램 시작 시 초기화 할 때 가져와서 사용하면 편리하다.그러면 새로운 메뉴 항목이 생성되거나 기존 메뉴 항목을 추가, 삭제할 때도 쉽게 수정할 수 있다.

1. ContextParamServlet 클래스 파일과 web.xml파일을 준비한다.

2. Web.xml에 메뉴 항목을 설정. <context-param> 태그 안에 <param-name>태그와 <param-value>태그를 이용해 메뉴에 대한 하위 메뉴 항목을 설정한다.

   ```
   
   ```

   ```java
   <context-param>
       <param-name>menu_member</param-name>
       <param-value>회원등록 회원조회 회원수정</param-value>
   </context-param>
   
   <context-param>
       <param-name>menu_order</param-name>
       <param-value>주문조회 주문등록 주문수정 주문취소</param-value>
   </context-param>
   
   <context-param>
       <param-name>menu_goods</param-name>
       <param-value>상품조회 상품등록 상품수정 상품삭제</param-value>
   </context-param>
   ```

3. ContextParamServlet클래스를 다음과 같이 작성한다. getServletContext()메서드로 ServletContext 객체에 접근한다. 그리고 getInitParameter() 메서드의 인자로 각각의 메뉴 이름을 전달한 후 메뉴 항목들을 가져와 이를 브라우저로 출력한다.

```java
package sec11.ex02;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/initMenu")
public class ContextParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ServletContext context = getServletContext(); // ServletContext객체를 가져온다

        /*
         * web.xml의 <param-name>태그의 이름으로 <param-value> 태그의 값인 메뉴 이름들을 받아온다.
         */
        String menu_member = context.getInitParameter("menu_member");
        String menu_order = context.getInitParameter("menu_order");
        String menu_goods = context.getInitParameter("menu_goods");

        out.print("<html><body>");
        out.print("<table border=1 cellspacing=0><tr>메뉴이름</tr>");
        out.print("<tr><td>"+menu_member+"</td></tr>");
        out.print("<tr><td>"+menu_order+"</td></tr>");
        out.print("<tr><td>"+menu_goods+"</td></tr>");
        out.print("</table></body></html>");

    }
}

```

#### 8.5.5 ServletConfig

**ServletConfig는 각 Servlet 객체에 대해 생성된다. 서블릿 API 계층 구조를 보면 ServletConfig 인터페이스를 GenericServlet 클래스가 실제로 구현 하고 있다.**

**ServletConfig에서 제공하는 여러 가지 메서드를 이용해 서블릿에 관련된 기능을 사용할 수 있다. 대표적인 기능이 앞에서 실습한 ServletContext 객체를 가져오는 기능이다.**

**ServletConfig는 javax.servlet 패키지에 인터페이스로 선언되어 있으며, 서블릿에 대한 여러가지 기능을 제공한다. 각 서블릿에서만 접근할 수 있으며 공유는 불가능 합니다. ServletConfig는 서블릿과 동일하게 생성되고 서블릿이 소멸되면 같이 소멸된다.**

ServletConfig가 제공하는 기능은 다음과 같다.

- ServletContext 객체를 얻는 기능
- 서블릿에 대한 초기화 작업 기능

이번에는 서블릿에서 사용할 설정 정보를 익어 들여와 초기화하는 기능을 알아보겠다. 서블릿에서 초기화하는 방법으로는 @WebServlet 애너테이션을 이용하는 방법과 web.xml에 설정하는 방법이 있다.

#### 8.5.6 애너테이션을 이용한 서블릿 설정

| 요소          | 설명                                                     |
| ------------- | -------------------------------------------------------- |
| urlPatterns   | 웹 브라우저에서 서블릿 요청 시 사용하는 매핑 이름        |
| name          | 서블릿 이름                                              |
| loadOnStartup | 컨테이너 실행시 서블릿이 로드되는 순서 지정              |
| initParams    | @WebInitParam 애너테이션 이용해 매개변수를 추가하는 기능 |
| description   | 서블릿에 대한 설명                                       |


--------
#### 8.6 load-on-startup

**서블릿은 브라우저에서 최초 요청 시 init()메서드를 실행한 후 메모리에 로드되어 기능을 수행합니다.따라서 최초 요청에 대해서는 실행 시간이 길어질 수밖에 없다. 이런 단점을 보완하기 위해 이용하는 기능이 load-on-startup이다.**

- 톰캣 컨테이너가 실행되면서 미리 서블릿을 실행한다.
- 지정한 숫자가 0보다 크면 톰캣 컨테이너가 실행되면서 서블릿이 초기화 된다.
- 지정한 숫자는 우선순위를 의미하며 작은 숫자부터 먼저 초기화된다.

#### 8.6.1 애너테이션을 이용하는 방법

```java
 package sec12.ex02;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loadConfig", urlPatterns = {"/loadConfig"},loadOnStartup = 1)//loadOnStartup 속성을 추가하고 우선수위를 1로 설정한다.
public class loadConfig extends HttpServlet {
    private ServletContext context;//멤버변수로 선언한다.

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("LoadAppConfig");
        context = config.getServletContext();//init() 메서드에서 ServletContext 객체를 얻는다.
        String menu_member = context.getInitParameter("menu_member");
        String menu_order = context.getInitParameter("menu_order");
        String menu_goods = context.getInitParameter("menu_goods");

        context.setAttribute("menu_member",menu_member);
        context.setAttribute("menu_order",menu_order);
        context.setAttribute("menu_goods",menu_goods);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String menu_member = (String)context.getAttribute("menu_member");
        String menu_order = (String)context.getAttribute("menu_order");
        String menu_goods = (String)context.getAttribute("menu_goods");

        out.print("<html><body>");
        out.print("<table border=1 cellspacing=0><tr>메뉴 이름</tr>");
        out.print("<tr><td>"+menu_member+"</td></tr>");
        out.print("<tr><td>"+menu_order+"</td></tr>");
        out.print("<tr><td>"+menu_goods+"</td></tr>");
        out.print("</table></body></html>");

    }
}

```

위에 코드에서 우선순위는 양의 정수로 지정하며 숫자가 작으면 우선순위가 높으므로 먼저 실행한다.

톰캣 실행 시 init() 메서드를 호출하면 getInitParameter() 메서드를 이용해 web.xml의 메뉴 정보를 읽어 들인 후 다시 ServletContext 객체에 setAttribute() 메서드로 바인딩한다. 브라우저에서 요청하면 web.xml이 아니라 ServletContext 객체에서 메뉴 항목을 가져온 후 출력하기 때문에 파일에서 읽어 들여와 출력하는 것보다 빨리 출력할 수 있다.


