# Redis-replicator  
[![Build Status](https://travis-ci.org/leonchen83/redis-replicator.svg?branch=master)](https://travis-ci.org/leonchen83/redis-replicator)
[![Coverage Status](https://coveralls.io/repos/github/leonchen83/redis-replicator/badge.svg?branch=master)](https://coveralls.io/github/leonchen83/redis-replicator?branch=master)  
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.moilioncircle/redis-replicator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.moilioncircle/redis-replicator)  
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.moilioncircle/redis-replicator/badge.svg)](http://www.javadoc.io/doc/com.moilioncircle/redis-replicator)  
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)](https://github.com/leonchen83/redis-replicator/blob/master/LICENSE)  
  
Redis Replicator is a redis RDB and Command parser written in java.  
It can parse,filter,broadcast the RDB and Command events in a real time manner.  
  
#Requirements  
jdk 1.7+  
rdb version 6  
rdb version 7  

#Maven Dependency

```java  
<dependency>
    <groupId>com.moilioncircle</groupId>
    <artifactId>redis-replicator</artifactId>
    <version>1.0.6</version>
</dependency>
```
  
#Flow Chart  
![Alt text](https://github.com/leonchen83/redis-replicator/blob/master/redis-replicator-flow-chart.png)  
  
#Class Chart  
![Alt text](https://github.com/leonchen83/redis-replicator/blob/master/redis-replicator-class-chart.png)  
  
#Usage  
  
##Socket  
  
```java  
        RedisReplicator replicator = new RedisReplicator("127.0.0.1", 6379, Configuration.defaultSetting());
        replicator.addRdbListener(new RdbListener.Adaptor() {
            @Override
            public void handle(Replicator replicator, KeyValuePair<?> kv) {
                System.out.println(kv);
            }
        });
        replicator.addCommandListener(new CommandListener() {
            @Override
            public void handle(Replicator replicator, Command command) {
                System.out.println(command);
            }
        });
        replicator.open();
```

##File  

```java  
        RedisReplicator replicator = new RedisReplicator(new File("dump.rdb"), Configuration.defaultSetting());
        replicator.addRdbFilter(new RdbFilter() {
            @Override
            public boolean accept(KeyValuePair<?> kv) {
                return kv.getKey().startsWith("SESSION");
            }
        });
        replicator.addRdbListener(new RdbListener.Adaptor() {
            @Override
            public void handle(Replicator replicator, KeyValuePair<?> kv) {
                System.out.println(kv);
            }
        });

        replicator.open();
```  

#Command Extension  
  
* **write a command parser.**  
```java  
public class AppendParser implements CommandParser<AppendParser.AppendCommand> {

    @Override
    public AppendCommand parse(CommandName cmdName, Object[] params) {
        return new AppendCommand((String) params[0], (String) params[1]);
    }

    public static class AppendCommand implements Command {
        public final String key;
        public final String value;

        public AppendCommand(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "AppendCommand{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

        @Override
        public CommandName name() {
            return CommandName.name("APPEND");
        }
    }
}
```
  
* **register this parser.**  
```java  
    RedisReplicator replicator = new RedisReplicator("127.0.0.1",6379);
    replicator.addCommandParser(CommandName.name("APPEND"),new AppendParser());
```
  
* **handle event about this command.**  
```java
        replicator.addCommandListener(new CommandListener() {
            @Override
            public void handle(Replicator replicator, Command command) {
                if(command.name().equals(CommandName.name("APPEND"))){
                    //your code here
                }
            }
        });
```
  
#References  
  * [rdb.c](https://github.com/antirez/redis/blob/unstable/src/rdb.c)  
  * [Redis RDB File Format](https://github.com/sripathikrishnan/redis-rdb-tools/wiki/Redis-RDB-Dump-File-Format)  
  * [Redis Protocol specification](http://redis.io/topics/protocol)  
