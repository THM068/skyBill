package com.sky

import com.sky.BusinessException

/**
 * Created by tm1c14 on 15/04/2016.
 */
class ConnectException extends BusinessException {

    String message

    ConnectException(String message) {
        super(message)
        this.message = message
    }


}
