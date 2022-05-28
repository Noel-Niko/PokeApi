package com.livingTechUSA.pokemon.screens.ItemDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.livingTechUSA.pokemon.databinding.AbilityListContentBinding
import com.livingTechUSA.pokemon.models.Ability
import com.livingTechUSA.pokemon.util.Constant

class AbilityListRecyclerViewAdapter(
    private val ability: MutableList<Ability>
) : RecyclerView.Adapter<AbilityListRecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context

    private var mIsLoaderVisible: Boolean = false
    private var savedAbilityList: MutableList<Ability> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            AbilityListContentBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(context, binding)

    }

    override fun getItemCount() = ability.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }


    override fun getItemViewType(position: Int): Int =
        if (mIsLoaderVisible && position == ability.size - 1) {
            Constant.RECYCLER_VIEW_TYPE_LOADING
        } else {
            Constant.RECYCLER_VIEW_TYPE_NORMAL
        }


    fun updateList(abilityList: List<Ability>) {
        ability.clear()
        ability.addAll(abilityList)
        notifyDataSetChanged()
        /*TODO: Address glich with diffCallBack causing crash:
        java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionViewHolder{3...
        ...due to the hashMap being recreated by the navGraph with each return to prior
        screen resulting in discrepencies when calculating recyclerview positions.
         */
//        val diffCallBack = ArticleListDiffUtil.PatientListDiffCallback(savedArticleList, articles)
//        val diffResult = DiffUtil.calculateDiff(diffCallBack)
//        diffResult.dispatchUpdatesTo(this)
        savedAbilityList.clear()
        savedAbilityList.addAll(ability)

    }


    fun clearAbility() {
        ability.clear()
    }


    class AbilityListDiffUtil(
        private val oldList: List<Ability>,
        private val newList: List<Ability>
    ) {
        //Only updates changes rather than redrawing the entire list.
        open class PatientListDiffCallback(
            private val oldList: List<Ability>,
            private val newList: List<Ability>
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

    inner class ViewHolder(context: Context, val binding: AbilityListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var abilityName: TextView = binding.abilityName
        var abilityDetails: TextView = binding.detailsLink



         fun onBind(position: Int) {
            val ability1 = ability[position]
            abilityName.text = ability1.name
             abilityDetails.text = ability1.url
             binding.detailsLink.setOnClickListener {
                 val text: String = binding.detailsLink.text as String
                 val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(text))
                 startActivity(context, browserIntent, null)
             }
        }
    }

}