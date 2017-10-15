package com.moilioncircle.redis.replicator.startuse;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2017/10/15 15:57
 * @ProjectName redis-replicator
 * @Version 5.0
 */
public enum RedisCommandEnum {

    APPEND("APPEND","追加字符串指令","2.0.0"),

    BIT_FIELD("BIT_FIELD","",""),

    BIT_OP("BIT_OP","位操作","2.6.0"),

    BR_POP_L_PUSH("BR_POP_L_PUSH","RPOPLPUSH阻塞版本","2.2.0"),

    DECR_BY("DECR_BY","对key所储存的值减去减量","1.0.0"),

    DECR("DECR","对key所储存的值减1","1.0.0"),

    DEL("DEL","删除一个或多个key","1.0.0"),

    EVAL("EVAL","对Lua脚本进行请值","2.6.0"),

    EXEC("EXEC","执行所有事物块的命令","1.2.0"),

    EXPIRE_AT("EXPIRE_AT","",""),

    EXPIRE("","",""),

    FLUSH_ALL("","",""),

    FLUSH_DB("","",""),

    H_DEL("","",""),

    H_INCR_BY("","",""),

    H_M_SET("","",""),

    H_SET("","",""),

    H_SET_NX("","",""),

    INCR_BY("","",""),

    INCR("","",""),

    L_INSERT("","",""),

    L_POP("","",""),

    L_PUSH("","",""),

    L_PUSH_X("","",""),

    L_REM("","",""),

    L_SET("","",""),

    L_TRIM("","",""),

    MOVE("","",""),

    M_SET("","",""),

    M_SET_NX("","",""),

    MULTI("","",""),

    PERSIST("","",""),

    P_EXPIRE("","",""),

    P_F_ADD("","",""),

    P_F_COUNT("","",""),

    P_F_MERGE("","",""),

    PING("","",""),

    P_SET_EX("","",""),

    PUBLISH("","",""),

    RENAME("","",""),

    RENAME_NX("","",""),

    RESTORE("","",""),

    R_POP("","",""),

    R_POP_L_PUSH("","",""),

    R_PUSH("","",""),

    R_PUSH_X("","",""),

    S_ADD("","",""),

    SCRIPT("","",""),

    SCRIPT_FLUSH("","",""),

    SCRIPT_LOAD("","",""),

    S_DIFF_STORE("","",""),

    SELECT("","",""),

    SET_BIT("","",""),

    SET("","",""),

    SET_EX("","",""),

    SET_NX("","",""),

    SET_RANGE("","",""),

    S_INTER_STORE("","",""),

    S_MOVE("","",""),

    SORT("","",""),

    S_REM("","",""),

    S_UNIONSTORE("","",""),

    SWAP_DB("","",""),

    UN_LINK("","",""),

    Z_ADD("","",""),

    Z_INCR_BY("","",""),

    Z_INTER_STORE("","",""),

    Z_REM("","",""),

    Z_REM_RANGE_BY_LEX("","",""),

    Z_REM_RANGE_BY_RANK("","",""),

    Z_REM_RANGE_BY_SCORE("","",""),

    Z_UNION_STORE("","",""),

    ;

    private final String code;

    private final String desc;

    private final String version;

    RedisCommandEnum(String code,String desc,String version){
        this.code = code;
        this.desc = desc;
        this.version = version;
    }

    public static RedisCommandEnum explain(String code){

        for (RedisCommandEnum redisCommandEnum : RedisCommandEnum.values()){
            if (redisCommandEnum.code.equals(code)){
                return redisCommandEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getVersion() {
        return version;
    }

}
