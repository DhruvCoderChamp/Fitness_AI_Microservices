// import React from 'react'
// import ReactDOM from 'react-dom/client'

// import { Provider } from 'react-redux'
// import { store } from './store/store'

// import App from './App'
// import { AuthProvider } from 'react-oauth2-code-pkce'
// import { authConfig } from './authConfig'

// const root = ReactDOM.createRoot(document.getElementById('root'))
// root.render(
//   <AuthProvider authConfig={authConfig}>
//   <Provider store={store}>
//     <App />
//   </Provider>,
//   </AuthProvider>

// )
import React from 'react'
import ReactDOM from 'react-dom/client'

import { Provider } from 'react-redux'
import { store } from './store/store'

import App from './App'
import { AuthProvider } from 'react-oauth2-code-pkce'
import { authConfig } from './authConfig'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  // **strict-mode ---cross verify krta h 2 bar load kr ke*
  //redux -- global state manangement h , data rhkta h sabko reflect kr h .
  <React.StrictMode>
    <AuthProvider authConfig={authConfig}>
      <Provider store={store}>
        <App />
      </Provider>
    </AuthProvider>
  </React.StrictMode>
)