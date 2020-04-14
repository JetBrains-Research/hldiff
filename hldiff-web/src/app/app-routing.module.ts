import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiffComponent } from './visualization/diff/diff.component';
import { DashboardComponent } from './evaluation/dashboard/dashboard.component';
import { DiffEvaluationComponent } from './evaluation/diff-evaluation/diff-evaluation.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'diff/:id', component: DiffComponent },
  { path: 'diff/evaluation/:id', component: DiffEvaluationComponent },
  { path: 'dashboard', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
