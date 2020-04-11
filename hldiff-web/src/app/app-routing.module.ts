import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiffComponent } from './diff/diff.component';
import { UploadComponent } from './evaluation/upload/upload.component';


const routes: Routes = [
  { path: '', redirectTo: '/diff', pathMatch: 'full' },
  { path: 'diff', component: DiffComponent },
  { path: 'upload', component: UploadComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
