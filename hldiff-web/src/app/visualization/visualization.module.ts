import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DiffComponent } from './diff/diff.component';
import { SourceCodeComponent } from './source-code/source-code.component';
import { CodeFragmentComponent } from './code-fragment/code-fragment.component';
import { ChangeActionComponent } from './change-action/change-action.component';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [
    DiffComponent,
    SourceCodeComponent,
    CodeFragmentComponent,
    ChangeActionComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
  ],
  exports: [
    DiffComponent,
    SourceCodeComponent,
    CodeFragmentComponent,
    ChangeActionComponent
  ]
})
export class VisualizationModule { }
