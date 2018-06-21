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

在根目录下的build.gradle文件中对应位置添加如下内容
```groovy
buildscript {
    ext.kotlin_version = '1.2.50'
  
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}
```

[![](https://jitpack.io/v/nicolite/archer.svg)](https://jitpack.io/#nicolite/archer)

在app目录下的build.gradle文件中对应位置添加如下内容
```groovy
//在apply plugin: 'com.android.application'后面添加
apply plugin: 'kotlin-android'

dependencies {
  //latestVersion替换为上面徽章后面的数字
    implementation 'com.github.nicolite:archer:latestVersion'

    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    implementation 'com.trello.rxlifecycle2:rxlifecycle:2.2.1'
    implementation 'com.trello.rxlifecycle2:rxlifecycle-components:2.2.1'

    implementation 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'

    implementation 'com.r0adkll:slidableactivity:2.0.6'
   }
```
## 使用说明
如果你使用Java，那么使Activity继承JBaseActivity，Fragment继承JBaseFragment，Presenter继承JBasePresenter，你也可以将你的View接口集成JBaseView(非必需)
如果你使用Kotlin，那么使Activity继承KBaseActivity，Fragment继承KBaseFragment，Presenter继承KBasePresenter，你也可以将你的View接口集成KBaseView(非必需)
当然你也可以通过继承这些类实现自己的Base类，这样可以添加自己的一些需求，推荐使用此方式，需要注意的是，Java和Kotlin继承的类不能使用不同的包中的Base类，也就是说你如果继承的是JBase类型那么配套的Presenter也必须继承JBase类型的  

具体使用可以参考该项目
https://github.com/nicolite/Palm300Heroes

具体方法说明请查看源码，源码中有注释，内容太多就不介绍了

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