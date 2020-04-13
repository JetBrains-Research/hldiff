import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutingModule } from '../app-routing.module';
import { UploadDialogComponent } from './upload-dialog/upload-dialog.component';
import { MaterialModule } from '../material/material.module';


@NgModule({
  declarations: [DashboardComponent, UploadDialogComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    MaterialModule
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class EvaluationModule {
}
