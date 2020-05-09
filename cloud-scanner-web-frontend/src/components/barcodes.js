import React from 'react'

const Barcodes = ({ barcodes }) => {
  return (
    <div>
      <center><h1>Barcodes</h1></center>
      {barcodes.map((barcode) => (
        <div class="card">
          <div class="card-body">
            <p class="card-text">{barcode.value}</p>
          </div>
        </div>
      ))}
    </div>
  )
};

export default Barcodes
