// src/App.js

import React, { Component } from 'react';
import { Container, ButtonGroup, Button } from 'reactstrap';
import './config';
import Barcodes from './components/Barcodes';
import RegistrationForm from './components/RegistrationForm';
import ProfileForm from './components/ProfileForm';
import LoginForm from './components/LoginForm';

let thisApp;

class App extends Component {
  state = {
    page: 'login',
    authenticated: false
  }
  
  barcodes = {}
  
  barcodeuser = {}
  
  loginId = null
  
  status(response) {
	if (response.status >= 200 && response.status < 300) {
      return Promise.resolve(response)
	} else {
      return Promise.reject()
    }
  }

  json(response) {
    return response.json()
  }
  
  doFetch(method, url, s, e, body, headers) { 
	if (headers == null) {
      headers = this.createHeaders();
	}
    if (body != null) {
    	body = JSON.stringify(body);
    }
    if (s == null) {
      s = console.log;
    }
    if (!url.startsWith('http')) {
      url = global.config.host + url;
    }
    fetch(url, {
      method: method,
      headers: headers,
      body: body
    }).then(thisApp.status).then(thisApp.json).then(s).catch((r) => thisApp.showError(e, r))
  }
  
  doGet(url, s, e, headers) {
    this.doFetch('GET', url, s, e, headers);
  }
  
  doPost(url, s, e, body, headers) {
	this.doFetch('POST', url, s, e, body, headers);
  }
  
  doPut(url, s, e, body, headers) {
	this.doFetch('PUT', url, s, e, body, headers);
  }

  doDelete(url, s, e, body, headers) {
	this.doFetch('DELETE', url, s, e, body, headers);
  }
  
  createHeaders() {
    return {
      'Content-Type': 'application/json',
	  'Accept': 'application/json, text/plain, */*',
	  'Authorization': 'Basic ' + btoa(thisApp.loginId + ":")
    };
  }
  
  showError(e, r) {
    if (e != null) {
	  alert(e)
	}
    console.log(r);
  }
  
  showInfo(i) {if (i != null) alert(i)}
    
  registration(v) {
    thisApp.doPost('/signUp', thisApp.afterRegistration, 'Registration failed.', v, {
	    'Accept': 'application/json, text/plain, */*',
	    'Content-Type': 'application/json'
	  });
  }
  
  afterRegistration (res) {
	thisApp.loginId = res.response;
    thisApp.showInfo('Registration successful.\n\nYour LoginID: ' + thisApp.loginId);
	thisApp.goToLogin();
  }
  
  saveProfile(v) { 
    thisApp.doPut(v._links.self.href, thisApp.afterSaveProfile, 'Save profile failed.', v);
  }
  
  afterSaveProfile() {
	  thisApp.showInfo('Saved.');
  }
  
  goToLogin() {
	 thisApp.setState({ page: 'login', authenticated: false });
  }
  
  login(loginId) {
	thisApp.loginId = loginId;
    thisApp.doGet('/barcodeUsers/search/findByLoginId?loginId=' + loginId, thisApp.afterLogin, 'Login failed.');
  }
  
  afterLogin(d) {
	thisApp.barcodeuser = d;
	thisApp.getBarcodes1();
  }
  
  logout() {
	thisApp.loginId = null;
	thisApp.barcodeuser = {};
	thisApp.doGet('/perform_logout', thisApp.goToLogin);
	thisApp.setState({ page: 'login', authenticated: false });
	
  }
  
  getBarcodes1() {
	thisApp.getBarcodes2(thisApp.afterGetBarcodes1, 'Get barcodes failed.');
  }
  
  afterGetBarcodes1(d) {  
	  thisApp.barcodes = d;	
	  thisApp.setState( {page: 'barcodes', authenticated: true} );
  }
  
  getBarcodes2(s, e) {
    thisApp.doGet('/barcode/list', s, e, null);
  }
  
  addBarcode(v) {
	 thisApp.doPut('/barcode/save', thisApp.getBarcodes1, 'Add barcode failed.', v);
  }
  
  deleteBarcode(v) {  
	  thisApp.doDelete('/barcode/delete', thisApp.getBarcodes1, 'Delete barcode failed.', [v]);
  }
  
  render() {
	thisApp = this
    return (
      <Container>
        <ButtonGroup>
          <Button onClick={() => this.setState({ page: 'registration' })}>Registration</Button>
          {!this.state.authenticated && <Button onClick={() => this.setState({ page: 'login' })}>Login</Button>}
          {this.state.authenticated && <Button onClick={() => this.setState({ page: 'barcodes' })}>Barcodes</Button>}
          {this.state.authenticated && <Button onClick={() => this.setState({ page: 'profile' })}>Profile</Button>}
          {this.state.authenticated && <Button onClick={() => this.logout()}>Logout</Button>}
        </ButtonGroup>
        <div className='mt-4'>
          {this.state.page === 'registration' && <RegistrationForm app={thisApp}/>}
          {this.state.page === 'login' && <LoginForm app={thisApp}/>}
          {this.state.page === 'barcodes' && <Barcodes app={thisApp} barcodes={thisApp.barcodes}/>}
          {this.state.page === 'profile' && <ProfileForm app={thisApp}/>}
        </div>
      </Container>
    );
  }
}

export default App;