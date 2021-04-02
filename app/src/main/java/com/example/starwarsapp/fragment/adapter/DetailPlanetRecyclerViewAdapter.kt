package com.example.starwarsapp.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.databinding.ItemPlanetsBinding
import com.example.starwarsapp.network.models.Planet

class DetailPlanetRecyclerViewAdapter (private val planet:HashMap<Int,Planet>,
                                        private val planetsID:ArrayList<Int>) : RecyclerView.Adapter<DetailPlanetRecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(private val binding: ItemPlanetsBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bindViewHolder(planet:String){
            binding.itemPlanetName.text = "# ${planet}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlanetsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val term = planetsID[position]
        val key = getPage(term)
        if(planet.containsKey(key)){
            holder.bindViewHolder(planet.get(key)!!.results.get(term%10).name)
        }
    }

    override fun getItemCount(): Int {
        return planetsID.size
    }
    fun getPage(i:Int):Int{
        val result: Int
        if (i<=10){
            result = 1
        }else if (i>10 && i<=20){
            result = 2
        }else if (i>20 && i<=30){
            result = 3
        }else if(i>30 && i<=40){
            result = 4
        }else if (i>40 && i<=50){
            result = 5
        }else if (i>50 && i<=60){
            result = 6
        }
        else{
            result = -1
        }
        return result;
    }
}