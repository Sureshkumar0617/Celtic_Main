Feature: Straight Renewal Fleet which is about to expire
@RWC003
Scenario: Straight Renewal of the fleet which is about to expire edge


Given login user as Internal
When User will navigate to IRP option
Then User navigate to renew fleet to input the required information 
Then User will input the data for account screen  and validate message 
Then User will navigate to fleet  screen and update the required information "IRPACC00 : [I] Renewal account has been generated."
Then User will navigate to distance to input the data and validate message "IRPFLT01 : [I] Renewal fleet has been generated." "[I] MCE: IRP status successfully updated."
Then User will navigate to weight group to add the new weight group "IRPMLG13 : [I] Renewal Distance has been generated."
And  User will update the existing weight group to proceed
Then User will navigate to vehicle screen and proceed further  
Then User will navigate to billing to input data and avail installment plan 
Then User will navigate to payment tab to input the fields and validate message "GEN24 : [I] Invoice report generated successfully." "IRPREP28 : [I] Billing completed successfully." "GEN1239 : [I] TVR generated successfully."
Then User will select the payment type and fill the requirement
Then User will validate message "Payment Receipt generated successfully" "Credentials generated successfully." "IRPBILL89 : [E] All the required documents are not collected at Fleet Level"
Then User will navigate to supplement documents to collect the document "Documents changes were updated successfully"
Then User will navigate to payment page for second installment and validate message "Payment Receipt generated successfully" "Payment completed successfully." 
And User will validate all the inquiries and report with respect to the flow.