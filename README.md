# README

```shell
docker run \
-v .:/myapp \
wubuku/dddappp:0.0.1 \
--dddmlDirectoryPath /myapp/dddml \
--boundedContextName Test.SuiBlogExample \
--suiMoveProjectDirectoryPath /myapp/sui-contracts \
--boundedContextSuiPackageName sui_blog_example\
--boundedContextJavaPackageName org.test.suiblogexample \
--javaProjectsDirectoryPath /myapp/sui-java-service \
--javaProjectNamePrefix suiblogexample \
--pomGroupId test.suiblogexample
```
