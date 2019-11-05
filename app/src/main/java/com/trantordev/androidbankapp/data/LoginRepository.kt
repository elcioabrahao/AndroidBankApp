package com.trantordev.androidbankapp.data

class LoginRepository private constructor(
    private val loginDao: LoginDao
) {

    suspend fun createLogin(user: String, password: String) {
        val login = Login(user,password)
        loginDao.insertUserInfo(login)
    }

    suspend fun removeLogin(login: Login) {
        loginDao.deleteUserInfo(login)
    }

    fun isCached(user: String) =
        loginDao.isCached(user)

    fun getUserInfo() = loginDao.getUserInfo()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: LoginRepository? = null

        fun getInstance(loginDao: LoginDao) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(loginDao).also { instance = it }
            }
    }
}