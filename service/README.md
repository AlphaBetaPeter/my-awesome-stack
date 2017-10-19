

## Using the gradle daemon
Create a file named gradle.properties in the following directory:

`/home/<username>/.gradle/` (Linux)

`/Users/<username>/.gradle/` (Mac)

`C:\Users\<username>\.gradle` (Windows)

Add this line to the file:
`org.gradle.daemon=true`
After this, the message won't appear. Subsequent builds will be noticeably faster.

## Using docker

Build

`docker build  . -t gradle-kotlin-vertx-minimal`


Run

`docker run -p 8080:8080 gradle-kotlin-vertx-minimal`

to keep it running in the background and make it restart everytime use

`docker run -d -p 8080:8080 gradle-kotlin-vertx-minimal --restart=always`

Curl

`curl localhost:8080`
