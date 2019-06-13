![1558917620389](C:\Users\ADMINI~1\AppData\Local\Temp\1558917620389.png)

![1558917906204](C:\Users\ADMINI~1\AppData\Local\Temp\1558917906204.png)

![1558919020635](C:\Users\ADMINI~1\AppData\Local\Temp\1558919020635.png)

​	 ![1558922240337](C:\Users\ADMINI~1\AppData\Local\Temp\1558922240337.png)

![1558925085710](C:\Users\ADMINI~1\AppData\Local\Temp\1558925085710.png)

![1558926153291](C:\Users\ADMINI~1\AppData\Local\Temp\1558926153291.png)

![1558926258917](C:\Users\ADMINI~1\AppData\Local\Temp\1558926258917.png)

![1558926438559](C:\Users\ADMINI~1\AppData\Local\Temp\1558926438559.png)

![1558927558005](C:\Users\ADMINI~1\AppData\Local\Temp\1558927558005.png)

![1558928265824](C:\Users\ADMINI~1\AppData\Local\Temp\1558928265824.png)





















# 漫谈Java高并发方案

慕课网 2018-07-05 10:48:45

所有示例代码,请见/下载于

https://github.com/Wasabi1234/concurrency

**1 基本概念**

**1.1 并发**

同时拥有两个或者多个线程，如果程序在单核处理器上运行多个线程将交替地换入或者换出内存,这些线程是同时“存在"的，每个线程都处于执行过程中的某个状态，如果运行在多核处理器上,此时，程序中的每个线程都将分配到一个处理器核上，因此可以同时运行.

**1.2 高并发( High Concurrency)**

互联网分布式系统架构设计中必须考虑的因素之一，通常是指，通过设计保证系统能够同时并行处理很多请求.

**1.3 区别与联系**

- 并发: 多个线程操作相同的资源，保证线程安全，合理使用资源
- 高并发:服务能同时处理很多请求，提高程序性能

**2 CPU**

**2.1 CPU 多级缓存**

- 
- 为什么需要CPU cache
- CPU的频率太快了，快到主存跟不上
- 如此,在处理器时钟周期内，CPU常常需要等待主存，浪费资源。所以cache的出现，是为了缓解CPU和内存之间速度的不匹配问题(结构:cpu-> cache-> memory ).
- CPU cache的意义
- 1) 时间局部性
- 如果某个数据被访问，那么在不久的将来它很可能被再次访问
- 2) 空间局部性
- 如果某个数据被访问，那么与它相邻的数据很快也可能被访问

**2.2 缓存一致性(MESI)**

- 用于保证多个 CPU cache 之间缓存共享数据的一致
- M-modified被修改
- 该缓存行只被缓存在该 CPU 的缓存中,并且是被修改过的,与主存中数据是不一致的,需在未来某个时间点写回主存,该时间是允许在其他CPU 读取主存中相应的内存之前,当这里的值被写入主存之后,该缓存行状态变为 E
- E-exclusive独享
- 缓存行只被缓存在该 CPU 的缓存中,未被修改过,与主存中数据一致
- 可在任何时刻当被其他 CPU读取该内存时变成 S 态,被修改时变为 M态
- S-shared共享
- 该缓存行可被多个 CPU 缓存,与主存中数据一致
- I-invalid无效

- 
- 乱序执行优化
- 处理器为提高运算速度而做出违背代码原有顺序的优化

**并发的优势与风险**

- 

**3 项目准备**

**3.1 项目初始化**

- 

- 

- 

**3.2 并发模拟-Jmeter压测**

- 

- 

- 

- 

**3.3 并发模拟-代码**

**CountDownLatch**

**Semaphore(信号量)**

以上二者通常和线程池搭配

下面开始做并发模拟

运行发现结果随机,所以非线程安全

**4线程安全性**

**4.1 线程安全性**

当多个线程访问某个类时，不管运行时环境采用何种调度方式或者这些进程将如何交替执行，并且在主调代码中不需要任何额外的同步或协同，这个类都能表现出正确的行为，那么就称这个类是线程安全的

**4.2 原子性**

**4.2.1 Atomic 包**

- AtomicXXX:CAS,Unsafe.compareAndSwapInt
- 提供了互斥访问，同一时刻只能有一个线程来对它进行操作

- AtomicReference,AtomicReferenceFieldUpdater

- AtomicBoolean

- 
- AtomicStampReference : CAS的 ABA 问题

**4.2.2 锁**

- synchronized:依赖 JVM
- 修饰代码块:大括号括起来的代码，作用于调用的对象
- 修饰方法: 整个方法，作用于调用的对象

- 
- 修饰静态方法:整个静态方法，作用于所有对象

synchronized 修正计数类方法

- 修饰类:括号括起来的部分，作用于所有对象
- 子类继承父类的被 synchronized 修饰方法时,是没有 synchronized 修饰的!!!

Lock: 依赖特殊的 CPU 指令,代码实现

**4.2.3 对比**

- synchronized: 不可中断锁，适合竞争不激烈，可读性好
- Lock: 可中断锁，多样化同步，竞争激烈时能维持常态
- Atomic: 竞争激烈时能维持常态，比Lock性能好; 只能同步一
- 个值

**4.3 可见性**

- 一个线程对主内存的修改可以及时的被其他线程观察到

**4.3.1 导致共享变量在线程间不可见的原因**

- 线程交叉执行
- 重排序结合线程交叉执行
- 共享变量更新后的值没有在工作内存与主存间及时更新

**4.3.2 可见性之synchronized**

- JMM关于synchronized的规定
- 线程解锁前，必须把共享变量的最新值刷新到主内存
- 线程加锁时，将清空工作内存中共享变量的值，从而使
- 用共享变量时需要从主内存中重新读取最新的值(加锁与解锁是同一把锁)

**4.3.3 可见性之volatile**

- 通过加入内存屏障和禁止重排序优化来实现
- 对volatile变量写操作时，会在写操作后加入一条store
- 屏障指令，将本地内存中的共享变量值刷新到主内存
- 对volatile变量读操作时，会在读操作前加入一条load
- 屏障指令，从主内存中读取共享变量

- 

- 

- 
- volatile使用

```
volatile boolean inited = false;
//线程1:
context = loadContext();
inited= true;
// 线程2:
while( !inited ){
 sleep();
}
doSomethingWithConfig(context)
```

**4.4 有序性**

一个线程观察其他线程中的指令执行顺序，由于指令重排序的存在，该观察结果一般杂乱无序

JMM允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，却会影响到多线程并发执行的正确性

**4.4.1 happens-before 规则**

**5发布对象**

**5.1 安全发布对象**

**7 AQS**

**7.1 介绍**

- 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架
- 利用了一个int类型表示状态
- 使用方法是继承
- 子类通过继承并通过实现它的方法管理其状态{acquire 和release} 的方法操纵状态
- 可以同时实现排它锁和共享锁模式(独占、共享)
- 同步组件

**CountDownLatch**

```
package com.mmall.concurrency.example.aqs;
```

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

/**

- @author shishusheng
- */
- @Slf4j
- public class CountDownLatchExample1 {
- private final static int threadCount = 200;
- public static void main(String[] args) throws Exception {

```
ExecutorService exec = Executors.newCachedThreadPool();
final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
for (int i = 0; i < threadCount; i++) {
 final int threadNum = i;
 exec.execute(() -> {
 try {
 test(threadNum);
 } catch (Exception e) {
 log.error("exception", e);
 } finally {
 countDownLatch.countDown();
 }
 });
}
countDownLatch.await();
log.info("finish");
exec.shutdown();
```

- }
- private static void test(int threadNum) throws Exception {
- Thread.sleep(100);
- log.info("{}", threadNum);
- Thread.sleep(100);
- }
- }
- package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;

/**

- 指定时间内处理任务
- @author shishusheng
- */
- @Slf4j
- public class CountDownLatchExample2 {
- private final static int threadCount = 200;
- public static void main(String[] args) throws Exception {

```
ExecutorService exec = Executors.newCachedThreadPool();
final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
for (int i = 0; i < threadCount; i++) {
 final int threadNum = i;
 exec.execute(() -> {
 try {
 test(threadNum);
 } catch (Exception e) {
 log.error("exception", e);
 } finally {
 countDownLatch.countDown();
 }
 });
}
countDownLatch.await(10, TimeUnit.MILLISECONDS);
log.info("finish");
exec.shutdown();
```

- }
- private static void test(int threadNum) throws Exception {
- Thread.sleep(100);
- log.info("{}", threadNum);
- }
- }

```
##Semaphore用法
![](https://upload-images.jianshu.io/upload_images/4685968-e6cbcd4254c642c5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/4685968-dbefbf2c76ad5a2a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/4685968-41f5f5a5fd135804.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##CycliBarrier
```

- package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

/**

- @author shishusheng
- */
- @Slf4j
- public class CyclicBarrierExample1 {
- private static CyclicBarrier barrier = new CyclicBarrier(5);
- public static void main(String[] args) throws Exception {

```
ExecutorService executor = Executors.newCachedThreadPool();
for (int i = 0; i < 10; i++) {
 final int threadNum = i;
 Thread.sleep(1000);
 executor.execute(() -> {
 try {
 race(threadNum);
 } catch (Exception e) {
 log.error("exception", e);
 }
 });
}
executor.shutdown();
```

- }
- private static void race(int threadNum) throws Exception {
- Thread.sleep(1000);
- log.info("{} is ready", threadNum);
- barrier.await();
- log.info("{} continue", threadNum);
- }
- }

```
![](https://upload-images.jianshu.io/upload_images/4685968-4fb51fa4926fd70e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```

- package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;

/**

- @author shishusheng
- */
- @Slf4j
- public class CyclicBarrierExample2 {
- private static CyclicBarrier barrier = new CyclicBarrier(5);
- public static void main(String[] args) throws Exception {

```
ExecutorService executor = Executors.newCachedThreadPool();
for (int i = 0; i < 10; i++) {
 final int threadNum = i;
 Thread.sleep(1000);
 executor.execute(() -> {
 try {
 race(threadNum);
 } catch (Exception e) {
 log.error("exception", e);
 }
 });
}
executor.shutdown();
```

- }
- private static void race(int threadNum) throws Exception {
- Thread.sleep(1000);
- log.info("{} is ready", threadNum);
- try {
- barrier.await(2000, TimeUnit.MILLISECONDS);
- } catch (Exception e) {
- log.warn("BarrierException", e);
- }
- log.info("{} continue", threadNum);
- }
- }

```
![await 超时导致程序抛异常](https://upload-images.jianshu.io/upload_images/4685968-0f899c23531f8ee8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```

- package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.Semaphore;

/**

- @author shishusheng
- */
- @Slf4j
- public class SemaphoreExample3 {
- private final static int threadCount = 20;
- public static void main(String[] args) throws Exception {

```
ExecutorService exec = Executors.newCachedThreadPool();
final Semaphore semaphore = new Semaphore(3);
for (int i = 0; i < threadCount; i++) {
 final int threadNum = i;
 exec.execute(() -> {
 try {
 // 尝试获取一个许可
 if (semaphore.tryAcquire()) {
 test(threadNum);
 // 释放一个许可
 semaphore.release();
 }
 } catch (Exception e) {
 log.error("exception", e);
 }
 });
}
exec.shutdown();
```

- }
- private static void test(int threadNum) throws Exception {
- log.info("{}", threadNum);
- Thread.sleep(1000);
- }

}

```
#9 线程池
##9.1 newCachedThreadPool
![](https://upload-images.jianshu.io/upload_images/4685968-1122da7a48223ba1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##9.2 newFixedThreadPool
![](https://upload-images.jianshu.io/upload_images/4685968-0ea942bf12e5210f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##9.3 newSingleThreadExecutor
看出是顺序执行的
![](https://upload-images.jianshu.io/upload_images/4685968-989d59429f589403.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##9.4 newScheduledThreadPool
![](https://upload-images.jianshu.io/upload_images/4685968-f7536ec7a1cf6ecc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/4685968-c90e09d5bfe707e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
#10 死锁
![](https://upload-images.jianshu.io/upload_images/4685968-461f6a4251ae8ca4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/4685968-46d58773e597195f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```








高并发 











![1559219025185](C:\Users\ADMINI~1\AppData\Local\Temp\1559219025185.png)