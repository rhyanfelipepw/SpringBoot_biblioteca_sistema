import React from 'react';
import {BrowserRouter as Router,Switch,Route} from "react-router-dom";
import './App.css';

import Dashboard from './components/Home';
import Pessoa from './components/Pessoa';
import Emprestimo from './components/Emprestimo';
import Livro from './components/Livro';
import Exemplar from './components/exemplar';
import Navbarmenu from './components/menu/Navbarmenu';
import Categoria from './components/Categoria';
import AreaConhecimento from './components/AreaConhecimento';

function App() {
  return (
    <div>
      <Router basename="/">

        {/* Add Menu Component */}
        <Navbarmenu />
        <Switch> 
          <Route exact path="/" component={Dashboard}/>
          <Route path="/Pessoa" component={Pessoa}/>
          <Route path="/Emprestimo" component={Emprestimo}/>
          <Route path="/Livro" component={Livro}/>
          <Route path="/Exemplar" component={Exemplar}/>
          <Route path="/Categoria" component={Categoria}/>
          <Route path="/AreaConhecimento" component={AreaConhecimento}/>

        </Switch>
      </Router>

    </div>
  );
}

export default App;
