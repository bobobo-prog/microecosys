import { NgModule } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { authGuard } from './auth/auth.guard';

export const routes: Routes = [

    {
        path:'auth',
        loadChildren:() => import('./auth/auth.module').then(m => m.AuthModule)
    },
    {
        path:'dashboard',
        canActivate:[authGuard],
        loadChildren:() => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
            
    },

    {
        path:'',redirectTo:'/auth/login',pathMatch:'full'
    },
    {
        path:'**',redirectTo:'/auth/login'
    }


];


@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
})
export class AppModule{}
