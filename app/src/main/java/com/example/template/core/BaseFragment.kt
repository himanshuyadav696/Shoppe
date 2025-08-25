package com.example.template.core

import androidx.fragment.app.Fragment


open class BaseFragment(private val layoutRes: Int) : Fragment(layoutRes)



data class EmptyState(val imageRes: Int, val title: String, val subTitle: String)