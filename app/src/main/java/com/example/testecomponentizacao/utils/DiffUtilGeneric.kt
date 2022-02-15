package com.example.testecomponentizacao.utils

import androidx.recyclerview.widget.DiffUtil

class DiffUtilGeneric<T>(
    private val mOldList: List<T>,
    private val mNewList: List<T>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = mOldList.size

    override fun getNewListSize(): Int = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition] === mNewList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition] == mNewList[newItemPosition]
    }
}