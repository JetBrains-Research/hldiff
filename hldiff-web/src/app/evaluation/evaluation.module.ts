import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutingModule } from '../app-routing.module';
import { UploadDialogComponent } from './upload-dialog/upload-dialog.component';
import { MaterialModule } from '../material/material.module';
import { DiffEvaluationComponent } from './diff-evaluation/diff-evaluation.component';
import { VisualizationModule } from '../visualization/visualization.module';
import { ActionEvaluationDialogComponent } from './action-evaluation-dialog/action-evaluation-dialog.component';
import { FileUploadComponent } from './file-upload/file-upload.component';


@NgModule({
  declarations: [DashboardComponent, UploadDialogComponent, DiffEvaluationComponent, ActionEvaluationDialogComponent, FileUploadComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    MaterialModule,
    VisualizationModule
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class EvaluationModule {
}
