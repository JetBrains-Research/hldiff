import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiffComponent } from './visualization/diff/diff.component';
import { DashboardComponent } from './evaluation/dashboard/dashboard.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'diff/:id', component: DiffComponent },
  { path: 'dashboard', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
