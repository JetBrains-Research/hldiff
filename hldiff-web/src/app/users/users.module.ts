import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { MaterialModule } from '../material/material.module';
import { RegisterDialogComponent } from './register-dialog/register-dialog.component';


@NgModule({
  declarations: [LoginDialogComponent, RegisterDialogComponent],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports: [
    LoginDialogComponent,
    RegisterDialogComponent
  ]
})
export class UsersModule {
}
