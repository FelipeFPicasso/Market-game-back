package com.marketgame.exception

class AuthenticationException(override val message: String, val errorCode: String) :Exception()