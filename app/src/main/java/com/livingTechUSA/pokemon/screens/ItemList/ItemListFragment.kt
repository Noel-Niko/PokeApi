package com.livingTechUSA.pokemon.screens.ItemList

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.livingTechUSA.pokemon.R
import com.livingTechUSA.pokemon.databinding.FragmentItemListBinding
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.util.Ui
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ItemListFragment : Fragment(), ItemListView, CoroutineScope, KoinComponent {
    val appDispatcher: IAppDispatchers by inject()
    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = appDispatcher.io() + job


    private lateinit var presenter: ItemListPresenter
    private lateinit var itemListAdapter: ItemListRecyclerViewAdapter
    private var pokemonList = mutableListOf<Pokemon>()
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        initPresenter()
        val search = binding.searchEditText
        search?.doAfterTextChanged {
            if(search.text.length >= 2) {
                launch(appDispatcher.io()) {
                    val searchResult = presenter.searchByName(search.text.toString().lowercase())
                    val pokeList: MutableList<Pokemon> = mutableListOf()
                    searchResult?.pokemon?.let { it1 -> pokeList.add(it1) }
                    updateList(
                        pokeList
                    )
                }
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.refresh, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.refresh -> {
                binding.searchEditText?.text?.clear()
                presenter.initAndShow()
                return true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.itemList
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        presenter.onCreated()

        itemListAdapter = ItemListRecyclerViewAdapter(
            pokemonList, itemDetailFragmentContainer,
            object : ItemListRecyclerViewAdapter.ListItemSelectListener<Pokemon> {
                override fun onSelect(item: Pokemon) {
                    selectItem(item)
                }
            })
        if (recyclerView != null) {
            setupRecyclerView(recyclerView)
        }

    }

    private fun selectItem(item: Pokemon) {
        val action =
            ItemListFragmentDirections.showItemDetail(item)
        if (action.arguments != null) {
            view?.findNavController()?.navigate(action)
        }

    }

    override fun initPresenter(): ItemListPresenter {
        presenter = ItemListPresenter(this, ItemListModel())
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}