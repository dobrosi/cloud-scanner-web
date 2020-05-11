import React from 'react'
import { Form, FormGroup, Label, Input, Button } from 'reactstrap';

class LoginForm extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        value: props.app.loginId
      };
      
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
      this.setState({value: event.target.value});
    }

    handleSubmit(event) {
      this.props.app.login(this.state.value)
      event.preventDefault();
    }

    render() {
      return (
        <Form>
        <FormGroup>
          <Label>
            Login ID:
            <Input type="text" name="login" value={this.state.value} onChange={this.handleChange} />
          </Label>
        </FormGroup>
        <FormGroup>
          <Button onClick={this.handleSubmit}>Login</Button>
        </FormGroup>
        </Form>
      );
    }
  }

export default LoginForm
