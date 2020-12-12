package by.vlfl.task7_mvvm.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.vlfl.domain.model.Rate

@BindingAdapter(value = ["bindRates"])
fun bindRates(view: RecyclerView, items: MutableList<Rate>) {
    if (view.adapter == null) {
        view.apply {
            adapter = CustomAdapter()
            val mLayoutManager =  LinearLayoutManager(context)
            layoutManager = mLayoutManager

            val mDividerItemDecoration = DividerItemDecoration(
                context,
                mLayoutManager.orientation
            )
            addItemDecoration(mDividerItemDecoration)
        }
    }
    (view.adapter as CustomAdapter).replaceAll(items)
}