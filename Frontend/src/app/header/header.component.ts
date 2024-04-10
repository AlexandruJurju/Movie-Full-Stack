import {Component, inject, Input} from '@angular/core';
import {NgIf, NgOptimizedImage} from "@angular/common";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    NgOptimizedImage,
    RouterLink,
    RouterLinkActive,
    NgIf
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  @Input() title = "Title"

  authService = inject(AuthService);
  router = inject(Router);

  logout() {
    console.log("logout");
    localStorage.setItem('token', '');
    this.authService.currentUserSig.set(null);
    this.router.navigateByUrl('/')
  }
}
