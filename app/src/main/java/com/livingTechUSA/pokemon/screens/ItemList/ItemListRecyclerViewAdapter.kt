package com.livingTechUSA.pokemon.screens.ItemList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.livingTechUSA.pokemon.databinding.ItemListContentBinding
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.util.Constant

class ItemListRecyclerViewAdapter(
    private val pokemon: MutableList<Pokemon>,
    private val itemDetailFragmentContainer: View?,
    private val mItemSelectListener: ListItemSelectListener<Pokemon>
) : RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context

    private var mIsLoaderVisible: Boolean = false
    private var savedPokemonList: MutableList<Pokemon> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            ItemListContentBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(context, binding)

    }

    override fun getItemCount() = pokemon.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }


    override fun getItemViewType(position: Int): Int =
        if (mIsLoaderVisible && position == pokemon.size - 1) {
            Constant.RECYCLER_VIEW_TYPE_LOADING
        } else {
            Constant.RECYCLER_VIEW_TYPE_NORMAL
        }


    fun updateList(pokemonList: List<Pokemon>) {
        pokemon.clear()
        pokemon.addAll(pokemonList)
        notifyDataSetChanged()
        /*TODO: Address glich with diffCallBack causing crash:
        java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionViewHolder{3...
        ...due to the hashMap being recreated by the navGraph with each return to prior
        screen resulting in discrepencies when calculating recyclerview positions.
         */
//        val diffCallBack = ArticleListDiffUtil.PatientListDiffCallback(savedArticleList, articles)
//        val diffResult = DiffUtil.calculateDiff(diffCallBack)
//        diffResult.dispatchUpdatesTo(this)
        savedPokemonList.clear()
        savedPokemonList.addAll(pokemon)

    }


    fun clearPokemon() {
        pokemon.clear()
    }


    class PokemonListDiffUtil(
        private val oldList: List<Pokemon>,
        private val newList: List<Pokemon>
    ) {
        //Only updates changes rather than redrawing the entire list.
        open class PatientListDiffCallback(
            private val oldList: List<Pokemon>,
            private val newList: List<Pokemon>
        ) : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].name == newList[newItemPosition].name
            }

            //Called if areItemsTheSame == true
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].name == newList[newItemPosition].name
            }
        }

    }

    inner class ViewHolder(context: Context, val binding: ItemListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.Name
         fun onBind(position: Int) {
            val pokemon = pokemon[position]
            title.text = pokemon.name
            itemView.setOnClickListener { v: View ->
                pokemon.let {
                    mItemSelectListener.onSelect(it)
                }
            }
        }
    }

    interface ListItemSelectListener<T> {
        fun onSelect(item: T)
    }

}