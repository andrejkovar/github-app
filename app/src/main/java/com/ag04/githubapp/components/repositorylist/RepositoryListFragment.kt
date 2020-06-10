package com.ag04.githubapp.components.repositorylist

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import android.view.View
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.adapter.BaseRecyclerViewAdapter
import com.ag04.githubapp.components.base.searchlist.BaseSearchListFragment
import com.ag04.githubapp.data.model.Repository
import com.ag04.githubapp.databinding.LayoutRepositorySortBinding

/**
 * Created by akovar on 08/06/2020.
 */
class RepositoryListFragment :
    BaseSearchListFragment<Repository, SearchContract.View<Repository>, SearchContract.Presenter<Repository>>(),
    SearchContract.View<Repository> {

    // TODO inject this
    private val presenter: SearchContract.Presenter<Repository> = RepositoryListPresenter()
    private lateinit var adapter: BaseRecyclerViewAdapter<Repository, RepositoryListAdapter.ViewHolder>

    // sort dialog view properties
    private lateinit var sortDialog: AlertDialog
    private lateinit var sortDialogBinding: LayoutRepositorySortBinding

    private lateinit var onRepositoryClickListener: OnRepositoryClickListener

    override fun providePresenter(): SearchContract.Presenter<Repository> {
        return presenter
    }

    override fun provideResourceViewId(): Int {
        return R.layout.fragment_repository_list
    }

    override fun onPostViewCreate(view: View) {
        super.onPostViewCreate(view)
        initSortDialog()
        initAdapter()
    }

    override fun setItems(items: List<Repository>?) {
        super.setItems(items)
        adapter.items = items
    }

    override fun showSortDialog(sort: RepositorySearchSort) {
        if (sortDialog.isShowing) {
            sortDialog.dismiss()
        }

        sortDialogBinding.checkboxStars.isChecked = sort.stars
        sortDialogBinding.checkboxForks.isChecked = sort.forks
        sortDialogBinding.checkboxUpdate.isChecked = sort.updated

        sortDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        return if (itemId == R.id.action_sort) {
            presenter.onSortClick()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun initSortDialog() {
        sortDialogBinding = LayoutRepositorySortBinding.inflate(layoutInflater)

        sortDialogBinding.buttonSubmit.setOnClickListener {
            presenter.onSortSubmit(
                RepositorySearchSort(
                    stars = sortDialogBinding.checkboxStars.isChecked,
                    forks = sortDialogBinding.checkboxForks.isChecked,
                    updated = sortDialogBinding.checkboxUpdate.isChecked
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

    private fun initAdapter() {
        onRepositoryClickListener = object : OnRepositoryClickListener {

            override fun onClick(item: Repository) {
                presenter.onItemClick(item)
            }

            override fun onAvatarClick(item: Repository) {
                presenter.onItemImageClick(item)
            }
        }

        adapter = RepositoryListAdapter().apply {
            onItemClickListener = onRepositoryClickListener
        }
    }
}