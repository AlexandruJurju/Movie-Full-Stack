import {Component} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink, RouterOutlet} from "@angular/router";
import {MenuComponent} from "./components/menu/menu.component";
import {ApiModule} from "./service/api.module";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    RouterOutlet,
    RouterLink,
    MenuComponent,
    ApiModule
  ]
  ,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {

}
