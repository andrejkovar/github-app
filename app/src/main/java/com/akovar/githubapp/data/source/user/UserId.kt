package com.akovar.githubapp.data.source.user

/**
 * Created by akovar on 16/06/2020.
 */
sealed class UserId

object UserMe : UserId()

class UserById(val id: String) : UserId()