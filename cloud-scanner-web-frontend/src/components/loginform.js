import React from 'react'
import { Form, FormGroup, Label, Input, Button } from 'reactstrap';

class LoginForm extends React.Component {
	  constructor(props) {
	    super(props);
	    this.state = {value: ''};

	    this.handleChange = this.handleChange.bind(this);
	    this.handleSubmit = this.handleSubmit.bind(this);
	  }

	  handleChange(event) {
	    this.setState({value: event.target.value});
	  }

	  handleSubmit(event) {
	    alert('A name was submitted: ' + this.state.value);
	    event.preventDefault();
	  }

	  render() {
	    return (
	      <Form onSubmit={this.handleSubmit}>
	      <FormGroup>
	        <Label>
	          User ID:
	          <Input type="text" name="login" value={this.state.value} onChange={this.handleChange} />
	        </Label>
	      </FormGroup>
	      <FormGroup>
	        <Button type="submit">Login</Button>
	      </FormGroup>
	      </Form>
	    );
	  }
	}

export default LoginForm
