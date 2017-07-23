# Android一些小demo案例，用于巩固Android知识。
## 1、ActivityLifeCycle

1.创建控制日志输出的工具类，可以定义自己的customLevel来决定输出什么级别的日志。

2.activity正常使用的生命周期方法输出

3.Intent显示启动
* 生命周期是。第一个MainActivity先onPause(),然后SecondActivity依次onCreate、onStart、onResume，接着MainActivity调用onStop方法

![启动第二个Activity](https://github.com/zhuangshaoBryant/MyAndroidProject/raw/master/Screenshots/1.png)

* 返回键后，SecondActivity先onPause，然后MainActivity omRestart启动显示之后，SecondActivity再onStop、onDestroy。

![返回到第一个Activity](https://github.com/zhuangshaoBryant/MyAndroidProject/raw/master/Screenshots/2.png)

* 总结就是第一个先onPause后，才会显示另外一个Activity，之后再调用第一个的onStop。因此不要在onPause里做重量级操作，尽量在onStop里做，从而使新的Activity尽快显示。

4. Intent隐式启动
* 学习startActivityForResult()方法
* 遇到的坑：UI布局中RelativeLayout中， `android:layout_above="@id/request_button"` 此id要保证在request_button之后

5. 异常情况下的生命周期
* 旋转屏幕 onPause、**onSaveInstanceState**、onStop、onDestroy,再onCreate、onStart、**onRestoreInstanceState方法调用**、onResume，此时自动会保存信息
onSaveInstanceState在onStop之前，与onPause无时序关系，onRestoreInstanceState在onStart之后

![异常销毁时生命周期](https://github.com/zhuangshaoBryant/MyAndroidProject/raw/master/Screenshots/3.png)
* ` android:configChanges="orientation|screenSize`在activity里添加这样一个属性，就不会重新创建activity了,但是会调用onConfigurationChanged方法
* 当系统内存不足或者发生意外时，可以通过onSaveInstanceState方法进行存储值，
并且通过onCreate方法或者onRestoreInstanceState方法取值，区别是onCreate需要进行判断是否为空，因为正常启动是为空的，
而onRestoreInstanceState方法调用时Bundle一定是有值的。

* 遇到的坑：调用的是`protected void onSaveInstanceState(Bundle outState) `方法，而不是`public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) `

* 总结使用时机：onSaveInstanceState是在出现非主观关闭activity并且有机会显示的情况下调用，例如：按下home键，切换其他程序，屏幕旋转等。
onSaveInstanceState会自动保存一些实现onSaveInstanceState方法的UI控件的状态（例如EditText、checkBox等），
它适合保存瞬态数据, 比如UI控件的状态, 成员变量的值等，而不应该用来保存持久化数据，（比如将数据保存到数据库或文件中）
持久化数据应该在用户离开当前的 activity时，在 onStop() 中保存。


6. Activity启动模式
* singleinstance模式：A打开B，B打开C。B设置为单例模式
返回的时候是C、A、B
![异常销毁时生命周期](https://github.com/zhuangshaoBryant/MyAndroidProject/raw/master/Screenshots/4.png)
* TaskAffinity 与 singleTask使用时，待启动的Activity会运行在名字为TaskAffinity的任务栈中
TaskAffinity 与 allowTaskReparenting属性配对使用时，allowTaskReparenting为true。（A应用启动B应用的Activity C时，
ActivityC运行在A的栈中，当打开B应用时，ActivityC会返回到B应用的任务栈中，因此打开的不是B的启动Activity，而是ActivityC）

## 2、ListViewAndRecyclerViewTest
* 学习RecyclerView使用用法，包括子View的点击事件
## 3、FragmentTest
* Fragment生命周期:
启动到显示：onAttach、onCreate、onCreateView、onActivityCreated、onStart、onResume
![异常销毁时生命周期](https://github.com/zhuangshaoBryant/MyAndroidProject/raw/master/Screenshots/5.png)
切换另一个Fragment时：onPause、onStop、onDestroyView
再显示回来：onCreateView、onActivityCreated、onStart、onResume
销毁：onPause、onStop、onDestroyView、onDestroy、onDetach
* 异常情况下的生命周期：
onSaveInstanceState（）方法来保存一些数据，在onCreate、onCreateView、onActivityCreated里面的Bundle可以获取到
* 一个小demo，用fragment模拟新闻页面，打开标题进入内容，同时适配平板与手机。
* 易混点:
1、在activity里的fragment标签，添加name属性，会自动布局fragment
2、getSupportFragmentManager.beginTranscation.replace(R.layout.xxx,fragment).commit   可以实现动态添加fragment
3、在activity里调用fragment的方法，首先需要获取fragment实例，getFragmentManager.findFragmentById(R.id.xxx)，然后可以调用fragment的方法了
4、在fragment里调用activity的方法，getActivity().xxx方法

## 4、BroadcastTest
1. 学习动态广播、静态广播
动态广播，要在代码中registerReceiver(mDongtaiTaiBroadcast, mIntentFilter)，最后记得unRegisterReceiver，以免内存泄漏
静态广播，创建BroadcastReceiver,会在清单文件里注册，添加name属性，例如开启启动等
2. 自定义广播
* 第一种：自定义静态广播：类似静态广播，创建BroadcastReceiver,会在清单文件里注册，添加自定义的name属性，在代码中添加`sendBroadcast(new Intent("自定义name属性"));`
* 第二种：自定义动态广播：首先动态注册广播,添加自定义的IntentFilter。 之后发送广播，使用自定义的Intent。如下例子：
```
    mIntentFilter.addAction("com.zhuang.jackyli.mylocal");
    localBroadcastManager.registerReceiver(mDongtaiTaiBroadcast,mIntentFilter);
    
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent("com.zhuang.jackyli.mylocal");
            localBroadcastManager.sendBroadcast(intent);
        }
    });
```
3. 有序广播`sendBroadcast(new Intent("自定义name属性"),null);`在清单文件里<intent-filter 添加priority属性>
4. 本地广播：使用localBroadcastManager.registerReceiver和localBroadcastManager.unRegisterReceiver，其余用法一样。
5. 小demo

## 5、ServiceTest
* 学习bindService的用法，Service与Activity之间的交互
service里创建内部类MyBinder继承Binder，onBind（）方法里返回MyBinder对象，因此可以调用MyBinder里的方法
Activity使用bindService启动Service，其中当绑定Service时ServiceConnection的onServiceConnected会返回一个Binder，将其转换为
service里的MyBinder对象，从而可以调用Service里的方法
* 学习了通知栏的消息
`startForeground(1,notification);`会让服务处于前台，会在系统通知栏一直显示
* 生命周期
startService时:onCreate()、onStartCommand()、onDestory()；其中onCreate()调用一次，而每次startService时会调用onStartCommand方法
bindService时：onCreate()、onBind()、onUnBind()、onDestory()
当同时调用startService与bindService时，需要同时stopService()与unbindService()才会执行onDestory销毁。
* IntentService：用于执行后台耗时的服务，任务执行完后会自动结束，由于是服务，优先级会比线程高很多
自建MyIntentService继承IntentService，onHandleIntent方法就是子线程中的方法，特点是运行完就结束服务

## 6、Handler、AsyncTask用法
* 网络下载进度条在通知栏上显示的demo

## AndroidStudio快捷键
* 提取全局变量：Ctrl+Alt+F
* 提取方法：Ctrl+Alt+M
* 在代码下新增加一行：shift+enter
* 光标跳转上下一个：ctrl+alt+left/right
* 某单词上/下一个出现的地方：shift+F3/F3
* 项目中查找某个类：ctrl+N
#markdown基本用法学习使用


**目录 (Table of Contents)**

[TOC]

# Android一些小demo案例，用于巩固Android知识。
##1、ActivityLifeCycle：
* 无序标题
* asd
1. 有序列表数字+.
2. Green

>引用

这是一个链接 [链接地址](http://example.com/).

这是 **加粗重点词** 的方法

一行代码：`npm install marked`

多行代码：
```
   public class MainActivity extends AppCompatActivity {
       public static final String TEG = "MainActivityCycleTest";
   
       @Override
       protected void onPause() {
           super.onPause();
           LogUtil.d(TEG,"onPause方法调用");

```
   
![测试图片](https://github.com/zhuangshaoBryant/MyAndroidProject/raw/master/Screenshots/1.png)