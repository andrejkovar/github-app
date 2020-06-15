package com.akovar.githubapp.client

/**
 * Created by akovar on 15/06/2020.
 */
abstract class Credentials {

    /**
     * Get the credentials.
     *
     * @return the credentials and it should not be null
     */
    abstract fun getCredentials(): String
}