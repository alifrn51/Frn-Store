package com.frn.frnstore.common

import com.frn.frnstore.R
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.lang.Exception

class FrnExceptionMapper {
    companion object {
        fun map(throwable: Throwable): FrnException {

            if (throwable is HttpException){
                try {

                    val errorJsonObject = JSONObject(throwable.response()?.errorBody()!!.toString())
                    val errorMessage = errorJsonObject.getString("message")
                    return FrnException(if (throwable.code() == 401) FrnException.Type.AUTH else FrnException.Type.SIMPLE , serverMessage = errorMessage)

                }catch (exception:Exception){
                    Timber.e(exception)
                }
            }

            return FrnException(FrnException.Type.SIMPLE , R.string.unknown_error)

        }
    }
}

