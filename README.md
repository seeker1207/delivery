- 프로젝트 아키텍처
  - 기본적으로 레이어드 아키텍처를 사용하여 구성하였고 도메인 중심의 구조를 구성하려고 하였습니다.
- repository  설정은 기본 JPA 를 통해 DB와 객체를 맵핑 하려고 했습니다.
- DB 설정
  - 기본적으로 mysql을 사용하여 프로젝트를 구성하였으며, 기본 데이터베이스 이름은 'delivery'이고 application.tml의 datasource 부분을 주석처리하면 기본 h2db를 사용하여 실행됩니다.

- API는 기본적으로 /swagger-ui.html로 이동하여 API 명세서를 볼수 있습니다.
- 로그인의 경우 controller 단이 아닌 spring security의 filter단에서 구현되어 swagger 페이지에 나와있지 않습니다. 
- 로그인 api 문서
  - https://documenter.getpostman.com/view/10994104/2s8ZDbX1nx
- jwt 인증
  - jwt 인증 방식은 spring security를 통해 구현하였으며 
  - 로그인(/login, post)을 하면 JWTLoginFilter를 통해 JWT 인증 과정을 거쳐서 Bearer 토큰을 반환합니다. 

- 테스트케이스
  - 테스트 케이스는 전부 구현하지는 못했으며,  주문날짜로 주문정보를 조회하는 repository 로직만 검증했습니다.
