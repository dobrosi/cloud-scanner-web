import React from 'react'
import { Form, FormGroup, Label, Input, Button } from 'reactstrap';
import '../config';

class RegistrationForm extends React.Component {
	  constructor(props) {
	    super(props);

		this.state = props.app.barcodeuser;
	    this.saveLabel = 'Registration';
	    
	    this.handleEmailChange = this.handleEmailChange.bind(this);
	    this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
	    this.handleLastNameChange = this.handleLastNameChange.bind(this);
	    this.handleSubmit = this.handleSubmit.bind(this);
	  }

	  handleEmailChange (evt) {
	    this.setState({ email: evt.target.value });
	  }
	  
	  handleFirstNameChange (evt) {
		  this.setState({ firstName: evt.target.value });
	  }

	  handleLastNameChange (evt) {
		    this.setState({ lastName: evt.target.value });
	  }

	  handleSubmit(event) {
		this.props.app.registration(this.state);
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
	          <Input type="text" value={this.state.firstName} onChange={this.handleFirstNameChange} />
	        </Label>
	      </FormGroup>
	      <FormGroup>
	        <Label>
	          Lastname
	          <Input type="text" value={this.state.lastName} onChange={this.handleLastNameChange} />
	        </Label>
	      </FormGroup>
	      <FormGroup>
	        <Button type="submit">{this.saveLabel}</Button>
	      </FormGroup>
	    </Form>
	    );
	  }
	}

export default RegistrationForm
