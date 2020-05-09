import React from 'react'
import { Form, FormGroup, Label, Input, Button } from 'reactstrap';

class LoginForm extends React.Component {
	  constructor(props) {
	    super(props);
	    this.state = {email: '', firstname: '', lastname: ''};

	    this.handleEmailChange = this.handleEmailChange.bind(this);
	    this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
	    this.handleLastNameChange = this.handleLastNameChange.bind(this);
	    this.handleSubmit = this.handleSubmit.bind(this);
	  }

	  
	  handleEmailChange (evt) {
	    this.setState({ email: evt.target.value });
	  }
	  
	  handleFirstNameChange (evt) {
	    this.setState({ firstname: evt.target.value });
	  }

	  handleLastNameChange (evt) {
		    this.setState({ lastname: evt.target.value });
	  }
	  
	  handleSubmit(event) {
	    alert('A name was submitted: ' + this.state);
	    event.preventDefault();
	  }

	  render() {
	    return (
  		  <Form onSubmit={this.handleSubmit}>
	       <FormGroup>
	        <Label>
	          E-mail
	          <Input type="email" value={this.state.email} onChange={this.handleEmailChange} />
	        </Label>
	      </FormGroup>
	      <FormGroup>
	        <Label>
	          Firstname
	          <Input type="text" value={this.state.firstname} onChange={this.handleFirstNameChange} />
	        </Label>
	      </FormGroup>
	      <FormGroup>
	        <Label>
	          Lastname
	          <Input type="text" value={this.state.lastname} onChange={this.handleLastNameChange} />
	        </Label>
	      </FormGroup>
	      <FormGroup>
	        <Button type="submit">Registration</Button>
	      </FormGroup>
	    </Form>
	    );
	  }
	}

export default LoginForm
