package com.example.ast.injection.component


import com.example.ast.injection.module.ActivityModule
import com.example.ast.injection.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

//     fun inject(activity: PickCountryActivity)

}
