import React from 'react';
import './App.css';
import {
  Route,
  NavLink,
  BrowserRouter,
  Switch
} from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div className="app-header">
        <NavLink to="/"><h1>Home</h1></NavLink>
      </div>
      <div className="app-search">
        <Route exact path="/" component={ProductSearchPage} />
      </div>
      <Switch>
        <Route path="/search?:query" component={SearchResults} />
        <Route path="/product/:id" component={ProductPage} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
