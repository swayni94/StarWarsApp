package com.example.starwarsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.starwarsapp.AppAplication
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.FragmentMainStarwarsBinding
import com.example.starwarsapp.fragment.adapter.MainFragmentRecyclerViewAdapter
import com.example.starwarsapp.fragment.helper.MainRecyclerItemClick
import com.example.starwarsapp.network.models.Results
import com.example.starwarsapp.viewmodel.MainViewModel
import com.example.starwarsapp.viewmodel.viewModelProvider
import javax.inject.Inject


class MainFragment : Fragment(), MainRecyclerItemClick {

    private val appComponents by lazy { AppAplication.appComponent }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private fun getViewModel(): MainViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private var _binding: FragmentMainStarwarsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appComponents.inject(this)
        _binding = FragmentMainStarwarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getViewModel().fecthFilmsDatas()
    }
    override fun onResume() {
        super.onResume()

        getViewModel().filmsData?.observe(this, {
            setAdapter(it)
        })

        binding.mainFragmentSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                                             androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { text->
                    getViewModel().setSearchQuery(text)
                    getViewModel().searchFilms?.observe(viewLifecycleOwner, {
                        setAdapter(it)
                    })
                }
                return true
            }
        })
    }

    private fun setAdapter(datas:List<Results>){
        val adapter = MainFragmentRecyclerViewAdapter(datas, this)
        binding.mainFragmentRecyclerView.layoutManager = GridLayoutManager(activity,2)
        binding.mainFragmentRecyclerView.adapter = adapter
    }

    override fun itemClick(url: String, likeCount:String, image:Int, planetId:ArrayList<Int>) {
        val navController = Navigation.findNavController(requireActivity(), R.id.fragment_view_main_container)
        val args=Bundle()
        args.putString("url",url)
        args.putString("like",likeCount)
        args.putInt("image", image)
        args.putIntegerArrayList("planetId", planetId)
        navController.navigate(R.id.detailFragment_navigation, args)
    }

}