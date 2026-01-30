import { Component, OnDestroy, signal } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { authConfig } from './oauth.config';
import { AppService } from './app-service';
import { Subscription, filter } from 'rxjs';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  
  standalone: true,
    imports: [NgIf],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent implements OnDestroy {

  protected readonly title = signal('frontend');
  text = '';
  isLoggedIn = false;

  private helloSubscription?: Subscription;
  private oauthSubscription?: Subscription;

  constructor(
    private oauthService: OAuthService,
    private appService: AppService
  ) {
    
    this.initAuth();
  }

 private initAuth(): void {
  this.oauthService.configure(authConfig);

  this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
    if (this.oauthService.hasValidAccessToken()) {
      console.log('Logged in (refresh)');
      this.isLoggedIn = true;
      this.callBackend();
    }
  });

  this.oauthSubscription = this.oauthService.events
    .pipe(filter(e => e.type === 'token_received'))
    .subscribe(() => {
      console.log('Logged in (new token)');
      this.isLoggedIn= true;
      this.callBackend();
    });
}

  private callBackend(): void {
    this.helloSubscription?.unsubscribe(); 

    this.helloSubscription = this.appService.hello().subscribe({
      next: response => this.text = response,
      error: err => console.error('Backend call failed', err)
    });
  }



  login(): void {
      if (this.oauthService.hasValidAccessToken()) {
    console.log('Already logged in, not redirecting');
    return;
  }

  this.oauthService.initCodeFlow();
    this.oauthService.initCodeFlow();
  }

  logout(): void {
      this.oauthService.logOut();
  this.isLoggedIn = false;
  this.text = '';
  }

  ngOnDestroy(): void {
    this.helloSubscription?.unsubscribe();
    this.oauthSubscription?.unsubscribe();
  }
}
