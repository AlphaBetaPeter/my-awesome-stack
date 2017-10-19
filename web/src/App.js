import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  state = {
    pet: null
  }

  fetchPet = () => {
    fetch("/api/pet")
      .then(result => result.json())
      .then(pet => {
        console.log(pet)
        this.setState({
          pet
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
        <pre>{JSON.stringify(this.state.pet)}</pre>
      </div>
    );
  }
}

export default App;
