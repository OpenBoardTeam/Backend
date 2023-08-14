# GitBoard Project
OSS 공모전을 위한 GitBoard project의 백앤드 레포지토리 입니다. 

# Development Information
Programming Language: Spring 2.6.5, Java 11(JDK 11)   
Database: Mysql(aws RDS)

# 개발 규칙
## (1) Git Flow
master : 최종 배포되는 브랜치   
develop : 개발을 위한 브랜치
feature : 각각의 개발자가 기능을 개발하는 브랜치   
release : develop에서 master로 Merge 전에 품질검사를 진행하는 브랜치    
hotfix : 출시 버전에서 발생한 버그를 수정 하는 브랜치


## (2) Commit Message Rules
- Add: 파일 추가
- Feat: 새로운 기능 추가
- Fix: 버그 수정
- Docs: 문서 수정
- Refactor: 코드 리팩토링
- Test: 테스트 코드, 리팩토링 테스트 코드 추가
- Chore: 패키지 매니저 수정, 그 외 기타 수정
  - ex) .gitignore, README ...
- Comment: 필요한 주석 추가 및 변경
- Rename: 파일 또는 폴더 명의 수정 혹은 위치 변경
- Remove: 파일 삭제
- !BREAKING CHANGE: API의 대대적인 변경
- !HOTFIX: 긴급한 버그 수정

예시 -> Fix: 북마크 테스트 수정
