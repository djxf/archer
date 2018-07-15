## Archer

## 目录

* [介绍](#介绍)
* [依赖框架](#依赖框架)
* [使用说明](#使用说明)
* [License](#License)

## 介绍
从原来项目中抽离的mvp快速开发框架，封装了一些常用的功能，比如简化了Activity跳转，
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
   }
```
## 使用说明
使Activity继承KBaseActivity，Fragment继承KBaseFragment，Presenter继承KBasePresenter，你也可以将你的View接口集成KBaseView(非必需)
当然你也可以通过继承这些类实现自己的Base类，这样可以添加自己的一些需求，推荐使用此方式。  

打开Log输出，默认是关闭的，打开后可以看到完整的生命周期调用，便于调试,推荐在Application类中配置，也可以在其他敌方配置  
```kotlin
LogUtils.debug = true
```

基类继承写法:  
kotlin:
```kotlin
abstract class BaseActivity : KBaseActivity() {

}

abstract class BaseFragment : KBaseFragment() {

}

abstract class BasePresenter<I, V>(iView: I, view: V) : KBasePresenter<I, V>(iView, view) {

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