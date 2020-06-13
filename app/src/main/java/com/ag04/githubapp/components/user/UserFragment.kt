package com.ag04.githubapp.components.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ag04.githubapp.components.base.details.BaseDetailsFragment
import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.databinding.FragmentUserDetailsBinding
import com.ag04.githubapp.di.PresenterInjector
import com.bumptech.glide.Glide

/**
 * Created by akovar on 08/06/2020.
 */
class UserFragment :
    BaseDetailsFragment<User, UserContract.View<User>, UserContract.Presenter<User>>(),
    UserContract.View<User> {

    private lateinit var presenter: UserContract.Presenter<User>
    private lateinit var binding: FragmentUserDetailsBinding

    override fun providePresenter(): UserContract.Presenter<User> {
        return presenter
    }

    override fun provideResourceView(inflater: LayoutInflater): View {
        binding = FragmentUserDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        activity?.let {
            val intent = it.intent
            presenter = PresenterInjector.provideUserPresenter(
                intent.getStringExtra(UserActivity.USER_ID_EXTRA)!!
            )
            super.onActivityCreated(savedInstanceState)
        }
    }

    override fun showLoadingProgress(show: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = show
    }

    override fun showDetails(show: Boolean) {
        binding.scrollDetails.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    override fun setItem(item: User) {
        Glide.with(context!!)
            .load(item.avatarUrl)
            .circleCrop()
            .into(binding.imageUserAvatar)

        binding.textUserName.text = item.login

        binding.textUserCompany.text = item.company
        binding.textUserCompany.visibility =
            if (item.company.isNullOrBlank()) View.GONE else View.VISIBLE

        binding.textUserEmail.text = item.email
        binding.textUserEmail.visibility =
            if (item.email.isNullOrBlank()) View.GONE else View.VISIBLE

        binding.textUserBio.text = item.bio
        binding.textUserBio.visibility =
            if (item.bio.isNullOrBlank()) View.GONE else View.VISIBLE

        binding.textUserFollowersCount.text = item.followers.toString()
        binding.textUserFollowingCount.text = item.following.toString()

        binding.textUserUrl.text = item.htmlUrl
    }
}