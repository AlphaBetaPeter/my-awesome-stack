import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  state = {
    pets: null
  }

  fetchPet = () => {
    fetch("/api/pets")
      .then(result => result.json())
      .then(pets => {
        console.log(pets)
        this.setState({
          pets
        })
      })
    .catch(e => console.log(e))
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <button onClick={this.fetchPet}>fetchPet</button>
        <pre>{JSON.stringify(this.state.pets)}</pre>
      </div>
    );
  }
}

export default App;
