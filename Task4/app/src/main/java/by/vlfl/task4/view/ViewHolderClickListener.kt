package by.vlfl.task4.view

import by.vlfl.task4.model.db.RateEntity

interface ViewHolderClickListener {
    fun onItemClicked(item: RateEntity)
}