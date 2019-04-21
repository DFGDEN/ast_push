package com.example.ast.injection.component


import com.example.ast.injection.module.FragmentModule
import com.example.ast.injection.scope.FragmentScope
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {


//    fun inject(fragment: ContactsFragment)

}
