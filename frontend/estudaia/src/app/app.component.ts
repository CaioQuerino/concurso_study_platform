import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { 
    RouterLink, 
    RouterOutlet 
} from '@angular/router';
import { IonRouterOutlet } from '@ionic/angular/standalone';

@Component({
selector: 'app-root',
standalone: true,
  imports: [
    CommonModule,
    FormsModule, 
    RouterLink,
    RouterOutlet,
    IonRouterOutlet
  ],
templateUrl: './app.component.html'
})
export class AppComponent {}