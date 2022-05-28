package com.livingTechUSA.pokemon.screens.ItemDetail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.livingTechUSA.pokemon.R
import com.livingTechUSA.pokemon.databinding.FragmentItemDetailBinding
import com.livingTechUSA.pokemon.models.Ability
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.localService.ILocalService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext


class ItemDetailFragment(): Fragment(), ItemDetailView,CoroutineScope, KoinComponent {
    private val localService: ILocalService by inject()
    private lateinit var itemListAdapter: AbilityListRecyclerViewAdapter
    private var abilityList = mutableListOf<Ability>()
    companion object {
        fun getNewInstance(bundle: Bundle?): ItemDetailFragment {
            val fragment = ItemDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    val appDispatcher: IAppDispatchers by inject()
    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = appDispatcher.io() + job


    private lateinit var presenter: ItemDetailPresenter
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    val args: ItemDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initPresenter()
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.pokemon.name
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.save -> {
                launch(appDispatcher.io()) {
                    localService.insertPokemon(args.pokemon)
                    launch(appDispatcher.ui()) {
                        Toast.makeText(
                            context,
                            getString(R.string.save_success),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                true
            }
            R.id.delete -> {
                launch(appDispatcher.io()) {
                    localService.removePokemon(args.pokemon)
                    launch(appDispatcher.ui()) {
                        Toast.makeText(
                            context,
                            getString(R.string.delete_success),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                true
            }
            R.id.viewSaved -> {
                val action = ItemDetailFragmentDirections.actionItemDetailFragmentToSavedListFragment()
                view?.findNavController()?.navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView? = binding.abilityListRV
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)
        itemListAdapter = AbilityListRecyclerViewAdapter(
            abilityList)
        if (recyclerView != null) {
            setupRecyclerView(recyclerView)
        }
        presenter.setBundle(args.pokemon)
        presenter.onCreated()
        setHasOptionsMenu(true)
        //Load image
        val image = binding.detailImageView
        if(args.pokemon.imageUrl.isNullOrEmpty().not()) {
            Picasso.get().load(args.pokemon.imageUrl).into(image)
        }
        binding.pokemonName.text = args.pokemon.name
    }


    override fun initPresenter(): ItemDetailPresenter {
        presenter = ItemDetailPresenter(this, ItemDetailModel())
        return presenter
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView
    ) {
        recyclerView.adapter = itemListAdapter
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.abilityListRV?.layoutManager = linearLayoutManager
        binding.abilityListRV?.adapter = itemListAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override suspend fun navigateToSavedPokemon() {
//        val _sample = async { localService.getOneFromPokemonTable() }
//        val sample = _sample.await()
//        if (sample?.title == null) {
//            launch(appDispatcher.ui()) {
//                Toast.makeText(
//                    context,
//                    getString(R.string.nothing_saved),
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
//            }
//        } else {
//            launch(appDispatcher.ui()) {
//                val action =
//                    ItemDetailFragmentDirections.actionItemDetailFragmentToSavedPokemonsFragment()
//                view?.findNavController()?.navigate(action)
//            }
//        }
    }


    override suspend fun updateList(abilityList: List<Ability>) {
        launch(appDispatcher.ui()) { binding.progressBar2?.visibility = View.VISIBLE }
        coroutineScope {
            val job = launch {
                itemListAdapter.clearAbility()
            }
            job.join()
            launch(appDispatcher.ui()) {
                itemListAdapter.updateList(abilityList)
                showNoAbilitiesFound(abilityList.isEmpty())
            }
            launch(appDispatcher.ui()) { binding.progressBar2?.visibility = View.GONE }
        }
    }


    override fun showNoAbilitiesFound(show: Boolean) {
        if (show) {
            Toast.makeText(context, "No Abilities found for search criteria.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    interface PokemonSelectListener<Pokemon> {
        fun onSelect(item: Pokemon)
    }
}