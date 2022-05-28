package com.livingTechUSA.pokemon.screens.SavedList

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.livingTechUSA.pokemon.R
import com.livingTechUSA.pokemon.databinding.FragmentSavedListBinding
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.screens.ItemList.*
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

/**
 * A fragment representing a list of Items.
 */
class SavedListFragment : Fragment(), SavedListView, CoroutineScope, KoinComponent {
    val appDispatcher: IAppDispatchers by inject()
    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = appDispatcher.io() + job


    private lateinit var presenter: SavedListPresenter
    private lateinit var itemListAdapter: SavedListRecyclerViewAdapter
    private var pokemonList = mutableListOf<Pokemon>()
    private var _binding: FragmentSavedListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedListBinding.inflate(inflater, container, false)
        initPresenter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView? = binding.itemList
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)
        itemListAdapter = SavedListRecyclerViewAdapter(
            pokemonList,  itemDetailFragmentContainer,
            object : SavedListRecyclerViewAdapter.ListItemSelectListener<Pokemon> {
                override fun onSelect(item: Pokemon) {
                    selectItem(item)
                }
            })
        if (recyclerView != null) {
            setupRecyclerView(recyclerView)
        }
        presenter.onCreated()
    }


    private fun selectItem(item: Pokemon) {
        val action =
            SavedListFragmentDirections.actionSavedListFragmentToItemDetailFragment(item)
        if (action.arguments != null) {
            view?.findNavController()?.navigate(action)
        }

    }

    override fun initPresenter(): SavedListPresenter {
        presenter = SavedListPresenter(this, SavedListModel())
        return presenter
    }


    private fun setupRecyclerView(
        recyclerView: RecyclerView
    ) {
        recyclerView.adapter = itemListAdapter
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.itemList?.layoutManager = linearLayoutManager
        binding.itemList?.adapter = itemListAdapter
    }


    override suspend fun showPokemon(pokemonList: List<Pokemon>) {
        updateList(pokemonList)
    }



    override fun showNoPokemonFound(show: Boolean) {
        if (show) {
            Toast.makeText(context, "No Pokemon found for search criteria.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun navigateToPokemonDetail(pokemon: Pokemon) {
        val action =
            ItemListFragmentDirections.showItemDetail(pokemon)
        this.findNavController().navigate(action)
    }

    override suspend fun updateList(pokemonList: List<Pokemon>) {
        launch(appDispatcher.ui()) { binding.progressBar?.visibility = View.VISIBLE }
        coroutineScope {
            val job = launch {
                itemListAdapter.clearPokemon()
            }
            job.join()
            launch(appDispatcher.ui()) {
                itemListAdapter.updateList(pokemonList)
                showNoPokemonFound(pokemonList.isEmpty())
            }
            launch(appDispatcher.ui()) { binding.progressBar?.visibility = View.GONE }
        }
    }


//
//    override fun showSearchViewEndDrawable(show: Boolean) {
//        if(show){
//            binding.customToobar?.searchView?.visibility = View.VISIBLE
//        }else {
//            binding.customToobar?.searchView?.visibility = View.GONE
//        }
//
//    }
//
//    /**
//     * Extension function for showing clear text drawable in EditText
//     * */
//    private fun AppCompatEditText.showEndDrawable(show: Boolean) {
//        when {
//            show -> setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_edittext, 0)
//            else -> setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
//        }
//    }
//
//    /**
//     * Shows/Hides search view
//     * */
//    private fun showSearchView(show: Boolean) {
//        launch(appDispatcher.ui()) {
//                if (show){
//                    binding.customToobar?.articleSearchView?.visibility = View.VISIBLE
//                    binding.customToobar?.searchView?.visibility = View.GONE
//                } else View.GONE
//            if (!show) {
//                presenter.clearSearchText()
//                binding.customToobar?.articleSearchView?.setText("")
//                binding.customToobar?.articleSearchView?.visibility = View.GONE
//                binding.customToobar?.searchView?.visibility = View.VISIBLE
//            }
//            presenter.setSearchSelected(show)
//        }
//    }
//
//    /**
//     * invalidateOptionsMenu() calls onCreateOptionsMenu() and resets the search bar as well as the menu icon
//     * */
//    override fun clearSearchTextIfAny() {
//        showSearchView(false)
//        activity?.invalidateOptionsMenu()
//    }
//
//
//    override fun setSearchQueryTextListener() {
//        binding.customToobar?.articleSearchView?.doAfterTextChanged {
//            presenter.onSearchQueryChange(
//                it?.toString() ?: ""
//            )
//        }
//    }
//
//    override fun showErrorMessage(show: Boolean, message: String) {
//        binding.errorMessage?.apply {
//            isVisible = show
//            text = message
//        }
//    }
//    /**
//     * Extension function for clearing search in EditText
//     * */
//    @SuppressLint("ClickableViewAccessibility")
//    private fun AppCompatEditText.setRightDrawableOnTouchListener(func: AppCompatEditText.() -> Unit) {
//        setOnTouchListener { view, event ->
//            var isClicked = false
//            if (event.action == MotionEvent.ACTION_UP) {
//                val drawable = compoundDrawables[2]
//                drawable?.let {
//                    if (event.rawX >= (right - drawable.bounds.width())) {
//                        func()
//                        isClicked = true
//                    }
//                }
//            }
//            isClicked
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}