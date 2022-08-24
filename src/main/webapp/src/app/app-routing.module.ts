import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardContainerComponent } from './courses/dashboard/dashboard-container/dashboard-container.component';

const routes: Routes = [
  {path : "dashboard", component : DashboardContainerComponent},
  {path : "", redirectTo : "dashboard", pathMatch : "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
