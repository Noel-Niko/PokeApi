package com.livingTechUSA.pokemon.Base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.livingTechUSA.pokemon.util.Ui

abstract class BaseFragment : Fragment() {
    private var presenter: BasePresenter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setPresenter(initPresenter())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStart()

    }

    override fun onResume() {
        super.onResume()
        if (presenter != null) {
            presenter?.onResume()
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStop()
    }

    override fun onPause() {
        super.onPause()
        if (presenter != null) {
            presenter?.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter?.onDestroy()
        }
    }


    fun setPresenter(presenter: BasePresenter?) {
        this.presenter = presenter
    }

    abstract fun initPresenter(): BasePresenter?


    open fun isTablet(): Boolean = activity?.let { Ui.isTablet(it) } ?: false

}