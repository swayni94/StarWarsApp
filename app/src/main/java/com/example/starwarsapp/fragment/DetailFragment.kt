package com.example.starwarsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.starwarsapp.AppAplication
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.FragmentDetailBinding
import com.example.starwarsapp.fragment.adapter.DetailPlanetRecyclerViewAdapter
import com.example.starwarsapp.network.models.Planet
import com.example.starwarsapp.viewmodel.DetailViewModel
import com.example.starwarsapp.viewmodel.viewModelProvider
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment : Fragment() , View.OnClickListener{

    private val appComponents by lazy { AppAplication.appComponent }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private fun getViewModel(): DetailViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var planetsID: ArrayList<Int>
    private lateinit var url:String
    private lateinit var likeCount:String
    private var imageInt:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString("url") as String
            imageInt = it.getInt("image")
            likeCount = it.getString("like") as String
            planetsID = it.getIntegerArrayList("planetId") as ArrayList<Int>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appComponents.inject(this)
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onStart() {
        super.onStart()
        getViewModel().fectPlanets(getPage())
        getViewModel().fectFilmDatas(url)
    }

    override fun onResume() {
        super.onResume()
        getViewModel().film!!.observe(this, {
            Picasso.get().load(imageInt).resize(300,300).into(binding.fragmentDetailImageView)
            binding.filmName.text = it.title
            binding.releaseDate.text = it.release_date.replace("-",".")
            binding.directorName.text = it.director
            binding.likeCount.text = likeCount
            binding.fragmentDetailDescreption.text = it.opening_crawl

            getViewModel().planetLiveData.observe(this, {
                setAdapter(it, planetsID)
            })
        })

        binding.backButton.setOnClickListener(this)
    }



    fun setAdapter(planet:HashMap<Int,Planet>, planetsID:ArrayList<Int>){
        val adapter = DetailPlanetRecyclerViewAdapter(planet, planetsID)
        binding.fragmentDetailRecyclerview.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.fragmentDetailRecyclerview.adapter = adapter
    }

    fun getPage():ArrayList<Int>{
        val result = ArrayList<Int>()
        for (i in planetsID){
            if (i<=10){
                result.add(1)
            }else if (i>10 && i<=20){
                result.add(2)
            }else if (i>20 && i<=30){
                result.add(3)
            }else if(i>30 && i<=40){
                result.add(4)
            }else if (i>40 && i<=50){
                result.add(5)
            }else if (i>50 && i<=60){
                result.add(6)
            }
            else{
                result.add(-1)
            }
        }
        return result;
    }

    override fun onClick(v: View?) {
        val navController = Navigation.findNavController(requireActivity(), R.id.fragment_view_main_container)
        navController.navigate(R.id.mainFragment_navigation)
    }
}