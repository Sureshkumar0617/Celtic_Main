Feature: Renewal after Reinstatement
@RWC002
Scenario: Renew with Reinstate chrome

Given Login as Internal user
When User will navigate to IRP & Reinstate Fleet
Then User will move to Distance page & validate the message "[I] MCE: IRP status successfully updated."
Then User will navigate to billing Page & verify the information
And User will validate the Success Information Message "IRPBILL01 : [I] Reinstatement process completed successfully."
Then User will navigate to the Renewal Screen
Then User will navigate to Account screen under IRP and provides all the required input to proceed 
Then user will navigate to Fleet to input the data and validate the message "IRPACC00 : [I] Renewal account has been generated." 
Then user will navigate to Distance to input the data and validate message "IRPFLT01 : [I] Renewal fleet has been generated." "[I] MCE: IRP status successfully updated."
Then user will navigate to Weight Group to add weight group "IRPMLG13 : [I] Renewal Distance has been generated."
And user will amend the existing weight group 
Then user will navigate to Vehicle screen and add the vehicles that was previously deleted in expired year and validate the message "IRPVEH375 : [I] Please search the VIN first to process a vehicle." "IRPVEH004 : [I] Vehicle amended successfully."
And user will navigate to vehicle list to update the added vehicle details and validate message "IRPVEH004 : [I] Vehicle amended successfully."
And user will navigate to vehicle list to modify the added vehicle details
Then User will navigate to billing to verify as well to waive fees and adjust the cost fees "IRPBILL09 : [E] Manual adj. reason is required."
Then User will navigate to Payment Tab to input the fields and validate message "GEN24 : [I] Invoice report generated successfully." "IRPREP28 : [I] Billing completed successfully." "GEN1239 : [I] TVR generated successfully."
Then User will navigate will collect all vehicle level documents from Vehicle Documents "Credentials generated successfully"
Then user will navigate to Payment page for 2nd installment and validate message "Payment Receipt generated successfully" "Payment completed successfully." 
And User will navigate to all the inquiries and report with respect to the flow for validation.