# README

```shell
docker run \
-v .:/myapp \
wubuku/dddappp:0.0.1 \
--dddmlDirectoryPath /myapp/dddml \
--boundedContextName Test.SuiBlogExample \
--suiMoveProjectDirectoryPath /myapp/sui-contracts \
--boundedContextSuiPackageName SuiBlogExample \
--boundedContextJavaPackageName org.test.suiblogexample \
--javaProjectsDirectoryPath /myapp/sui-java-service \
--javaProjectNamePrefix suiblogexample \
--pomGroupId test.suiblogexample
```
