package com.ydc.mvp.model

import com.ydc.mvp.contract.IModelContract

/**
 * @Description
 * @Author ydc
 * @CreateDate 2019/11/25
 * @Version 1.0
 */
abstract class BaseModel : IBaseModel, IModelContract {

    override fun onCreate() {

    }

    override fun onDestroy() {

    }

}
