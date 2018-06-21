package cn.nicolite.mvp.kBase

/**
 * Created by nicolite on 2018/5/20.
 * email nicolite@nicolite.cn
 */
interface IBaseView {
    fun showLoading()
    fun closeLoading()
    fun showMessage(msg: String)
}