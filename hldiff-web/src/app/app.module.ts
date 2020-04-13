import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { EvaluationModule } from './evaluation/evaluation.module';
import { VisualizationModule } from './visualization/visualization.module';
import { NavigationModule } from './navigation/navigation.module';
import { UsersModule } from './users/users.module';
import { BasicAuthenticationInterceptor } from './users/basic-authentication-interceptor';
import { ErrorInterceptor } from './users/error-interceptor';

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
    NavigationModule,
    UsersModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthenticationInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
