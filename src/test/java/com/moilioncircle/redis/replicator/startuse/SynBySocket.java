package com.moilioncircle.redis.replicator.startuse;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.cmd.Command;
import com.moilioncircle.redis.replicator.cmd.CommandListener;
import com.moilioncircle.redis.replicator.cmd.impl.ExpireCommand;
import com.moilioncircle.redis.replicator.cmd.impl.PingCommand;
import com.moilioncircle.redis.replicator.cmd.impl.SetCommand;
import com.moilioncircle.redis.replicator.rdb.RdbListener;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2017/10/14 12:27
 * @ProjectName redis-replicator
 * @Version 5.0
 */
public class SynBySocket {

    static final Log logger = LogFactory.getLog(SynBySocket.class);

    public static void main(String[] args) throws Exception{
        Replicator replicator = new RedisReplicator("redis://192.168.100.107:8003");

        replicator.addRdbListener(new RdbListener.Adaptor(){
            @Override
            public void handle(Replicator replicator, KeyValuePair<?> kv) {
                logger.info(kv);
            }
        });

        replicator.addCommandListener(new CommandListener() {
            @Override
            public void handle(Replicator replicator, Command command) {
//                logger.debug(command);
                if (command instanceof PingCommand){
                    return;
                }
                if (command instanceof SetCommand){
                    logger.info(command);
                    SetCommand setCommand = (SetCommand) command;
                    logger.info(setCommand.getKey() + " --> " +setCommand.getValue());
                }
                if (command instanceof ExpireCommand){
                    logger.debug(command);
                    ExpireCommand expireCommand = (ExpireCommand)command;
                    logger.info(expireCommand.getKey() + " --> " + expireCommand.getEx());
                }
            }
        });

        replicator.open();
    }


}
