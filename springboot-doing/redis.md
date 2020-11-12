## NoSql的四大分类

### 1、KV键值对：
 redis

### 2、文档型数据库：
- MongDB(必须掌握)
  基于分布式文件存储的数据库，用C++编写，主要用于处理大量的文档
  是介于关系型数据库和非关系型数据库之间的产品
  
- ConthDB
### 3、列存储数据库
  HBase
  分布式文件系统
### 4、 图关系数据库
  不是存图形，存的是关系，如：朋友圈社交网络，广告推荐


- string 字符
- list 集合
- hash 集合（map）
- set  集合
- zset  有序集合
- Geo 地理空间位置（在redis3.2之后就推出了）
- Hyperloglog 基数计算（比如网站访问人数，错误率有0.84%）
- BitMap 位集合（结果只有0和1，比如是否打卡）

### 5、redis-String讲解
redis基于内存，具有可持续化操作，**是单线程的** ，单线程并不一定比多线程快，多线程涉及到CPU的上下文切换，会花费一定的时间，且redis是单线程的内存操作，速度所以很快。
速度： CPU>内存>磁盘
redis 默认有16个数据库，默认使用第一个（db0）

````
# 清空当前库
flushdb 
# 清空所有库
flushall 
#切换到数据库2
select 2  
# 列出所有key
keys *   
# 设置key-redis为（key1，incredible）
set key1 incredible   
# 判断key1的值是否存在
EXISTS key1 
# 从当前数据库中移除key1这个key
move key1 1 
# 获取key1的value值
get key1 
# 让key1的数据10秒钟后过期
EXPIRE key1 10 
# 获取key1的值还有多久过期
ttl key1  
# 查看当前key的类型
type key1 
# 在原有基础上追加一段字符,这个时候获取key1的值为 incredibletest
# 若key1不存在，则相当于set key1 test
APPEND key1 test  
# 获取字符串长度
STRLEN key1
````

````
# 设置key2的初始值为0
set key2 0
# 自增1，每执行一次，key2的值便增加1
incr key2
# 自减1，每执行一次，key2的值便减少1
decr key2
# 设置自增步长，每执行一次，key2的值增加10
INCRBY key2 10
# 设置自减步长，每执行一次，key2的值减少7
DECRBY key2 7


set str no,matter,the,end,while,be
# 截取字符串,获取下标为0到4的字符段
getrange str 0 4
# 截取全部字符
getrange str 0 -1
# 替换字符串，替换指定下标字符为新字符
setrange str 2 xxx


# 设置值的同时设置过期时间,设置（key3，testMsg）且在30秒后过期
# setex
setex key3 30 "testMsg"

#不存在时再设置值 
# setnx 在分布式锁中会常常使用
setnx testkey "name"  //设置成功
setnx testkey "demo"  //设置失败，已存在该key值

# 同时设置多个key-value值(k1,v1)(k2,v2)(k3,v3)
set k1 v1 k2 v2 k3 v3

# 判断是否存在再设值
setnx k1 v1 k4 v4

# 使用hashset设值redis的值
redis-6379:0>mset user1:name zhangsan user1:age 13
"OK"
redis-6379:0>mget user1:name user1:age
 1)  "zhangsan"
 2)  "13"


````

### 6、redis-list讲解

````
redis-6379:0>lpush list one  //插入list元素
"1"
redis-6379:0>lpush list two   //插入list元素
"2"
redis-6379:0>lpush list three   //插入list元素
"3"
redis-6379:0>lrange list 0 -1  //输出所有list
 1)  "three"
 2)  "two"
 3)  "one"
redis-6379:0>lrange list 0 0   // 获取list区间元素
 1)  "three"

redis-6379:0>rpush list forth  //将一个或多个值插入列表右部
"4"
redis-6379:0>lrange list 0 -1
 1)  "three"
 2)  "two"
 3)  "one"
 4)  "forth"

redis-6379:0>lpop list  // 移除list的第一个元素
"three"
redis-6379:0>rpop list  // 移除list右边的第一个元素
"forth"
redis-6379:0>lrange list 0 -1
 1)  "two"
 2)  "one"

redis-6379:0>lindex list 0  // 获取list中指定下标的值
"two"

# 获取队列长度
llen list 
# 移除指定值,移除list中的2个one，从上到下，
# 若存在三个one，则list中还剩余1个one
lrem list 2 one

````


### redis-geo 地址位置

````
# geoadd添加地理位置
# 规则：两极的地理位置无法直接添加，一般的地理位置从网上下载下来通过java程序直接导入
geoadd china:city 116.40 39.90 beijin
geoadd china:city 106.50 29.53 chongqin
geoadd china:city 126.40 29.90 hangzhou
geoadd china:city 104.31 12.13 suzhou
geoadd china:city 123.40 129.30 shanghai
geoadd china:city 106.10 19.53 shenzhou

# 当经纬度超出范围，则报如下错误
redis-6379:0>geoadd china:ctity 123.40 129.30 shanghai
"ERR invalid longitude,latitude pair 123.400000,129.300000"


#########################################################

# 获取地理位置geopos
redis-6379:0>geopos china:city beijin
 1)    1)   "116.39999896287918091"
  2)   "39.90000009167092543"

# 获取两个位置间的距离 geodist
redis-6379:0>geodist china:city beijin hangzhou  //查看北京到杭州的直线距离
"1436447.9687"
redis-6379:0>geodist china:city beijin hangzhou km
"1436.4480"

#获取周围城市（附件的人功能） georadius
# 查找指定经纬度为中心，周围1500km范围内的城市
redis-6379:0>georadius china:city 100 30 1500 km
 1)  "chongqin"
# 显示到中间位置的距离
redis-6379:0>georadius china:city 100 30 1500 km withdist
 1)    1)   "chongqin"
  2)   "629.6756"

# 显示他人的定位信息
redis-6379:0>georadius china:city 100 30 1500 km withcoord
 1)    1)   "chongqin"
  2)      1)    "106.49999767541885376"
   2)    "29.52999957900659211"

# 指定城市为中心，指定范围内查找附件城市
redis-6379:0>georadiusbymember china:city beijin 10000 km
 1)  "chongqin"
 2)  "hangzhou"
 3)  "beijin"


````



### 事务

redis事务本质：一组命令的集合！一个事务中的所有命令都会被序列化，在事务执行的过程中，会按照顺序执行。
一次性、顺序性、排他性，执行一系列的命令

- redis的单条命令是保证原子性的，但是事务不保证原子性
- redis没有隔离级别的概念，所有的命令在事务中没有被直接执行，只有发起执行命令才会执行，Exec！

> 执行事务

redis的事务的操作步骤：
- 开启事务(multi)
- 命令入队(.....)
- 执行事务(exec)
**注意：一次事务执行之后，再次使用事务需要再使用multi命令开启事务**
```
redis-6379:1>multi
"OK"
redis-6379:1>set key1 v1
"QUEUED"
redis-6379:1>set k2 v2
"QUEUED"
redis-6379:1>set k3 v3
"QUEUED"
redis-6379:1>get k2
"QUEUED"
redis-6379:1>exec
 1)  "OK"
 2)  "OK"
 3)  "OK"
 4)  "v2"
```

> 放弃事务 

**回滚事务后，本次事务开启后执行的命令全部回滚，没有成功执行**
```
redis-6379:1>multi
"OK"
redis-6379:1>set k5 v5 
"QUEUED"
redis-6379:1>set k6 v6 
"QUEUED"
redis-6379:1>discard       #回滚事务
"OK"
redis-6379:1>get v5
null
```

> 出现以下异常，事务中的所有命令都不会被执行
- 编译行型异常（代码有问题！命令有错误！）
````
redis-6379:0>multi
"OK"
redis-6379:0>set k1 v1 
"QUEUED"
redis-6379:0>set k2 v2
"QUEUED"
redis-6379:0>setsss k3 v3
"ERR unknown command `setsss`, with args beginning with: `k3`, `v3`, "
redis-6379:0>set k4 v4
"QUEUED"
redis-6379:0>exec
"EXECABORT Transaction discarded because of previous errors."
````
- 运行时异常，如果事务队列中有命令存在语法性错误，那么执行命令时，其他命令是可以正常执行的
````
redis-6379:0>set k1 v1
"OK"
redis-6379:0>multi
"OK"
redis-6379:0>incr k1      # 值加1，只能对number类型操作
"QUEUED"
redis-6379:0>set k2 v2
"QUEUED"
redis-6379:0>get k2
"QUEUED"
redis-6379:0>exec
 1)  "ERR value is not an integer or out of range"
 2)  "OK"
 3)  "v2"
````


### redis 监控 Watch（redis 悲观锁）
> 悲观锁：
- 很悲观，认为什么时候都会出现问题，做什么都会加锁

> 乐观锁
- 很乐观，认为什么时候都不会出问题，所以不会上锁，更新数据的时候去判断一下，此期间是否有人更改过这个数据
- 获取version
- 更新的时候比较version

> Redis监视测试
- 正常执行成功
````
redis-6379:0>set money 100
"OK"
redis-6379:0>set cost 0
"OK"
redis-6379:0>watch money  # 监视money对象
"OK"
redis-6379:0>multi #事务正常结束，期间数据没有改动，这个时候就正常执行成功
"OK"
redis-6379:0>decrby money 20
"QUEUED"
redis-6379:0>incrby cost 20
"QUEUED"
redis-6379:0>exec
 1)  "80"
 2)  "20"
````
- 模拟多线程测试修改数据，使用watch可以当做redis的乐观锁操作，这里开启两个redis命令行窗口进行简单模拟
**窗口一**
````
redis-6379:0>watch money #相当于获取当前money值，当提交事务时发现与此字不一致则事务提交失败
"OK"
redis-6379:0>multi
"OK"
redis-6379:0>decrby money 50
"QUEUED"
redis-6379:0>incrby cost 20
"QUEUED"
##此时等待窗口二执行完数据更改操作再提交事务
redis-6379:0>exec  # 执行完会返回一个空，说明事务提交失败
redis-6379:0>unwatch  # 如果事务执行失败，则先解锁
"OK"
redis-6379:0>watch money  #再加锁，重新开启事务-执行命令-提交事务
"OK"
redis-6379:0>multi
"OK"
redis-6379:0>decrby money 10
"QUEUED"
redis-6379:0>incrby cost 10
"QUEUED"
redis-6379:0>exec
 1)  "71"
 2)  "31"
````
**窗口二**
````
redis-6379:0>incr money
"81"
redis-6379:0>incr cost
"21"
````





