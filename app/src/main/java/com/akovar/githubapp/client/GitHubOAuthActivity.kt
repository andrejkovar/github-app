package com.akovar.githubapp.client

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.akovar.githubapp.BuildConfig
import com.akovar.githubapp.R
import com.akovar.githubapp.di.ApplicationInjector

/**
 * Created by akovar on 15/06/2020.
 */
class GitHubOAuthActivity : AppCompatActivity() {

    private val oAuthClient: AuthClient<GitHubToken, GitHubCredentials> =
        ApplicationInjector.provideAuthClient()

    override fun onResume() {
        super.onResume()

        val url = intent.data
        if (url != null && url.toString().startsWith(BuildConfig.GITHUB_REDIRECT_URL)) {
            val code = url.getQueryParameter("code")
            if (code != null) {
                onCode(code)
            } else {
                onError()
            }
        } else {
            startAuth()
        }
    }

    private fun startAuth() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                BuildConfig.GITHUB_AUTH_URL +
                        "?client_id=" + BuildConfig.GITHUB_CLIENT_ID +
                        "&redirect_uri=" + BuildConfig.GITHUB_REDIRECT_URL
            )
        )

        startActivity(intent)
    }

    private fun onCode(code: String) {
        oAuthClient.authenticate(GitHubCredentials(code))
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_unknown_error), Toast.LENGTH_SHORT).show()
        finish()
    }

    companion object {

        /**
         * Opens GitHubOAuthActivity.
         *
         * @param context context
         */
        fun open(context: Context?) {
            context?.let {
                it.startActivity(Intent(context, GitHubOAuthActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                })
            }
        }
    }
}