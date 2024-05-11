import {Component} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink, RouterOutlet} from "@angular/router";
import {MenuComponent} from "./components/menu/menu.component";
import {ApiModule} from "./service/swagger/api.module";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    RouterOutlet,
    RouterLink,
    MenuComponent,
    // NEED THIS FOR SWAGGER GENERATED SERVICES
    ApiModule
  ]
  ,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {

}
