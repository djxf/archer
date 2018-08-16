## Archer

## 目录

* [介绍](#介绍)
* [依赖框架](#依赖框架)
* [方法说明](#方法说明)
* [使用说明](#使用说明)
* [License](#License)

## 介绍
从原来项目中抽离的mvp快速开发框架，封装了一些常用的功能，使用rxlifecycle管理RxJava生命周期，比如简化了Activity跳转，
还提供了一些经常用的的属性，比如context，activity，fragment可以直接获取，
针对Activity和Fragment的生命周期，定义了自己的生命周期处理方法，比如setLayoutId()设置xml layout，doBusiness()处理业务代码等，
大大加快开发速度。解决了mvp在安卓上运用的一些坑，比如Presenter会造成内存泄漏
不过这些都不用担心了，Presenter与Activity和Fragment的生命周期进行了绑定，所以无论你使用Activity还是Fragment都无需担心Presenter内存泄漏问题。

## 依赖框架
使用Kotlin实现，如果你是Java项目，还需要在项目中引入Kotlin，或者参考Demo中的jBase包中的Java实现  
在根目录下的build.gradle文件中对应位置添加如下内容
```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

[![](https://jitpack.io/v/nicolite/archer.svg)](https://jitpack.io/#nicolite/archer)

在app目录下的build.gradle文件中对应位置添加如下内容
```groovy
dependencies {
  //latestVersion替换为上面徽章后面的数字
    implementation 'com.github.nicolite:archer:latestVersion'
    implementation 'com.trello.rxlifecycle2:rxlifecycle:2.2.1'
    implementation 'com.trello.rxlifecycle2:rxlifecycle-components:2.2.1'
   }
```
## 方法说明

KBaseActivity:  

```kotlin
   // 初始化Activity配置，该方法是在setContentView之前调用，用于Activity的一些配置，比如隐藏actionBar，设置沉静状态栏
   protected open fun initConfig(savedInstanceState: Bundle?)
   
    //初始化Bundle参数，获取上一个页面传过来的bundle数据
    protected open fun initBundleData(bundle: Bundle?)

   //获取 xml layout，设置layout文件
    protected open fun setLayoutId(): Int 

    
    //业务逻辑代码，该方法在onCreate中调用，在initBundleData()之后调用，再这里写业务代码
    protected open fun doBusiness()

  
    /**
     * 页面跳转
     */
    fun startActivity(clazz: Class<*>)

    /**
     * 页面携带数据跳转
     *
     * @param clazz
     * @param bundle
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?) 

    /**
     * 包含回调的页面跳转
     *
     * @param clazz
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, requestCode: Int) 

    /**
     * 带动画的页面跳转
     *
     * @param clazz
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivityWithOptions(clazz: Class<*>, options: Bundle?)
    
    /**
     * 带数据和动画的页面跳转
     *
     * @param clazz
     * @param bundle  数据
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?, options: Bundle?)

    /**
     * 包含回调和数据的页面跳转
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, bundle: Bundle?, requestCode: Int)


   //将窗口设置为半透明
    fun setPixelFormat()

    //全屏
    fun setFullScreen()

    //隐藏actinBar
    fun hideToolBar()

    
    //设置屏幕旋转 rotate可以为SENSOR(自动旋转)/PORTRAIT(竖屏)/LANDSCAPE(横屏)
    fun setScreenRotate(rotate: Int)

    //设置沉浸状态栏
    fun setImmersiveStatusBar()

  // 使布局背景填充状态栏，布局将会显示到状态栏中
    fun setLayoutNoLimits()
    
   // 设置状态栏字体为深色，某些5.x版本机器无效
    fun setDeepColorStatusBar()
```  

KBaseFragment:  
  
```kotlin
 /**
     * 初始化Fragment配置,
     */
    protected open fun initConfig(savedInstanceState: Bundle?)

    /**
     * 初始化Bundle参数
     *
     * @param arguments
     */
    protected open fun initArguments(arguments: Bundle?) 

    /**
     * 获取 xml layout
     */
    protected open fun setLayoutId(): Int 
    
    /**
     * 业务逻辑代码
     */
    protected open fun doBusiness()
    
    /**
     * fragment对用户可见
     *
     * @param isVisible      是否可见
     * @param isFirstVisible 是否第一次可见
     */
    protected open fun visibleToUser(isUserVisible: Boolean, isFirstVisible: Boolean)

    /**
     * 是否为Attach到Activity中的第一个fragment
     * 如果使用懒加载，配合visibleToUser使用的话，第一个显示的fragment必须返回true
     * @return
     */
    protected open fun isFirstFragment(): Boolean 
    
    /**
     * 页面跳转
     */
    fun startActivity(clazz: Class<*>) 
    /**
     * 页面携带数据跳转
     *
     * @param clazz
     * @param bundle
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?)

    /**
     * 包含回调的页面跳转
     *
     * @param clazz
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, requestCode: Int) 

    /**
     * 带动画的页面跳转
     *
     * @param clazz
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivityWithOptions(clazz: Class<*>, options: Bundle?)

    /**
     * 带数据和动画的页面跳转
     *
     * @param clazz
     * @param bundle  数据
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?, options: Bundle?) 

    /**
     * 包含回调和数据的页面跳转
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, bundle: Bundle?, requestCode: Int)
```

## 使用说明
使Activity继承KBaseActivity，Fragment继承KBaseFragment，Presenter继承KBasePresenter，你也可以将你的View接口集成KBaseView(非必需)
当然你也可以通过继承这些类实现自己的Base类，这样可以添加自己的一些需求，推荐使用此方式。  

打开Log输出，默认是关闭的，打开后可以看到完整的生命周期调用，便于调试,推荐在Application类中配置，也可以在其他地方配置  
```kotlin
LogUtils.debug = true
```

基类继承写法:  

```kotlin
abstract class BaseActivity : KBaseActivity() {
 //重写或者添加自己的方法
}

abstract class BaseFragment : KBaseFragment() {
 //重写或者添加自己的方法

}

abstract class BasePresenter<I, V>(iView: I, view: V) : KBasePresenter<I, V>(iView, view) {
 //重写或者添加自己的方法
}
```

## License
```text
    Copyright 2018 nicolite

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 ```