package by.vlfl.task4_moxy.view

import by.vlfl.task4_moxy.model.db.RateEntity

interface ViewHolderClickListener {
    fun onItemClicked(item: RateEntity)
}