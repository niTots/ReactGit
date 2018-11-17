package com.example.fox.reactgit.arch.ui.base.rv

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<Item,VH:RecyclerView.ViewHolder>  : RecyclerView.Adapter<VH>(){

    protected val list = arrayListOf<Item>()
    protected lateinit var clickListener:OnItemClickedListener<Item>

    fun setList(list:List<Item>,notify:Boolean = false){
        this.list.addAll(list)
        if (notify) notifyDataSetChanged()
    }



    open fun setListener(listener: OnItemClickedListener<Item>) = apply{
        this.clickListener = listener
    }
}