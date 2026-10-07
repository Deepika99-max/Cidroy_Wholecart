# WholeCart Marketplace — Test Specification

**Document:** Test Specification
**Application:** WholeCart Marketplace
**Release:** Marketplace Release 1.0
**Specification:** WC-FDD-1.0-cc34
**Release Notes:** WC-RN-1.0-cc34
**Prepared By:** Deepika – Senior QA Automation Engineer

---

# 1. Purpose

The purpose of this Test Specification is to translate the approved WholeCart test scenarios into executable test cases.

Each test case provides sufficient information for a QA engineer to execute the validation consistently and determine whether the implemented functionality satisfies the applicable business, integration, security, and non-functional requirements.

The Test Specification maintains traceability between:

```text
Business Requirement
        ↓
Test Scenario
        ↓
Test Case
        ↓
Test Data
        ↓
Execution Result
        ↓
Defect / Evidence
```

---

# 2. Scope

This Test Specification covers the functional and technical validation of the WholeCart Marketplace Release 1.0.

The specification covers:

* Buyer workflows.
* Seller workflows.
* Operator workflows.
* Authentication and authorization.
* Catalogue and inventory behaviour.
* Cart and checkout.
* Delivery slots.
* Order lifecycle.
* Cancellation.
* Redelivery.
* Delivery slot changes.
* Reorder.
* Refunds and exchanges.
* Invoice generation.
* Monthly statements.
* Product CSV import.
* Logistics export.
* Partner API.
* API authorization.
* Performance requirements.
* Rate limiting.
* Boundary and negative conditions.

---

# 3. Test Case Design Standards

Each test case shall contain the following attributes:

| Field             | Description                                                                   |
| ----------------- | ----------------------------------------------------------------------------- |
| Test Case ID      | Unique identifier for the executable test case                                |
| Scenario ID       | Source test scenario                                                          |
| Requirement ID    | Applicable business, integration, or non-functional requirement               |
| Test Case Title   | Concise description of the validation                                         |
| Test Type         | Functional, Negative, Boundary, API, Integration, Security, Performance, etc. |
| Priority          | Critical, High, Medium, or Low                                                |
| Preconditions     | Conditions required before execution                                          |
| Test Data         | Data required for execution                                                   |
| Steps             | Ordered actions to perform                                                    |
| Expected Result   | Expected application behaviour                                                |
| Severity          | Business impact if the test fails                                             |
| Execution Status  | Not Executed / Pass / Fail / Blocked / Not Applicable                         |
| Automation Status | Manual / Candidate / Automated / Not Suitable                                 |
| Evidence          | Screenshot, API response, log, report, or other evidence                      |

---

# 4. Test Case ID Convention

Test Case IDs shall follow the following format:

```text
TC-<Scenario ID>-<Sequence>
```

Examples:

```text
TC-BUY-001-01
TC-BUY-001-02
TC-BUY-018-01
TC-SELL-004-01
TC-AUTH-001-01
TC-INT-001-01
TC-PERF-001-01
```

Where:

* `TC` = Test Case
* `BUY` / `SELL` / `OPER` / `AUTH` / `INT` / `PERF` = Functional area
* Scenario number = originating test scenario
* Sequence = individual test case under that scenario

This convention ensures that every executable test case can be traced back to its originating scenario.

---

# 5. Test Case Status

| Status         | Definition                                                                     |
| -------------- | ------------------------------------------------------------------------------ |
| Not Executed   | Test case has not yet been executed                                            |
| Pass           | Actual result meets the expected result                                        |
| Fail           | Actual result does not meet the expected result                                |
| Blocked        | Execution cannot continue because of an external dependency or blocking defect |
| Not Applicable | Test case is not applicable to the current execution scope                     |

---

# 6. Automation Classification

| Classification       | Definition                                              |
| -------------------- | ------------------------------------------------------- |
| Manual               | Intended to remain manually executed                    |
| Automation Candidate | Suitable for future automation                          |
| Automated            | Covered by an automation script                         |
| Not Suitable         | Automation is not appropriate or provides limited value |

Automation classification represents automation planning and shall not be treated as an execution result.

---

# 7. Priority Classification

| Priority | Definition                                                                                                             |
| -------- | ---------------------------------------------------------------------------------------------------------------------- |
| Critical | Failure impacts core business operations, financial integrity, inventory integrity, authorization, or order processing |
| High     | Failure significantly impacts a major business workflow                                                                |
| Medium   | Failure affects supporting functionality with a reasonable workaround                                                  |
| Low      | Failure has limited business impact                                                                                    |

---

# 8. Severity Classification

| Severity | Definition                                                                                                                  |
| -------- | --------------------------------------------------------------------------------------------------------------------------- |
| Critical | Prevents core business operation, creates financial/data integrity risk, or introduces major authorization/security failure |
| High     | Major business functionality is unavailable or incorrect                                                                    |
| Medium   | Functionality is partially affected with a workaround available                                                             |
| Low      | Minor functional, validation, or presentation issue                                                                         |

---

# 9. Buyer Test Cases

## 9.1 Catalogue, Inventory and Cart

### TC-BUY-001-01 — Order Available Stock

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-001              |
| Requirement | BR-01                |
| Test Type   | Functional           |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Preconditions**

* Buyer is authenticated.
* Product is active.
* Product has available stock.

**Test Data**

* Valid buyer account.
* Valid in-stock product.
* Quantity within available stock.

**Steps**

1. Login as a valid buyer.
2. Open the product catalogue.
3. Select an in-stock product.
4. Add a quantity that does not exceed available stock.
5. Open the cart.
6. Proceed toward checkout.

**Expected Result**

* Product can be added to the cart.
* Requested quantity is accepted.
* Cart reflects the correct quantity.
* Checkout can proceed when all other checkout conditions are satisfied.
* Inventory is not reduced beyond the ordered quantity.

---

### TC-BUY-001-02 — Prevent Ordering Beyond Available Stock

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-001              |
| Requirement | BR-01                |
| Test Type   | Negative / Boundary  |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Login as a valid buyer.
2. Select a product with known available stock.
3. Attempt to order more pieces than available.
4. Proceed to cart/checkout.

**Expected Result**

* The system prevents ordering beyond available stock.
* An appropriate validation message is displayed.
* Inventory is not allowed to become negative.
* The invalid order is not created.

---

### TC-BUY-002-01 — Concurrent Ordering Does Not Oversell Stock

| Field       | Details                   |
| ----------- | ------------------------- |
| Scenario    | BUY-002                   |
| Requirement | BR-01                     |
| Test Type   | Concurrency / Integration |
| Priority    | Critical                  |
| Severity    | Critical                  |
| Status      | Not Executed              |
| Automation  | Automation Candidate      |

**Steps**

1. Prepare a product with limited stock.
2. Authenticate multiple buyers.
3. Submit orders concurrently for the same product.
4. Monitor order responses.
5. Validate resulting inventory.

**Expected Result**

* Successful orders do not collectively exceed available stock.
* Inventory does not become negative.
* Excess requests are rejected appropriately.
* Stock and order records remain consistent.

---

### TC-BUY-003-01 — Out-of-Stock Product Excluded From Catalogue and Search

| Field       | Details               |
| ----------- | --------------------- |
| Scenario    | BUY-003               |
| Requirement | BR-02                 |
| Test Type   | Functional / Negative |
| Priority    | High                  |
| Severity    | High                  |
| Status      | Not Executed          |
| Automation  | Automation Candidate  |

**Steps**

1. Identify a product with zero available stock.
2. Open the product catalogue.
3. Search for the out-of-stock product.
4. Review the returned results.

**Expected Result**

* The out-of-stock product is not displayed in catalogue/search results according to BR-02.
* The product cannot be ordered through the normal catalogue flow.

---

### TC-BUY-004-01 — Accept Minimum Cart Quantity

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-004              |
| Requirement | BR-03                |
| Test Type   | Boundary             |
| Priority    | High                 |
| Severity    | High                 |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Add a valid product to the cart.
2. Set quantity to `1`.
3. Save/update the cart.

**Expected Result**

* Quantity `1` is accepted.
* Cart totals are recalculated correctly.

---

### TC-BUY-005-01 — Validate Maximum Cart Quantity

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-005              |
| Requirement | BR-03                |
| Test Type   | Boundary / Negative  |
| Priority    | High                 |
| Severity    | High                 |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Add a valid product to the cart.
2. Set quantity to `99`.
3. Save/update the cart.
4. Attempt quantity `100`.

**Expected Result**

* Quantity `99` is accepted.
* Quantity `100` is rejected.
* Appropriate validation is displayed.
* Cart is not updated with an invalid quantity.

---

# 10. Minimum Order Validation

### TC-BUY-006-01 — Allow Seller Sub-Order Meeting Minimum Value

| Field       | Details               |
| ----------- | --------------------- |
| Scenario    | BUY-006               |
| Requirement | BR-04                 |
| Test Type   | Boundary / Functional |
| Priority    | Critical              |
| Severity    | Critical              |
| Status      | Not Executed          |
| Automation  | Automation Candidate  |

**Steps**

1. Add products from the same seller.
2. Configure the seller sub-order to meet the minimum net value of ₹1,500.
3. Proceed to checkout.

**Expected Result**

* Seller sub-order satisfies the minimum order requirement.
* Checkout is allowed.

---

### TC-BUY-007-01 — Reject Seller Sub-Order Below Minimum Value

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-007              |
| Requirement | BR-04                |
| Test Type   | Boundary / Negative  |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Add products from the same seller.
2. Configure the seller sub-order below ₹1,500 net.
3. Attempt checkout.

**Expected Result**

* Checkout is prevented for the affected seller sub-order.
* Appropriate minimum-order validation is displayed.
* No invalid order is created.

---

# 11. Delivery Slot and Same-Day Delivery

### TC-BUY-008-01 — Display Valid Delivery Dates

| Field       | Details               |
| ----------- | --------------------- |
| Scenario    | BUY-008               |
| Requirement | BR-05                 |
| Test Type   | Functional / Boundary |
| Priority    | Critical              |
| Severity    | High                  |
| Status      | Not Executed          |
| Automation  | Automation Candidate  |

**Steps**

1. Add an eligible product to the cart.
2. Proceed to delivery selection.
3. Review available delivery dates.

**Expected Result**

* Today and the next seven days are represented according to the configured delivery rules.
* Dates outside the supported range are not selectable.

---

### TC-BUY-009-01 — Enforce Delivery Slot Capacity

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-009              |
| Requirement | BR-05                |
| Test Type   | Boundary / Negative  |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Identify a delivery slot at capacity.
2. Attempt to select the full slot.
3. Attempt to place an order using that slot.

**Expected Result**

* Full slots cannot accept additional orders.
* The buyer is prevented from selecting or completing an order against an unavailable slot.

---

### TC-BUY-010-01 — Validate Same-Day Delivery Eligibility

| Field       | Details               |
| ----------- | --------------------- |
| Scenario    | BUY-010               |
| Requirement | BR-06                 |
| Test Type   | Boundary / Time-based |
| Priority    | Critical              |
| Severity    | Critical              |
| Status      | Not Executed          |
| Automation  | Automation Candidate  |

**Steps**

1. Configure the store clock before the same-day cut-off.
2. Create an eligible order.
3. Verify same-day delivery availability.
4. Move the store clock beyond the configured cut-off.
5. Repeat the delivery-date validation.

**Expected Result**

* Same-day delivery is available before the applicable cut-off.
* Same-day delivery is unavailable after the cut-off when the rule requires it.
* Future eligible delivery dates remain available.

---

### TC-BUY-011-01 — Validate Same-Day Slot Restrictions

| Field       | Details               |
| ----------- | --------------------- |
| Scenario    | BUY-011               |
| Requirement | BR-06                 |
| Test Type   | Negative / Time-based |
| Priority    | Critical              |
| Severity    | High                  |
| Status      | Not Executed          |
| Automation  | Automation Candidate  |

**Steps**

1. Select same-day delivery.
2. Review available slots.
3. Attempt to select an ineligible slot.

**Expected Result**

* Only eligible same-day slots are selectable.
* Ineligible slots cannot be used to complete checkout.

---

# 12. Tax, Delivery Fee and Checkout

### TC-BUY-012-01 — Calculate Tax by Applicable Tax Rate

| Field       | Details                  |
| ----------- | ------------------------ |
| Scenario    | BUY-012                  |
| Requirement | BR-07, BR-22             |
| Test Type   | Functional / Calculation |
| Priority    | Critical                 |
| Severity    | Critical                 |
| Status      | Not Executed             |
| Automation  | Automation Candidate     |

**Steps**

1. Add products having applicable tax rates.
2. Proceed to checkout.
3. Review tax calculation.

**Expected Result**

* Tax is calculated separately for each applicable tax rate.
* Tax is rounded once per rate according to the defined rule.
* Displayed totals are mathematically consistent.

---

### TC-BUY-013-01 — Validate Checkout Total

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-013              |
| Requirement | BR-07, BR-22         |
| Test Type   | Calculation          |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Add valid products to the cart.
2. Proceed to checkout.
3. Record item totals, tax and applicable charges.
4. Calculate the expected order total independently.
5. Compare it with the application total.

**Expected Result**

* Checkout total equals the sum of applicable item values, tax and delivery fee.
* No unexplained rounding difference exists.

---

### TC-BUY-014-01 — Apply Seller-Specific Delivery Fee

| Field       | Details                  |
| ----------- | ------------------------ |
| Scenario    | BUY-014                  |
| Requirement | BR-08, BR-22             |
| Test Type   | Functional / Calculation |
| Priority    | High                     |
| Severity    | High                     |
| Status      | Not Executed             |
| Automation  | Automation Candidate     |

**Steps**

1. Create a seller sub-order below ₹3,000.
2. Proceed to checkout.
3. Review delivery fee and tax.

**Expected Result**

* Applicable delivery fee is calculated according to BR-08.
* Delivery-fee tax is calculated correctly.
* Checkout total reflects the delivery fee.

---

# 13. Order Creation

### TC-BUY-015-01 — Validate Order Number Format

| Field       | Details                      |
| ----------- | ---------------------------- |
| Scenario    | BUY-015                      |
| Requirement | BR-09                        |
| Test Type   | Functional / Data Validation |
| Priority    | Medium                       |
| Severity    | Medium                       |
| Status      | Not Executed                 |
| Automation  | Automation Candidate         |

**Steps**

1. Complete a valid checkout.
2. Capture the generated order number.
3. Validate the format.

**Expected Result**

* Order number follows the format defined in BR-09.
* Generated order number is displayed to the buyer.
* Order number is persisted consistently across order history and related records.

---

### TC-BUY-016-01 — Prevent Duplicate Checkout Submission

| Field       | Details                |
| ----------- | ---------------------- |
| Scenario    | BUY-016                |
| Requirement | BR-10                  |
| Test Type   | Negative / Idempotency |
| Priority    | Critical               |
| Severity    | Critical               |
| Status      | Not Executed           |
| Automation  | Automation Candidate   |

**Steps**

1. Prepare a valid cart.
2. Submit checkout.
3. Trigger a duplicate submission through repeated click/request.
4. Review created orders.

**Expected Result**

* Only one order is created.
* Duplicate checkout request does not create a second order.
* Inventory is deducted only once.
* Buyer receives one valid order confirmation.

---

# 14. Order Cancellation

### TC-BUY-017-01 — Cancel Eligible Order Within Cancellation Window

| Field       | Details               |
| ----------- | --------------------- |
| Scenario    | BUY-017               |
| Requirement | BR-11                 |
| Test Type   | Functional / Boundary |
| Priority    | Critical              |
| Severity    | Critical              |
| Status      | Not Executed          |
| Automation  | Automation Candidate  |

**Steps**

1. Place a valid order.
2. Keep the order within the five-minute cancellation window.
3. Cancel the order.
4. Review order status and inventory.

**Expected Result**

* Cancellation is accepted.
* Order status changes to cancelled.
* Stock is restored correctly.
* Cancelled order remains visible in order history according to the specification.

---

# 15. Delivery Slot Change

### TC-BUY-018-01 — Change Delivery Slot Before Shipment

| Field       | Details               |
| ----------- | --------------------- |
| Scenario    | BUY-018               |
| Requirement | BR-05, BR-06, BR-15   |
| Test Type   | Functional / Boundary |
| Priority    | Critical              |
| Severity    | Critical              |
| Status      | Not Executed          |
| Automation  | Automation Candidate  |

**Steps**

1. Create an eligible order that has not shipped.
2. Open the order.
3. Select the delivery-slot change option.
4. Select another valid available slot.
5. Save the change.

**Expected Result**

* Valid delivery slots can be selected.
* The delivery slot is updated successfully.
* Order details reflect the new slot.

---

### TC-BUY-018-02 — Reject Unavailable Delivery Slot

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-018              |
| Requirement | BR-05, BR-06, BR-15  |
| Test Type   | Negative             |
| Priority    | Critical             |
| Severity    | High                 |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Open an eligible unshipped order.
2. Attempt to select an unavailable/full slot.
3. Attempt to save the change.

**Expected Result**

* Unavailable slot cannot be selected or saved.
* Existing valid slot remains unchanged.

---

### TC-BUY-018-03 — Prevent Slot Change After Shipment

| Field       | Details                     |
| ----------- | --------------------------- |
| Scenario    | BUY-018                     |
| Requirement | BR-15                       |
| Test Type   | Negative / State Transition |
| Priority    | Critical                    |
| Severity    | Critical                    |
| Status      | Not Executed                |
| Automation  | Automation Candidate        |

**Steps**

1. Open an order that has already been shipped.
2. Attempt to change the delivery slot.

**Expected Result**

* Delivery-slot change is unavailable.
* Existing delivery information remains unchanged.

---

# 16. Delivery Failure and Redelivery

### TC-BUY-019-01 — Request Redelivery After Failed Delivery

| Field       | Details                 |
| ----------- | ----------------------- |
| Scenario    | BUY-019                 |
| Requirement | BR-13, BR-14            |
| Test Type   | Functional / End-to-End |
| Priority    | Critical                |
| Severity    | Critical                |
| Status      | Not Executed            |
| Automation  | Automation Candidate    |

**Steps**

1. Use an order in the failed-delivery state.
2. Open the order.
3. Initiate redelivery.
4. Select a valid delivery slot.
5. Confirm the redelivery request.

**Expected Result**

* Redelivery option is available for the failed-delivery state.
* Valid delivery slot can be selected.
* Redelivery request is successfully recorded.
* Order status reflects the applicable redelivery state.

---

# 17. Reorder

### TC-BUY-020-01 — Reorder Previous Order

| Field       | Details |
| ----------- | ------- |
| Scenario    | BUY-020 |
| Requirement |         |

# 18. Refund and Exchange

### TC-BUY-021-01 — Request Refund for Delivered Order

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-021              |
| Requirement | BR-17, BR-18         |
| Test Type   | Functional           |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Open a delivered order.
2. Select refund.
3. Select a valid quantity.
4. Submit the request.
5. Review request status.

**Expected Result**

* Refund request is accepted only for eligible delivered orders.
* Requested quantity cannot exceed the originally ordered quantity.
* Refund request is routed for applicable seller processing.
* Applicable tax is included in the refund calculation.

---

### TC-BUY-021-02 — Reject Refund for Ineligible Order

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-021              |
| Requirement | BR-17                |
| Test Type   | Negative             |
| Priority    | Critical             |
| Severity    | High                 |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Open an order that is not eligible for refund.
2. Attempt to initiate a refund.

**Expected Result**

* Refund action is unavailable or rejected.
* No refund request is created.
* The original order remains unchanged.

---

### TC-BUY-022-01 — Request Exchange for Delivered Order

| Field       | Details              |
| ----------- | -------------------- |
| Scenario    | BUY-022              |
| Requirement | BR-17, BR-18         |
| Test Type   | Functional           |
| Priority    | Critical             |
| Severity    | High                 |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Steps**

1. Open a delivered order.
2. Select exchange.
3. Select a valid quantity.
4. Submit the exchange request.
5. Review the exchange request status.

**Expected Result**

* Exchange request is created successfully.
* Requested quantity cannot exceed the originally ordered quantity.
* Exchange request is associated with the correct order.
* Exchange does not create a monetary refund.

---

# 19. Invoice

### TC-BUY-023-01 — Generate Invoice on Delivery

| Field       | Details                      |
| ----------- | ---------------------------- |
| Scenario    | BUY-023                      |
| Requirement | BR-19                        |
| Test Type   | Functional / Data Validation |
| Priority    | High                         |
| Severity    | High                         |
| Status      | Not Executed                 |
| Automation  | Automation Candidate         |

**Steps**

1. Use an order that has reached delivered status.
2. Open the invoice.
3. Validate seller information.
4. Validate buyer information.
5. Validate order and invoice dates.
6. Validate line items.
7. Validate tax and delivery fee.
8. Validate the invoice total.

**Expected Result**

* Invoice is generated for the delivered order.
* Required seller and buyer information is present.
* Order and invoice dates are correct.
* Line items contain the required values.
* Tax and delivery fee are represented correctly.
* Invoice total matches the applicable order total.

---

# 20. Monthly Statement

### TC-BUY-024-01 — Generate Monthly Statement

| Field       | Details                  |
| ----------- | ------------------------ |
| Scenario    | BUY-024                  |
| Requirement | BR-20                    |
| Test Type   | Functional / Calculation |
| Priority    | High                     |
| Severity    | High                     |
| Status      | Not Executed             |
| Automation  | Automation Candidate     |

**Steps**

1. Prepare a buyer account with delivered, refunded, and cancelled orders.
2. Generate the applicable monthly statement.
3. Review the orders included in the statement.
4. Compare statement totals against the corresponding source orders.

**Expected Result**

* Eligible delivered orders are included.
* Approved refunds are deducted according to the applicable business rule.
* Cancelled orders are excluded.
* Statement totals are accurate.
* No duplicate order is included.

---

# 21. Buyer Access and Data Isolation

### TC-BUY-025-01 — Validate Buyer Access to Own Data

| Field       | Details                  |
| ----------- | ------------------------ |
| Scenario    | BUY-025                  |
| Requirement | BR-21                    |
| Test Type   | Security / Authorization |
| Priority    | Critical                 |
| Severity    | Critical                 |
| Status      | Not Executed             |
| Automation  | Automation Candidate     |

**Steps**

1. Login as a valid buyer.
2. Open orders, invoices, and statements.
3. Verify that the authenticated buyer can access permitted data.
4. Attempt to access another buyer's data.

**Expected Result**

* Buyer can access only permitted personal data.
* Buyer can access their own orders, invoices, and statements.
* Data belonging to another buyer is not exposed.
* Unauthorized access is rejected.

---

# 22. Seller Test Case Coverage

Seller scenarios shall be expanded into executable test cases using the same test case design standard and `TC-SELL-xxx-yy` naming convention.

| Scenario            | Requirement                | Primary Coverage                                     |
| ------------------- | -------------------------- | ---------------------------------------------------- |
| SELL-001            | BR-12                      | Seller cancellation and buyer status synchronization |
| SELL-002            | BR-13                      | Order state transitions                              |
| SELL-003            | BR-17, BR-18               | Refund and exchange processing                       |
| SELL-004            | IF-01                      | Product CSV import                                   |
| SELL-005            | IF-01                      | CSV validation and invalid-row handling              |
| SELL-006            | BR-01, IF-01               | Stock/product update validation                      |
| SELL-007            | IF-03                      | Partner API authentication and order access          |
| SELL-008            | IF-03                      | Partner API update operations                        |
| SELL-009 – SELL-014 | BR-13, BR-17, BR-18, BR-21 | Seller order and operational workflows               |

---

# 23. Operator Test Case Coverage

Operator scenarios shall be expanded into executable test cases using the `TC-OPER-xxx-yy` naming convention.

| Scenario | Requirement         | Primary Coverage                                   |
| -------- | ------------------- | -------------------------------------------------- |
| OPER-001 | BR-23               | Operator configuration changes                     |
| OPER-002 | BR-05, BR-06, BR-23 | Delivery configuration                             |
| OPER-003 | BR-24               | Store clock manipulation                           |
| OPER-004 | IF-02               | Logistics export                                   |
| OPER-005 | IF-02               | Export content and data validation                 |
| OPER-006 | BR-21, BR-23, BR-24 | Operator authorization and administrative controls |

---

# 24. Authentication and Authorization Test Case Coverage

Authentication and authorization shall be validated across all applicable roles.

| Scenario | Requirement         | Primary Validation                            |
| -------- | ------------------- | --------------------------------------------- |
| AUTH-001 | BR-21               | Buyer authentication and data isolation       |
| AUTH-002 | BR-21, IF-03        | Seller authentication and access control      |
| AUTH-003 | BR-21, BR-23, BR-24 | Operator authentication and privileged access |

The following authorization conditions shall be validated:

* Valid credentials are accepted.
* Invalid credentials are rejected.
* Unauthorized resources cannot be accessed.
* Buyer-to-buyer data isolation is enforced.
* Seller-to-seller data isolation is enforced.
* Buyer cannot access seller functions.
* Buyer cannot access operator functions.
* Seller cannot access operator functions.
* API authorization is independently enforced.

---

# 25. Integration Test Case Coverage

## 25.1 Product CSV Import

| Scenario | Requirement  | Validation                           |
| -------- | ------------ | ------------------------------------ |
| SELL-004 | IF-01        | Valid CSV import                     |
| SELL-005 | IF-01        | Invalid CSV data and error reporting |
| SELL-006 | BR-01, IF-01 | Product and stock update validation  |

CSV validation shall cover:

* Required columns.
* UTF-8 encoding.
* Valid records.
* Invalid records.
* Duplicate SKU behaviour.
* Existing SKU updates.
* Seller-specific SKU uniqueness.
* Row-level error reporting.
* Line numbers.
* Error reasons.
* No silent data loss.

---

# 26. Partner API Test Case Coverage

The Partner API shall be validated for:

* Authentication.
* Authorization.
* Seller-specific access.
* Order retrieval.
* Pagination.
* Page size.
* Complete pagination traversal.
* Duplicate prevention.
* Missing-record prevention.
* Offer price updates.
* Offer stock updates.
* Invalid API keys.
* Unauthorized seller access.
* HTTP response codes.
* Response schema and data consistency.

---

# 27. Logistics Export Test Case Coverage

The logistics export shall validate:

* Accepted orders.
* Shipped orders.
* Redelivery-requested orders.
* Unsupported statuses.
* Order number.
* Buyer information.
* Delivery address.
* Delivery date.
* Delivery slot start time.
* Delivery slot end time.
* Piece quantity.
* Required column order.
* UTF-8 encoding.
* Scheduled export behaviour.

---

# 28. Performance Test Cases

## TC-PERF-001-01 — Order Submission Performance

| Field       | Details              |
| ----------- | -------------------- |
| Requirement | NF-01                |
| Test Type   | Performance / Load   |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Objective**

Validate that at least 95% of order-submission requests complete within the defined three-second response-time target with 30 concurrent buyers.

**Execution**

1. Prepare valid buyer accounts and required test data.
2. Configure 30 concurrent virtual users.
3. Execute order-submission requests.
4. Capture response times.
5. Calculate the 95th percentile response time.
6. Review errors, throughput, and failed requests.

**Expected Result**

* At least 95% of order-submission requests complete within three seconds.
* No critical application error occurs during the test.
* System remains stable under the defined concurrency.

---

## TC-PERF-002-01 — Search Performance

| Field       | Details              |
| ----------- | -------------------- |
| Requirement | NF-02                |
| Test Type   | Performance          |
| Priority    | High                 |
| Severity    | High                 |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Objective**

Validate search response time against the defined two-second target.

**Execution**

1. Prepare representative search data.
2. Execute search requests under the defined test load.
3. Capture response times.
4. Calculate the 95th percentile response time.
5. Review errors and throughput.

**Expected Result**

* At least 95% of search requests complete within two seconds.
* No critical application errors occur during the test.

---

## TC-PERF-003-01 — API Rate Limiting

| Field       | Details              |
| ----------- | -------------------- |
| Requirement | NF-03                |
| Test Type   | Performance / API    |
| Priority    | Critical             |
| Severity    | Critical             |
| Status      | Not Executed         |
| Automation  | Automation Candidate |

**Objective**

Validate API behaviour at and above the supported request rate.

**Steps**

1. Generate requests up to the supported 50 requests/sec.
2. Monitor response codes.
3. Increase traffic above the supported rate.
4. Monitor rate-limiting behaviour.
5. Verify application stability.

**Expected Result**

* Supported traffic is processed successfully.
* Requests exceeding the configured limit are rate-limited.
* HTTP `429 Too Many Requests` is returned when the defined limit is exceeded.
* Application remains stable and responsive.

---

# 29. Test Data Requirements

The following test data categories shall be prepared before execution:

| Data Category    | Required Data                                                 |
| ---------------- | ------------------------------------------------------------- |
| Buyer Accounts   | Multiple valid buyer accounts                                 |
| Seller Accounts  | Multiple valid seller accounts                                |
| Operator Account | Valid operator account                                        |
| Products         | In-stock, out-of-stock, and low-stock products                |
| Quantities       | 1, 99, 100, available-stock boundary, and beyond-stock values |
| Order Values     | Below ₹1,500, exactly ₹1,500, and above ₹1,500                |
| Delivery Slots   | Available, full, unavailable, and expired slots               |
| Delivery Dates   | Today, next seven days, and outside supported range           |
| Order States     | Accepted, shipped, delivered, cancelled, and failed delivery  |
| Refund Data      | Eligible and ineligible orders                                |
| Exchange Data    | Eligible and ineligible orders                                |
| CSV Data         | Valid, invalid, duplicate, and update records                 |
| API Data         | Valid/invalid credentials and multiple pages                  |
| Performance Data | Concurrent users and request-rate profiles                    |

---

# 30. Evidence Requirements

For each failed or investigation-required test case, appropriate evidence shall be captured.

Acceptable evidence includes:

* Screenshots.
* Screen recordings.
* API request/response.
* Application logs.
* Database validation.
* Exported CSV files.
* Performance reports.
* Trace files.
* Console logs.
* Defect references.

Evidence shall be linked to the corresponding Test Case ID wherever applicable.

---

# 31. Test Execution Recording Format

Test execution shall use the following structure:

| Test Case ID   | Requirement | Expected Result              | Actual Result | Status       | Defect ID | Evidence |
| -------------- | ----------- | ---------------------------- | ------------- | ------------ | --------- | -------- |
| TC-BUY-001-01  | BR-01       | Valid quantity accepted      | —             | Not Executed | —         | —        |
| TC-BUY-001-02  | BR-01       | Excess stock rejected        | —             | Not Executed | —         | —        |
| TC-BUY-018-01  | BR-15       | Slot changed before shipment | —             | Not Executed | —         | —        |
| TC-AUTH-001-01 | BR-21       | Unauthorized data blocked    | —             | Not Executed | —         | —        |
| TC-PERF-001-01 | NF-01       | ≥95% within 3 sec            | —             | Not Executed | —         | —        |

---

# 32. Requirement Traceability

The final test specification shall maintain complete traceability.

| Requirement | Scenario | Test Case      | Execution | Defect |
| ----------- | -------- | -------------- | --------- | ------ |
| BR-01       | BUY-001  | TC-BUY-001-01  | Pending   | —      |
| BR-01       | BUY-001  | TC-BUY-001-02  | Pending   | —      |
| BR-01       | BUY-002  | TC-BUY-002-01  | Pending   | —      |
| BR-15       | BUY-018  | TC-BUY-018-01  | Pending   | —      |
| BR-15       | BUY-018  | TC-BUY-018-02  | Pending   | —      |
| BR-15       | BUY-018  | TC-BUY-018-03  | Pending   | —      |
| BR-21       | AUTH-001 | TC-AUTH-001-01 | Pending   | —      |
| NF-01       | PERF-001 | TC-PERF-001-01 | Pending   | —      |
| NF-02       | PERF-002 | TC-PERF-002-01 | Pending   | —      |
| NF-03       | PERF-003 | TC-PERF-003-01 | Pending   | —      |

---

# 33. Test Execution Readiness

Before execution begins, verify:

* [ ] Test environment is available.
* [ ] Application build is deployed.
* [ ] Buyer accounts are available.
* [ ] Seller accounts are available.
* [ ] Operator account is available.
* [ ] Test products are available.
* [ ] Required stock data is configured.
* [ ] Delivery slots are configured.
* [ ] Store clock controls are available.
* [ ] API credentials are available.
* [ ] CSV import/export capability is available.
* [ ] Required test data is prepared.
* [ ] Database access is available for validation where applicable.
* [ ] Test evidence location is available.
* [ ] Defect tracking mechanism is available.

---

# 34. Entry Criteria

Test execution can begin when:

* Test Plan is completed.
* Test Scenarios are completed.
* Test Matrix is completed.
* Test Specification is completed and reviewed.
* Test environment is stable.
* Required test data is available.
* Application build is deployable.
* Required integrations are available.
* No known blocker prevents execution.

---

# 35. Exit Criteria

The test cycle can be closed when:

* All Critical test cases have been executed.
* Planned High-priority test cases have been executed.
* Required regression coverage is completed.
* Integration validation is completed.
* Performance requirements are evaluated.
* Critical defects are resolved or formally accepted.
* High-severity defects have an approved disposition.
* Requirement traceability is complete.
* Execution evidence is available.
* Test results are consolidated.
* QA summary is prepared.
