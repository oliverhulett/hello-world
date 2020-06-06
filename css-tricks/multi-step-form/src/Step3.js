import React from "react";

export default class Step3 extends React.Component {
  render() {
    if (this.props.currentStep !== 3) { // Prop: The current step
      return null
    }
    // The markup for the Step 3 UI
    return (
      <div className="form-group">
        <label htmlFor="address">Address</label>
        <input
          className="form-control"
          id="address"
          name="address"
          type="textbox"
          placeholder="Enter address"
          value={this.props.address} // Prop: The address input data
          onChange={this.props.handleChange} // Prop: Puts data into state
        />
      </div>
    )
  }
}
