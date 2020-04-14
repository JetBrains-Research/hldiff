import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutingModule } from '../app-routing.module';
import { UploadDialogComponent } from './upload-dialog/upload-dialog.component';
import { MaterialModule } from '../material/material.module';
import { DiffEvaluationComponent } from './diff-evaluation/diff-evaluation.component';
import { VisualizationModule } from '../visualization/visualization.module';


@NgModule({
  declarations: [DashboardComponent, UploadDialogComponent, DiffEvaluationComponent],
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
