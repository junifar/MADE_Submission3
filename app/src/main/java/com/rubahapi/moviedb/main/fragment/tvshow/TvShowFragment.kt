package com.rubahapi.moviedb.main.fragment.tvshow


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.rubahapi.moviedb.DetailMovieActivity
import com.rubahapi.moviedb.R
import com.rubahapi.moviedb.adapter.MovieAdapter
import com.rubahapi.moviedb.adapter.TvShowAdapter
import com.rubahapi.moviedb.api.ApiRepository
import com.rubahapi.moviedb.main.fragment.movie.MoviePresenter
import com.rubahapi.moviedb.main.fragment.movie.MoviewFragmentViewModel
import com.rubahapi.moviedb.model.TvShow
import com.rubahapi.moviedb.util.invisible
import com.rubahapi.moviedb.util.visible
import com.rubahapi.moviedb.viewmodel.TVShowViewModel

class TvShowFragment : Fragment(), TVShowView {
    private var items: MutableList<TvShow> = mutableListOf()
    private lateinit var adapter: TvShowAdapter
    private lateinit var progressBar: ProgressBar
//    private lateinit var presenter: TvShowPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var list:RecyclerView
    private lateinit var tvShowViewModel: TVShowViewModel

    private val getTvShow:Observer<ArrayList<TvShow>> = Observer {
        tvShowItems ->
        if(tvShowItems != null){
            adapter.setData(tvShowItems)
            hideLoading()
        }
    }

    private fun initComponent(){
        progressBar = activity?.findViewById(R.id.progressBar) as ProgressBar
        swipeRefresh = activity?.findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout

        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
        tvShowViewModel.listTvShow.observe(this, getTvShow)

        adapter = TvShowAdapter(context!!)
        adapter.notifyDataSetChanged()

//        adapter = TvShowAdapter(context!!, items){
//            val intent = Intent(activity, DetailMovieActivity::class.java)
//            intent.putExtra(
//                DetailMovieActivity.EXTRA_DETAIL_ACTIVITY_TYPE,
//                DetailMovieActivity.EXTRA_DETAIL_TV_SHOW
//            )
////            intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_TV_SHOW, it)
//            startActivity(intent)
//        }
        list.adapter = adapter


//        val request = ApiRepository()
//        val gson = Gson()
//        presenter = TvShowPresenter(this, request, gson)
//        onAttachView()
//        presenter.getTvShow()

        swipeRefresh.setOnRefreshListener {
//            presenter.getTvShow()
            tvShowViewModel.setListTvShow()
//            showLoading()
            hideLoading()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_show, container, false)
        list = view.findViewById(R.id.recycler_view_tv_show)

        list.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun onAttachView() {
//        presenter.onAttach(this)
    }

    override fun onDetachView() {
//        presenter.onDetach()
    }

    override fun showTvShow(data: List<TvShow>) {
        swipeRefresh.isRefreshing = false
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object{
        @JvmStatic
        fun newInstance(): TvShowFragment {
            return TvShowFragment().apply {
                arguments = Bundle().apply {}
            }
        }

    }

}
