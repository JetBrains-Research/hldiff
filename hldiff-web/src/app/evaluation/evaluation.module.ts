import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UploadComponent } from './upload/upload.component';
import { AppRoutingModule } from '../app-routing.module';
import { AppModule } from '../app.module';


@NgModule({
  declarations: [UploadComponent],
  imports: [
    AppModule,
    CommonModule,
    AppRoutingModule
  ]
})
export class EvaluationModule {
}
