package com.ag04.githubapp.components.repositorylist

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.adapter.BaseRecyclerViewAdapter
import com.ag04.githubapp.components.base.searchlist.BaseSearchListFragment
import com.ag04.githubapp.components.repository.RepositoryActivity
import com.ag04.githubapp.components.repository.RepositoryPairId
import com.ag04.githubapp.components.user.UserActivity
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.data.model.User
import com.ag04.githubapp.databinding.FragmentRepositoryListBinding
import com.ag04.githubapp.databinding.LayoutRepositorySortBinding
import com.ag04.githubapp.di.PresenterInjector

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryListFragment :
    BaseSearchListFragment<
            Repository,
            RepositoryListContract.View,
            RepositoryListContract.Presenter>(),
    RepositoryListContract.View {

    /**
     * Repository list presenter holder.
     */
    private val presenter: RepositoryListContract.Presenter =
        PresenterInjector.provideRepositoryListPresenter()

    /**
     * Sort dialog view properties.
     */
    private lateinit var sortDialog: AlertDialog
    private lateinit var sortDialogBinding: LayoutRepositorySortBinding

    /**
     * Layout binding holder for this fragment.
     */
    private lateinit var binding: FragmentRepositoryListBinding

    /**
     * Repository list adapter holder.
     */
    private lateinit var adapter: BaseRecyclerViewAdapter<Repository, RepositoryListAdapter.ViewHolder>

    override fun providePresenter(): RepositoryListContract.Presenter {
        return presenter
    }

    override fun provideResourceView(inflater: LayoutInflater): View {
        binding = FragmentRepositoryListBinding.inflate(inflater)
        return binding.root
    }

    override fun onPostViewCreate(view: View) {
        initSortDialog()
        initAdapter()
        super.onPostViewCreate(view)
    }

    override fun setItems(items: List<Repository>?) {
        adapter.items = items
    }

    override fun initRecyclerView() {
        binding.layoutBaseList.recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RepositoryListFragment.adapter
        }
    }

    override fun showSortDialog(sort: RepositorySearchSort) {
        sortDialogBinding.radioButtonStars.isChecked = sort.stars
        sortDialogBinding.radioButtonForks.isChecked = sort.forks
        sortDialogBinding.radioButtonLastUpdated.isChecked = sort.updated

        if (!sortDialog.isShowing) {
            sortDialog.show()
        }
    }

    override fun navigateToRepositoryDetails(repo: Repository) {
        RepositoryActivity.open(context, RepositoryPairId(repo.user.login, repo.name))
    }

    override fun navigateToUserDetails(user: User) {
        UserActivity.open(context, user.login)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        return if (itemId == R.id.action_sort) {
            presenter.onSortClick()
            true
        } else super.onOptionsItemSelected(item)
    }

    /**
     * Creates and setups sort dialog.
     */
    private fun initSortDialog() {
        sortDialogBinding = LayoutRepositorySortBinding.inflate(layoutInflater)

        sortDialogBinding.buttonSubmit.setOnClickListener {
            presenter.onSortSubmit(
                RepositorySearchSort(
                    stars = sortDialogBinding.radioButtonStars.isChecked,
                    forks = sortDialogBinding.radioButtonForks.isChecked,
                    updated = sortDialogBinding.radioButtonLastUpdated.isChecked
                )
            )

            sortDialog.dismiss()
        }

        // build sort dialog
        sortDialog = AlertDialog.Builder(context)
            .setView(sortDialogBinding.root)
            .setCancelable(true)
            .create()

        sortDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
    }

    /**
     * Setups adapter.
     */
    private fun initAdapter() {
        adapter = RepositoryListAdapter().apply {
            onItemClickListener = object : OnRepositoryClickListener {

                override fun onItemClick(item: Repository) {
                    presenter.onItemClick(item)
                }

                override fun onAvatarClick(item: Repository) {
                    presenter.onOwnerAvatarClick(item)
                }
            }
        }
    }
}