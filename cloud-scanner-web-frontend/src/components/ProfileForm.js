import React from 'react'
import { Form, FormGroup, Label, Input, Button } from 'reactstrap';
import RegistrationForm from './RegistrationForm';
import '../config';

class ProfileForm extends RegistrationForm {
	  constructor(props) {
		super(props);
		this.saveLabel = 'Save';
	  }
	  handleSubmit(event) {
		this.props.app.saveProfile(this.state);
	    event.preventDefault();
	  }
}

export default ProfileForm
