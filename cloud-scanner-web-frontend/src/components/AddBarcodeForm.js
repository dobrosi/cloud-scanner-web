import React from 'react'
import { Form, FormGroup, Label, Input, Button } from 'reactstrap';

class AddBarcodeForm extends React.Component {
	  constructor(props) {
	    super(props);
	    
	    this.state = {
	      value: ''
	    };
	    this.position = null;
	    
	    this.handleChange = this.handleChange.bind(this);
	    this.handleSubmit = this.handleSubmit.bind(this);
	    this.getLocation();
	  }

	  handleChange(event) {
	    this.setState({
	    	value: event.target.value,
	    	gpsInfo: this.position
	    });
	  }

	  handleSubmit(event) {
        this.props.app.addBarcode(this.state)
	    event.preventDefault();
	  }

	  getLocation() {
		if (navigator.geolocation) {
		  navigator.geolocation.getCurrentPosition(this.savePosition);
		} else {
          this.position = null;
        }
      }

	  savePosition(position) {
        this.position = position.coords;
        this.position.date = position.timestamp;
      }
	  
	  render() {
	    return (
	      <Form>
	      <FormGroup>
	        <Label>
	          Barcode value:
	          <Input type="text" name="value" value={this.state.value} onChange={this.handleChange} />
	        </Label>
	        <Button onClick={this.handleSubmit}>Add</Button>
	      </FormGroup>
	      </Form>
	    );
	  }
	}

export default AddBarcodeForm
