import React from "react";

export default class Step2 extends React.Component {
  render() {
    if (this.props.currentStep !== 2) { // Prop: The current step
      return null
    }
    // The markup for the Step 2 UI
    return (
      <div className="form-group">
        <label htmlFor="name">Name</label>
        <input
          className="form-control"
          id="name"
          name="username"
          type="text"
          placeholder="Enter name"
          value={this.props.name} // Prop: The name input data
          onChange={this.props.handleChange} // Prop: Puts data into state
        />
      </div>
    )
  }
}
