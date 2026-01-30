import { AuthConfig } from 'angular-oauth2-oidc';


export const authConfig: AuthConfig ={

  issuer: 'http://localhost:8081/realms/oauth2-demo-realm',
  redirectUri: window.location.origin,
  clientId: 'oauth2-pkce-demo',
  responseType: 'code',
  scope: 'openid profile email',
  requireHttps: false,
  strictDiscoveryDocumentValidation: false,
  showDebugInformation: true,
}
