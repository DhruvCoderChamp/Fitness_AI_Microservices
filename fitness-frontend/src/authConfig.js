export const authConfig = {
  clientId: 'fitness-app-realm',
  authorizationEndpoint: 'http://localhost:8181/realms/master/protocol/openid-connect/auth',
  tokenEndpoint: 'http://localhost:8181/realms/master/protocol/openid-connect/token',
  redirectUri: 'http://localhost:5173/',
  scope: 'someScope openid',
  onRefreshTokenExpire: (event) => event.logIn(),
}