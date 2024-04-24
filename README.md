# InvLib

### Inventory GUI for Java users

* #### 적용방법
  1. GitHub 주소 복사
  2. ~/.m2/repository/io/github에 들어가서 Git Bash Here 오픈
  3. git clone https://github.com/kyungmin08g/invlib.git 입력
  4. 프로젝트에 아래와 같은 방법으로 의존성 주입

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
