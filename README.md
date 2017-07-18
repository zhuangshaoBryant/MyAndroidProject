#Android一些小demo案例，用于巩固Android知识。
##1、ActivityLifeCycle

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