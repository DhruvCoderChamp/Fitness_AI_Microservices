export const authConfig = {
  clientId: 'fitness-pkse-client',
  authorizationEndpoint: 'http://localhost:8181/realms/fitness-app/protocol/openid-connect/auth',
  tokenEndpoint: 'http://localhost:8181/realms/fitness-app/protocol/openid-connect/token',
  redirectUri: 'http://localhost:5173/',
  scope: 'openid profile roles email',
  onRefreshTokenExpire: (event) => event.logIn(),

  autoLogin: false,
  pkceEnabled: true,
  responseType: 'code',
}