package cn.nicolite.archer.jBase;


import cn.nicolite.mvp.jBase.JBasePresenter;

/**
 * Presenter基类  所有Persenter都要继承此类
 * Created by nicolite on 17-10-13.
 */

public abstract class BasePresenter<I, V> extends JBasePresenter<I, V> {

    public BasePresenter(I iView, V view) {
        super(iView, view);
    }
}
