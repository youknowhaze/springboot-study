package com.incredible.springbootdoing.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.*;

import java.util.*;

/**
 * create by incredile
 * 对于redis操作的一个工具类，可以直接拷贝使用，前提为，非jedis依赖的redis
 * 1、操作redis字符串和散列数据类型
 */
public class RedisHandleUtil {


    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    /*
      stringRedisTemplete继承了RedisTemplate，在它的构造器中声明并指定了序列化器.
      且它指定了redisTemplete的类型为<String,String>
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     * 操作redis字符串和散列数据类型（hash）
     */
    public void redisForStringAndHash(){
        redisTemplate.opsForValue().set("key1","value1");

        redisTemplate.opsForValue().set("int_key","1");
        stringRedisTemplate.opsForValue().set("int","1");
        // redisTemplate本身默认使用的jdk的序列化器，无法进行计算，这里使用的重写的templete是可以进行计算的
        //这里的重写实际上与stringRedisTemplate进行序列化器设置是一样的
        Long result0 = redisTemplate.opsForValue().increment("int_key",10);
        Long result = stringRedisTemplate.opsForValue().increment("int",10);
        System.out.println(result0+": ----------- :"+result);

        Map<String,String> map = new HashMap();
        map.put("hash_key01","hash_value01");
        map.put("hash_key02","hash_value02");

        //存入一个散列数据类型(key,map)
        stringRedisTemplate.opsForHash().putAll("myhash01",map);
        //增加一个字段
        stringRedisTemplate.opsForHash().put("myhash01","hash_key03","hash_value03");
        //绑定散列操作的key，这样可以连续对同一个散列数据类型进行操作
        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("myhash01");
        // 移除一个字段
        hashOperations.delete("hash_key02");
        //增加一个字段
        hashOperations.put("hash_key04","hash_value04");

    }


    /**
     * 操作redis 列表型数据（List）
     */
    public void redisForList(){
        // 从左边插入数据,即一条一条地将数据插入到前方
        // value04 | value03 | value02 | value01 | 列表头 | ..... | 列表尾
        stringRedisTemplate.opsForList().leftPushAll("list-1","value01","value02","value03","value04");
        List<String> rightList = new ArrayList();
        rightList.add("right-1");rightList.add("right-2");rightList.add("right-3");
        //将数据从右方插入,如下：
        // 列表头 | ..... | 列表尾 | right-1 | right-2 | right-3
        stringRedisTemplate.opsForList().rightPushAll("list-2",rightList);

        //绑定rightList,可以对绑定后的对象进行多次操作
        BoundListOperations listOperations = stringRedisTemplate.boundListOps("list-2");
        //得到集合右边的成员
        Object listRigjtObj = listOperations.rightPop();
        //redis队列从0 开始，这里意思是得到list中第二个位置的值，为 right-2
        Object listIndexObj = listOperations.index(1);
        // 将数据从list左边插入列表
        listOperations.leftPush("right-0");
        // 得到链表长度
        Long size = listOperations.size();
        // 获取链表区间，整个链表的区间为【0，size-1】
        List<String> rangList = listOperations.range(0,size-2);
    }


    /**
     * 操作redis 集合数据（Set）
     */
    public void redisForSet(){
        // set集合不允许有重复数据，所以这里插入两个v1，只会有一个数据进入集合中
        redisTemplate.opsForSet().add("set-1","v1","v1","v2","v3","v4");
        stringRedisTemplate.opsForSet().add("set-2","a1","a2","a3");

        //绑定set1集合
        BoundSetOperations setOperations = stringRedisTemplate.boundSetOps("set-1");
        setOperations.add("v6","v7"); //增加两个元素
        setOperations.remove("v1","v3"); //移除两个元素
        Long length = setOperations.size(); //得到set集合的长度
        Set set1 = setOperations.members(); //返回整个set集合


    }






}
