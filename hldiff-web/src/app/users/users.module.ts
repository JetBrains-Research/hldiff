import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { MaterialModule } from '../material/material.module';


@NgModule({
  declarations: [LoginDialogComponent],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports: [
    LoginDialogComponent
  ]
})
export class UsersModule {
}
