package by.vlfl.task6.view

import by.vlfl.task6.model.db.RateEntity

interface ViewHolderClickListener {
    fun onItemClicked(item: RateEntity)
}