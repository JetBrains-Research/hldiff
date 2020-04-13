import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { MaterialModule } from '../material/material.module';
import { AppRoutingModule } from '../app-routing.module';


@NgModule({
  declarations: [NavigationBarComponent],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule
  ],
  exports: [
    NavigationBarComponent,
  ]
})
export class NavigationModule {
}
