import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { EvaluationModule } from './evaluation/evaluation.module';
import { VisualizationModule } from './visualization/visualization.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    EvaluationModule,
    VisualizationModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
