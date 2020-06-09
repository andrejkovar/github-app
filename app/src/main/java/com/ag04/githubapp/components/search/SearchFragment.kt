package com.ag04.githubapp.components.search

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import com.ag04.githubapp.R
import com.ag04.githubapp.components.base.searchlist.BaseSearchListFragment
import com.ag04.githubapp.data.model.Repository

/**
 * Created by akovar on 08/06/2020.
 */
class SearchFragment :
    BaseSearchListFragment<Repository, SearchContract.View<Repository>, SearchContract.Presenter<Repository>>(),
    SearchContract.View<Repository> {

    private val presenter: SearchContract.Presenter<Repository> = SearchPresenter()

    // sort dialog view properties
    private lateinit var sortDialog: AlertDialog
    private lateinit var checkBoxStars: CheckBox
    private lateinit var checkBoxForks: CheckBox
    private lateinit var checkBoxUpdated: CheckBox
    private lateinit var buttonSubmitSort: Button

    override fun providePresenter(): SearchContract.Presenter<Repository> {
        return presenter
    }

    override fun onPostViewCreate(view: View) {
        super.onPostViewCreate(view)

        val sortDialogView = layoutInflater.inflate(R.layout.layout_repository_sort, null)

        // bind sort checkboxes
        checkBoxStars = sortDialogView.findViewById(R.id.checkbox_stars)
        checkBoxForks = sortDialogView.findViewById(R.id.checkbox_forks)
        checkBoxUpdated = sortDialogView.findViewById(R.id.checkbox_update)

        // bind sort submit button
        buttonSubmitSort = sortDialogView.findViewById(R.id.button_submit)
        buttonSubmitSort.setOnClickListener {
            presenter.onSortSubmit(
                RepositorySearchSort(
                    stars = checkBoxStars.isChecked,
                    forks = checkBoxForks.isChecked,
                    updated = checkBoxUpdated.isChecked
                )
            )

            sortDialog.dismiss()
        }

        // build sort dialog
        sortDialog = AlertDialog.Builder(context)
            .setView(sortDialogView)
            .setCancelable(true)
            .create()

        sortDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
    }

    override fun showSortDialog(sort: RepositorySearchSort) {
        if (sortDialog.isShowing) {
            sortDialog.dismiss()
        }

        checkBoxStars.isChecked = sort.stars
        checkBoxForks.isChecked = sort.forks
        checkBoxUpdated.isChecked = sort.updated

        sortDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        return if (itemId == R.id.action_sort) {
            presenter.onSortClick()
            true
        } else super.onOptionsItemSelected(item)
    }
}