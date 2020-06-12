package com.ag04.githubapp.components.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.BaseFragment
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.databinding.FragmentRepositoryDetailsBinding
import com.ag04.githubapp.di.PresenterInjector
import com.bumptech.glide.Glide

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryFragment :
    BaseFragment<RepositoryContract.View, RepositoryContract.Presenter>(),
    RepositoryContract.View {

    private lateinit var presenter: RepositoryContract.Presenter
    private lateinit var binding: FragmentRepositoryDetailsBinding

    override fun providePresenter(): RepositoryContract.Presenter {
        return presenter
    }

    override fun provideResourceView(inflater: LayoutInflater): View {
        binding = FragmentRepositoryDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onPostViewCreate(view: View) {
        super.onPostViewCreate(view)

        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }

        binding.linearOwnerDetails.setOnClickListener {
            presenter.onRepositoryOwnerClick()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        activity?.let {
            val intent = it.intent
            presenter = PresenterInjector.provideRepositoryPresenter(
                intent.getStringExtra(RepositoryActivity.USER_LOGIN_EXTRA)!!,
                intent.getStringExtra(RepositoryActivity.REPO_NAME_EXTRA)!!
            )
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun showLoadingIndicator(show: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = show
    }

    override fun showDetails(show: Boolean) {
        binding.scrollDetails.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    override fun onRepositoryLoaded(repository: Repository) {
        binding.textRepositoryName.text = repository.name
        binding.textRepositoryFullName.text = repository.fullName
        binding.textRepositoryDescription.text = repository.description
        binding.textRepositoryUrl.text = repository.htmlUrl
        binding.textRepositoryLanguage.text = getString(
            R.string.repository_language,
            repository.language
        )

        Glide.with(context!!)
            .load(repository.user.avatarUrl)
            .circleCrop()
            .into(binding.imageOwnerAvatar)

        binding.textOwnerName.text = repository.user.login
        binding.textWatchersCount.text = repository.watchersCount.toString()
        binding.textForksCount.text = repository.forksCount.toString()
        binding.textOpenIssuesCount.text = repository.openIssuesCount.toString()
        binding.textCreated.text = getString(
            R.string.repository_created,
            repository.createdAt
        )
        binding.textLastUpdated.text = getString(
            R.string.repository_last_updated,
            repository.updatedAt
        )
    }
}