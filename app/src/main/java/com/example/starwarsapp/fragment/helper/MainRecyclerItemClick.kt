package com.example.starwarsapp.fragment.helper

interface MainRecyclerItemClick {
    fun itemClick(url:String, likeCount:String, image:Int, planetId:ArrayList<Int>)
}