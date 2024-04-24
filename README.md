# InvLib

### Inventory GUI for Java users

* #### 적용방법
  1. ~/.m2/repository/io/github에 들어가서 Git Bash Here 오픈(만약 ~/.m2/repository로 들어가서 io/github가 없다면 폴더 생성)
  2. git clone https://github.com/kyungmin08g/invlib.git 입력
  3. 프로젝트에 아래와 같은 방법으로 의존성 주입

---

#### Gradle

```java
repositories {
    mavenLocal()
}
```

```java
dependencies {
    implementation "io.github.invlib.kyungmin08g:invlib-api:<version>"
}
```
