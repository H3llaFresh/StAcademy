 package by.vlfl.task7.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.vlfl.domain.model.Rate
import by.vlfl.task7.MainApp
import by.vlfl.task7.R
import by.vlfl.task7.common.BaseFragment
import by.vlfl.task7.di.MainComponent
import by.vlfl.task7.presenter.MainPresenter
import javax.inject.Inject

 class FragmentMain: BaseFragment<MainPresenter>(), MainView, ViewHolderClickListener {
    private var text: TextView? = null
    private var recyclerView: RecyclerView? = null
    var loader: ProgressBar? = null

    lateinit var mainComponent: MainComponent

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent = (activity?.application as MainApp)
            .appComponent.mainComponent().create()
        mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.setView(this)
        val customAdapter = CustomAdapter(viewHolderClickListener = this)
        loader = view.findViewById(R.id.loader)
        text = view.findViewById(R.id.bodyText)
        val aboutButton: ImageView = view.findViewById(R.id.about_button)
        aboutButton.setOnClickListener {
            mPresenter.handleAboutButtonClick()
        }
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = customAdapter
            val mLayoutManager = LinearLayoutManager(context)
            layoutManager = mLayoutManager

            val mDividerItemDecoration = DividerItemDecoration(
                context,
                mLayoutManager.orientation
            )
            addItemDecoration(mDividerItemDecoration)
        }
        mPresenter.loadRates()
    }

    override fun getPresenter(): MainPresenter {
        return mPresenter
    }

    override fun showRates(items: List<Rate>) {
        text?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
        (recyclerView?.adapter as CustomAdapter).replaceAll(items)
    }

    override fun navigateToAboutFragment() {
        val action = FragmentMainDirections.actionFragmentMainToFragmentAbout()
        findNavController().navigate(action)
    }

    override fun showLoading() {
        loader?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loader?.visibility = View.GONE
    }

    override fun showError(throwable: Throwable) {
        text?.visibility = View.VISIBLE
        recyclerView?.visibility = View.GONE
        text?.text = throwable.message
    }

    override fun onItemClicked(item: Rate) {
        val action = FragmentMainDirections.actionFragmentMainToFragmentRateInfo(
            item.curName,
            item.curBelName
        )
        findNavController().navigate(action)
    }
}