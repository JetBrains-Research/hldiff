import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiffComponent } from './diff/diff.component';
import { SourceCodeComponent } from './source-code/source-code.component';
import { CodeFragmentComponent } from './code-fragment/code-fragment.component';
import { ChangeActionComponent } from './change-action/change-action.component';
import { HttpClientModule } from '@angular/common/http';
import { EvaluationModule } from './evaluation/evaluation.module';

@NgModule({
  declarations: [
    AppComponent,
    DiffComponent,
    SourceCodeComponent,
    CodeFragmentComponent,
    ChangeActionComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    EvaluationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
