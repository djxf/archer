package cn.nicolite.archer.kBase

import cn.nicolite.mvp.kBase.KBasePresenter

/**
 * Created by nicolite on 2018/5/20.
 * email nicolite@nicolite.cn
 */
abstract class BasePresenter<I, V>(iView: I, view: V) : KBasePresenter<I, V>(iView, view) {

}