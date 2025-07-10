package ru.mavrinvladislav.shifttask2025.core.common.util

class UnAuthorizedException(override val message: String?) : Error(message)

class AuthorizationException(override val message: String?) : Error(message)
