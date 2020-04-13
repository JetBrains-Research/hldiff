import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { MaterialModule } from '../material/material.module';


@NgModule({
  declarations: [NavigationBarComponent],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports: [
    NavigationBarComponent,
  ]
})
export class NavigationModule {
}
