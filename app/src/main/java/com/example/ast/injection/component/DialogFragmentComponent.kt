package com.example.ast.injection.component



import com.example.ast.injection.module.DialogFragmentModule
import com.example.ast.injection.scope.DialogFragmentScope
import dagger.Subcomponent

/**
 * Created by dfgden on 10/2/17.
 */
@DialogFragmentScope
@Subcomponent(modules = arrayOf(DialogFragmentModule::class))
interface DialogFragmentComponent {


}