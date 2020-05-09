// src/App.js

import React, { Component } from 'react';
import { Container, ButtonGroup, Button } from 'reactstrap';
import Barcodes from './components/barcodes';
import RegistrationForm from './components/registration';
import LoginForm from './components/loginform';

let url = 'http://192.168.1.102:8080/barcodes';
let username = 'r5kVzDMZB';


let headers = new Headers();
headers.set('Authorization', 'Basic ' + btoa(username + ":"));

class App extends Component {
  state = {
    barcodes: [],
    page: 'home'
  }
  getBarcodeList(res) {
    headers.set('set-cookie', res.headers.get('set-cookie'));
      fetch(url, {method:'GET', headers: headers})
      .then(res => res.json())
      .then((data) => {
         this.setState({ barcodes: data._embedded.barcodes })})
      .catch(console.log)  
  }
  componentDidMount() {
  navigator.geolocation.getCurrentPosition(function(n){alert(n)});
    fetch(url, {method:'GET', headers: headers}).then(res => 
        this.getBarcodeList(res)
      ).catch(console.log)

  }
  render() {
    return (
      <Container>
        <ButtonGroup>
          <Button onClick={() => this.setState({ page: 'registration' })}>Registration</Button>
          <Button onClick={() => this.setState({ page: 'login' })}>Login</Button>
          <Button onClick={() => this.setState({ page: 'barcodes' })}>Barcodes</Button>
        </ButtonGroup>
        <div className='mt-4'>
          {this.state.page === 'registration' && <RegistrationForm/>}
          {this.state.page === 'login' && <LoginForm/>}
          {this.state.page === 'barcodes' && <Barcodes barcodes={this.state.barcodes} />}
        </div>
      </Container>
    );
  }
}

export default App;
