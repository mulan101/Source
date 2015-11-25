* Maven packge 실행 후 생성 되는 jar 실행 파일 명은 lbass-WAS.jar 입니다.
개발 환경은 eclipse 이며 JDK 1.7 입니다.
eclipse의 Project Explore에서 오른 마우스를 클릭하여 Import > General > Exising Projects into Workspace 를 선택하고
Select archive file에서 해당 압축 파일을 선택하여 Finish 버튼을 클릭하시면 됩니다.

1. HTTP/1.1의 Host 헤더를 해석하세요. (구현 O)

	o 예를 들어, a.com과 b.com의 IP가 같을지라도 설정에 따라 서버에서 다른 데이터를 제공할 수 있어야 합니다.
		- web.json에 설정 
			VIRTUALHOST : 가상호스트 설정
				- HOSTNAME : 호출한 서버를 설정.
				- CLASSNAME : 호출한 서버에 맵핑 되는 Dispatcher 클래스를 설정함.
			일치하는 HOSTNAME이 없을 경우에는 default HOSTNAME을 사용합니다.
			모바일의 경우를 가상으로 설정하여 Dispatcher에서 호스트가 www.lbass.com 일 경우 Mobile Dispatcher를 호출하며  
			localhost 일 경우에는 기본 Dispatcher를 호출합니다.

	o 아파치 웹 서버의 VirtualHost 기능을 참고하세요.
	
2. 다음 사항을 설정 파일로 관리하세요. (구현 O)

	o 파일 포맷은 JSON으로 자유롭게 구성하세요.
		- web.json 파일에 모든 설정을 관리합니다. jar파일로 실행 할 시에는 외부 설정 파일인 web.json 파일을 jar 파일과 같은
			디렉토리에 배치 하여야 합니다. 그래야만 설정을 읽고 정상적으로 서버가 기동이 됩니다.
	o 몇 번 포트에서 동작하는지 
		- web.json PORT 설정을 사용하여 기동 시에 사용할 포트를 설정합니다. 
	o HTTP/1.1의 Host별로 
		HTTP_ROOT 디렉토리를 다르게 
			- web.json 파일의  HOST 속성을 사용하여 각각의 호스트를 구분합니다. HOTST의 속성은 다음과 같습니다.
				HOSTNAME : url 입력 시 최초 경로가 HOSTNAME이 되며 일치할 경우에 다른 속성들을 사용합니다.
				DOCBASE : HOSTNAME이 일치하는 url의 root directory를 지정합니다.
		403, 404, 500 에러일 때 출력할 HTML 파일 이름 
			- 위의 사항과 마찬가지로 web.json 파일의  HOST 속성을 사용하여 호스트 각각의 에러페이지를 가져옵니다.
				ERRORPAGE : ERRORPAGE : {"403" : "/error/err_403", "404" : "/error/err_404", "500" : "/error/err_500" }
				위와 같이 경로와 파일명을 설정하고 확장자는 시스템에서 자동으로 html로 지정합니다.

3. 403, 404, 500 에러를 처리합니다. (구현 O)

	o 해당 오류 발생 시 적절한 HTML을 반환합니다.
		- 위 2번의 설정을 사용하여 Exception 발생 시 각각의 케이스에 따라 해당 에러 페이지를 호출합니다.
	o 설정 파일에 적은 파일 이름을 이용합니다.
		- 설정 파일에서 로드하여 사용하도록 구현되었습니다. 단 일치하는 호스트가 없는 url의 경우 공통 404에러 페이지를 표시합니다. 
		예) 지정된 host (root / lbass) 외에 x.x.x/etc/test.html 등의 url 요청이 들어오는 경우에는
			미리 정의된 404에러 페이지가 표시됩니다.
			
4. 다음과 같은 보안 규칙을 둡니다. (구현 O)
	
	o 다음 규칙에 걸리면 응답 코드 403을 반환합니다.
	 HTTP_ROOT 디렉터리의 상위 디렉토리에 접근할 때, 예, http://localhost:8000/../../../../etc/passwd
	 확장자가 .exe 인 파일을 요청 받았을 때
		- 페이지를 찾기 전 요청한 path에 대한 필터 처리를 합니다. 필터 기능은 web.json에 정의 되어 각각의 호스트 별로 사용할 
			filter 클래스를 지정 할 수 있습니다.
	o 추후 규칙을 추가할 것을 고려해주세요.
		- web.json을 통해 지속적으로 추가가 가능하며 모든 필터는 com.lbass.server.filter.LbassFilter 인터페이스의
			구현체이어야 합니다. 구현하여햐하는 메소드는 다음과 같습니다.
			public void doFileter(HttpRequest request) throws FilterException;
			request 객체를 받아 개발자는 필요한 필터기능을 구현하면 됩니다.
			
5. logback 프레임워크 http://logback.qos.ch/를 이용하여 다음의 로깅 작업을 합니다.(구현 O)

	o 로그 파일을 하루 단위로 분리합니다.
		- logback.xml 파일을 사용하여 설정을 하여 데일리로 백업 파일을 만듭니다. 
	o 로그 내용에 따라 적절한 로그 레벨을 적용합니다.
		- url에 관련된 정보는 DEBUG 레벨로 설정하였고 예외 부분은 ERROR 레벨로 설정하였습니다.
			단 favicon.ico 요청의 경우에는 요청을 무시하도록 하고 TRACE로 설정하였습니다.(통 패키지로 설정하였습니다.)
	o 오류 발생 시, StackTrace 전체를 로그 파일에 남깁니다.
		- 예외 발생 시 간단한 메세지와 StackTrace를 남기도록 설정하였습니다.
		
6. 간단한 WAS를 구현합니다.(일부구현 O) - 추후 설정을 통한 맵핑 기능을 작성하다보니 두번째 과제는 미구현입니다.(기본 Name 맵핑 기능이 없습니다.)

	o 다음과 같은 SimpleServlet 구현체가 동작해야합니다.
		다음 코드에서 SimpleServlet, HttpRequet, HttpResponse 인터페이스나 객체는 여러분이 보다 구체적인 인터페이스나 구현체를
		제공해야합니다. 표준 자바 Servlet과는 무관합니다.
		- com.lbass.servlet.SimpleServlet의 구현체를 작성할 수 있도록 구현하였습니다.
	o URL을 SimpleServlet 구현체로 매핑합니다. 규칙은 다음과 같습니다.
	 http://localhost:8000/Hello --> Hello.java로 매핑
	 http://localhost:8000/service.Hello --> service 패키지의 Hello.java로 매핑
		
	o 과제는 URL을 바로 클래스 파일로 매핑하지만, 추후 설정 파일을 이용해서 매핑하는 것도 고려해서 개발하십시오.
	 추후 확장을 고려하면 됩니다. 설정 파일을 이용한 매핑을 구현할 필요는 없습니다.
	 설정 파일을 이용한 매핑에서 사용할 수 있는 설정의 예, {“/Greeting”: “Hello”, “/super.Greeting”: “service.Hello”}
		- web.json 파일의 다음 설정을 통해 맵핑이 가능합니다.
			SERVLETS : [ {URL : "/Greeting", MAPPINGCLASS : "com.lbass.servlet.impl.Hello" }
			 			, {URL : "/super.Greeting", MAPPINGCLASS : "service.Hello" }]
		  	URL :  host를 제외한 url을 작성합니다.(예 : /root/Greeting 일 경우 /Greeting)
		  	MAPPINGCLASS : 서블릿으로 처리할 클래스를 지정합니다. 
		  		이러한 클래스는 반드시 com.lbass.servlet.SimpleServlet의 구현체이어야 합니다.

7. 현재 시각을 출력하는 SimpleServlet 구현체를 작성하세요.(구현 O)

	o 앞서 구현한 WAS를 이용합니다.
		- web.json 파일의 설정을 확인합니다.
			 , SERVLETS:[{URL : "/Greeting", MAPPINGCLASS : "com.lbass.servlet.impl.Hello" }
			 			, {URL : "/HelloTime", MAPPINGCLASS : "custom.servlet.TimePrintServlet"}]
			custom.servlet.TimePrintServlet를 작성하고 설정을 추가합니다.
			/HelloTime Url을 확인하면 HelloWorld 텍스트와 현재 시간이 찍힌 페이지를 확인 할 수 있습니다.	
	o WAS와 SimpleServlet 인터페이스를 포함한 SimpleServlet 구현 객체가 하나의 JAR에 있어도 괜찮습니다.
	 분리하면 더 좋습니다 :)
		- custom.servlet.TimePrintServlet을 작성하였으며 패키지를 구분하여 하나의 JAR로 같이 묶었습니다.
			url은 /root/HelloTime 입니다.
		
8. 앞에서 구현한 여러 스펙을 검증하는 테스트 케이스를 JUnit4를 이용해서 작성하세요.(일부구현 O)
	
	- 요건 사항 중 일부만 테스트 케이스가 작성이 되었습니다.
