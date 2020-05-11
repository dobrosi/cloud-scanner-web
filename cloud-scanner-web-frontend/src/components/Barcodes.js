import React from 'react'
import AddBarcodeForm from './AddBarcodeForm';
import { Button, Table } from 'reactstrap';

const Barcodes = ({ app, barcodes }) => {	
	  return (
		  <div>
		  <AddBarcodeForm app={app}/>
		  <Table>
		  <thead><tr><td>Value</td><td>GPS info</td></tr></thead>
		  <tbody>
	      {barcodes.map((barcode) => (
	        <tr>
	          <td>
	            {barcode.value}
	          </td>
	          <td>
	            {barcode.gpsInfo}
	          </td>
	          <td>
	            <Button onClick={() => app.deleteBarcode(barcode)}>Delete</Button>
	          </td>
	        </tr>
	      ))}
	      </tbody>
	      </Table>
	    </div>
	  )
	};

export default Barcodes
