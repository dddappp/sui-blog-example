# How to Develop a Blog Example on Sui

An example of developing a blog_example application based on the Sui platform.

## Programing

It requires less than 30 lines of code to be written by the developer (all of which is a description of the domain model), and then generates a blog example that emulates [RoR Getting Started](https://guides.rubyonrails.org/getting_started.html) in one click, without requiring the developer to write a single line of other code.

### Write DDDML Model File

See: [dddml/blog.yaml](./dddml/blog.yaml).

### Run dddappp Project Creation Tool

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

## Test Example


### Publish the Sui contracts

Execute the following command in the directory `sui-contracts` to publish the contracts to the chain:

```shell
sui client publish --gas-budget 1000000000 --skip-dependency-verification
```

If the command is executed successfully, the transaction digest of this publication will be output. For example:

```*shell
*----- Transaction Digest ----
3yydwTgzKJ9nQ6bEXQaacHQFyDRaMi924mWMGoFFat83
----- Transaction Data ----
#...
```

Take note of this transaction digest.


### Configuring Off-Chain Service

Open the `application-test.yml` file located in the directory `sui-java-service/suiblogexample-service-rest/src/main/resources` and set the published transaction digest. After setting, it should look like this:

```yaml
sui:
  contract:
    jsonrpc:
      url: "https://fullnode.testnet.sui.io/"
      #url: "http://localhost:9000"
    package-publish-transaction: "3yydwTgzKJ9nQ6bEXQaacHQFyDRaMi924mWMGoFFat83"
```

This is the only place where off-chain service need to be configured, and it's that simple.


### Creating a Database for Off-Chain Service

Use a MySQL client to connect to the local MySQL server and execute the following script to create an empty database (assuming the name is `test2`):

```sql
CREATE SCHEMA `test2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
```

Go to the `sui-java-service` directory and package the Java project:

```shell
mvn package
```

Then, run a command-line tool to initialize the database:

```shell
java -jar ./suiblogexample-service-cli/target/suiblogexample-service-cli-0.0.1-SNAPSHOT.jar ddl -d "./scripts" -c "jdbc:mysql://127.0.0.1:3306/test2?enabledTLSProtocols=TLSv1.2&characterEncoding=utf8&serverTimezone=GMT%2b0&useLegacyDatetimeCode=false" -u root -p 123456
```

### Starting Off-Chain Service

In the `sui-java-service` directory, run the following command to start the off-chain service:

```shell
mvn -pl suiblogexample-service-rest -am spring-boot:run
```

### Tip: Using this Cheatsheet

After the off-chain services are started, you can access this URL to get a cheatsheet on how to use the Sui Client CLI to call on-chain contracts: http://localhost:1023/api/sui.contract/SuiClientCLICheatsheet.md

In the cheatsheet, the Package Id of the contracts you just published and the Object Ids (created according to the Id Generators declared in models) you need when creating certain entities are already filled in for you. The parameters you need to fill in are placeholders containing their type and meaning (name). You can copy these commands, modify them as needed, and execute them directly in a terminal.

## CRUD Articles

## CRUD Comments

## Some Tips

### Blog Example for Rooch

Here is a Rooch version of blog sample: https://github.com/rooch-network/rooch/blob/main/examples/blog_example/README.md

### Clean Up Exited Docker Containers

Run the command:

```shell
docker rm $(docker ps -aq --filter "ancestor=wubuku/dddappp:0.0.1")
```



