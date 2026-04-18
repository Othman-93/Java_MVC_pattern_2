# Stuff Lending Test Report

### Note
Case 4.1.2 states that credits should be transfered on the day of contract start. In our system we made the assumption (based on conflicting requirements) that credits are transfered on contract creation day. We instead test for the contract state, and see it change from active to inactive.

Case | Result  | Note
---|---|---
**1.1 Create Member**  |  | 
1.1.1 Create a member with name: "Allan Turing", email: "allan@enigma.com", phone: "123456" | ✅ | 
1.1.2 Check that the member is created correctly with an id according to the requirement by checking the members full information. | ✅ | 
1.1.Quit the application | ✅ |
**1.2 Create Member - Duplicate Email and Phone** |  | 
1.2.1 Create a member with name: "Allan", email: "allan@enigma.com", phone: "123456" | ✅ |
1.2.2 Check that the member is created correctly with an id according to the requirement by checking the members full information. | ✅ |
1.2.3 Create a member with name: "Turing", email: "allan@enigma.com", phone: "123" | ✅ |
1.2.4 Check that the member is not created (duplicate email) | ✅ |
1.2.5 Create a membner with name: "Turing", email: "turing@enigma.com", phone: "123456" | ✅ |
1.2.6 Check that the member is not created (duplicate phone) | ✅ |
1.2.7 Create a member with name "Turing" , email: "turing@enigma.com", phone: "123" | ✅ |
1.2.8 Check that the member is created correctly with an id according to the requirement by checking the members full information. | ✅ |
1.2.9 Quit the application | ✅ |
**1.3 Delete Member** | |
1.3.1 Create a member with name: "Allan", email: "allan@enigma.com", phone: "123456" | ✅ |
1.3.2 Check that the member is created correctly with an id according to the requirement by checking the members full information. | ✅ |
1.3.3 Delete the member | ✅ |
1.3.4 Check that the member is deleted by listing all members (simple) | ✅ |
1.3.5 Create a member with name: "Allan", email: "allan@enigma.com", phone: "123456" | ✅ |
1.3.6 Check that the member is created correctly with an id according to the requirement by checking the members full information. | ✅ |
1.3.7 Quit the application. | ✅ |
**2.1 Create item** |  |
2.1.1 Create an item for a Member | ✅ |
2.1.2 Check that the item is created and part of the Members items by inspecint the member's details. | ✅ |
2.1.3 Check that the owner member has increased it's credits with 100 | ✅ |
**2.2 Delete item** |  |
2.2.1 Select a member with one or several items | ✅ |
2.2.2 Delete one of the member's items that is not involved in any contract | ✅ |
2.2.3 Check that the item was deleted from the members owned items | ✅ |
**2.3 Delete item** | |
2.3.1 Select a member with one or several items | ✅ |
2.3.2 Delete one of the member's items that is booked (i.e. a future contract) | ✅ |
2.3.3 Check that the item was deleted from the members owned items | ✅ |
2.3.4 Check that the contract was cancelled | ✅ |
**3.1 Create contract** | |
3.1.1 Create a contract for I2 lending to M2, 3 days of lending, day 1 to and including day 3 | ✅ |
3.1.2 Check that the contract was created | ✅ |
**3.2 Create Contract - not enough funds** |  |
3.2.1 Create a contract for I1 lending to M2, 3 days of lending (e.g. day 1 to and including day 3) | ✅ |
3.2.2. Check that the contract was not created due to lack of funds | ✅ |
**3.3 Create Contract - conflicting time** |  |
3.3.1 Create a contract for I2 lending to M2, 3 days of lending, day 4 to and including day 6 | ✅ |
3.3.2 Check that the contract was created not created due to conflicting time | ✅ |
**3.4 Create Contract - conflicting time** |  |
3.4.1 Create a contract for I2 lending to M2, 4 days of lending, day 6 to and including day 9 | ✅ | Note: changed the requirements to 4 days of lending
3.4.2 Check that the contract was created not created due to conflicting time | ✅ |
**3.5 Create Contract - conflicting time** |  |
3.5.1 Create a contract for I2 lending to M2, 6 days of lending, day 4 to and including day 9 | ✅ | Note: changed the requirements to 4 days of lending
3.5.2 Check that the contract was created not created due to conflicting time | ✅ |
**3.6 Create Contract - conflicting time** |  |
3.6.1 Create a contract for I2 lending to M2, 1 days of lending, day 6 to and including day 6 | ✅ | Note: changed the requirements to 1 days of lending
3.6.2 Check that the contract was created not created due to conflicting time | ✅ |
**4.1 Advance time** |  |
4.1.1 Advance the time 8 days. | ✅ |
4.1.2 Check that the contract has been fullfilled and that funds have been deduced from M3 who now has 70 credits. | ✅ | Note: The credits are transfered on contract creation. We instead check that contract state is changing from inactive to active, and active to inactive as time progress.
**5.1 Member Data** |  |
5.1.1 Check that there are at least 3 Members | ✅ |
5.1.2 Check that one member (M1) with 500 credits. M1 has two items for lending, I1 with cost 50 one cheap I2 cost 10 | ✅ |
5.1.3 Check that one member (M2) with 100 credits. M2 has no items for lending. | ✅ |
5.1.4 Check that one member (M3) with 100 credits has an active lending contract for I2 that starts on day 5 and ends on day 7 (3 days). | ✅ |
