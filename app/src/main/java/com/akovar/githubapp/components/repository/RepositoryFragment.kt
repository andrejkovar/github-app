package com.akovar.githubapp.components.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.akovar.githubapp.R
import com.akovar.githubapp.components.base.BaseFragment
import com.akovar.githubapp.components.user.UserActivity
import com.akovar.githubapp.data.model.Repository
import com.akovar.githubapp.data.model.User
import com.akovar.githubapp.databinding.FragmentRepositoryDetailsBinding
import com.akovar.githubapp.di.PresenterProvider
import com.bumptech.glide.Glide

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryFragment :
    BaseFragment<
            RepositoryContract.View<Repository>,
            RepositoryContract.Presenter<Repository, RepositoryContract.View<Repository>>>(),
    RepositoryContract.View<Repository> {

    /**
     * Repository presenter holder.
     */
    private lateinit var presenter: RepositoryContract.Presenter<
            Repository,
            RepositoryContract.View<Repository>>

    /**
     * Layout binding holder for this fragment.
     */
    private var binding: FragmentRepositoryDetailsBinding? = null
    private fun binding(): FragmentRepositoryDetailsBinding = binding!!

    override fun providePresenter(): RepositoryContract.Presenter<
            Repository,
            RepositoryContract.View<Repository>> {
        return presenter
    }

    override fun provideResourceView(inflater: LayoutInflater): View {
        binding = FragmentRepositoryDetailsBinding.inflate(inflater)
        return binding().root
    }

    override fun onPostViewCreate(view: View) {
        super.onPostViewCreate(view)

        binding().swipeRefreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }

        binding().linearOwnerDetails.setOnClickListener {
            presenter.onRepositoryOwnerClick()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        activity?.let {
            val intent = it.intent
            presenter = PresenterProvider.provideRepositoryPresenter(
                intent.getParcelableExtra(RepositoryActivity.REPOSITORY_PAIR_ID_EXTRA)!!
            )
            super.onActivityCreated(savedInstanceState)
        }
    }

    override fun showDetails(show: Boolean) {
        binding().scrollDetails.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    override fun showLoadingProgress(show: Boolean) {
        binding().swipeRefreshLayout.isRefreshing = show
    }

    override fun setItem(item: Repository) {
        binding().textRepositoryName.text = item.name
        binding().textRepositoryFullName.text = item.fullName
        binding().textRepositoryDescription.text = item.description
        binding().textRepositoryUrl.text = item.htmlUrl
        binding().textRepositoryLanguage.text = getString(
            R.string.text_repository_language,
            item.language
        )

        Glide.with(requireContext())
            .load(item.user.avatarUrl)
            .circleCrop()
            .into(binding().imageOwnerAvatar)

        binding().textOwnerName.text = item.user.login
        binding().textWatchersCount.text = item.watchersCount.toString()
        binding().textForksCount.text = item.forksCount.toString()
        binding().textOpenIssuesCount.text = item.openIssuesCount.toString()
        binding().textCreated.text = getString(
            R.string.text_repository_created,
            item.createdAt
        )
        binding().textLastUpdated.text = getString(
            R.string.text_repository_last_updated,
            item.updatedAt
        )
    }

    override fun navigateToUserDetails(user: User) {
        UserActivity.open(context, user.login)
    }

    override fun destroyViewBinding() {
        binding = null
    }
}