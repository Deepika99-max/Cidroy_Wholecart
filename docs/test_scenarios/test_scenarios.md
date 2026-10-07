# WholeCart Marketplace – Test Scenarios

**Document:** Test Scenarios
**Application:** WholeCart Marketplace  
**Release:** Marketplace Release 1.0  
**Specification:** WC-FDD-1.0-cc34  
**Release Notes:** WC-RN-1.0-cc34  
**Prepared By:** Deepika – Senior QA Automation Engineer

---

## 1. Scenario Identification

| Scenario ID | Priority | Area |
|-------------|---|---|
| BUY-001     | P0 | Buyer Authentication |
| BUY-002     | P1 | Catalogue |
| BUY-003     | P1 | Search |
| BUY-004     | P0 | Cart |
| BUY-005     | P0 | Multi-Seller Checkout |
| BUY-006     | P0 | Minimum Order Validation |
| BUY-007     | P0 | Tax Calculation |
| BUY-008     | P0 | Delivery Fee |
| BUY-009     | P0 | Delivery Date & Slot |
| BUY-010     | P0 | Same-Day Cut-off |
| BUY-011     | P0 | Slot Capacity |
| BUY-012     | P0 | Stock Validation |
| BUY-013     | P0 | Duplicate Order Submission |
| BUY-014     | P0 | Order Creation |
| BUY-015     | P0 | Order History |
| BUY-016     | P0 | Order Cancellation |
| BUY-017     | P0 | Order State Management |
| BUY-018     | P1 | Delivery Slot Change |
| BUY-019     | P1 | Delivery Failure & Redelivery |
| BUY-020     | P1 | Reorder |
| BUY-021     | P0 | Refund |
| BUY-022     | P0 | Exchange |
| BUY-023     | P0 | Invoice |
| BUY-024     | P1 | Monthly Statement |
| BUY-025     | P1 | Buyer Inquiry |
| SELL-001    | P0 | Seller Authentication |
| SELL-002    | P0 | Seller Order Visibility |
| SELL-003    | P0 | Order Acceptance |
| SELL-004    | P0 | Order Shipment |
| SELL-005    | P0 | Order Delivery |
| SELL-006    | P0 | Seller Cancellation |
| SELL-007    | P1 | Delivery Failure Handling |
| SELL-008    | P1 | Seller Product Management |
| SELL-009    | P0 | Seller Stock Management |
| SELL-010    | P1 | Product CSV Import |
| SELL-011    | P0 | Partner API – Orders |
| SELL-012    | P0 | Partner API – Offer Update |
| SELL-013    | P0 | Refund Approval / Rejection |
| SELL-014    | P0 | Exchange Approval / Rejection |
| OPER-001    | P0 | Operator Authentication |
| OPER-002    | P0 | Marketplace Settings |
| OPER-003    | P0 | Store Clock |
| OPER-004    | P0 | Delivery Slot Configuration |
| OPER-005    | P1 | Seller Approval |
| OPER-006    | P1 | Logistics Export |
| AUTH-001    | P0 | Buyer Data Isolation |
| AUTH-002    | P0 | Seller Data Isolation |
| AUTH-003    | P0 | Role-Based Access Control |
| INT-001     | P0 | CSV Import Integration |
| INT-002     | P1 | Logistics Export Integration |
| INT-003     | P0 | Partner API Integration |
| PERF-001    | P0 | Checkout Performance |
| PERF-002    | P1 | Search Performance |
| PERF-003    | P1 | API Rate Limiting |

---

# 2. Buyer Scenarios

## BUY-001 – Buyer Authentication

Validate that buyers can securely log in and access buyer functionality using valid credentials and are prevented from accessing the application with invalid credentials.

**Requirements:** Existing functionality

---

## BUY-002 – Product Catalogue

Validate that buyers can:

- View available products.
- View product name, unit and price.
- See only products with available stock.
- View products from applicable sellers.
- Navigate product details.

**Requirements:** BR-02, BR-22

---

## BUY-003 – Product Search

Validate that buyers can search for products and that search results:

- Return relevant available products.
- Exclude zero-stock products.
- Handle valid and invalid search terms.
- Respect product availability.

**Requirements:** BR-02

---

## BUY-004 – Cart Management

Validate that buyers can:

- Add products to cart.
- Remove products.
- Change quantities.
- View line totals.
- View net cart total.
- Handle quantity boundaries.

**Requirements:** BR-03

---

## BUY-005 – Multi-Seller Checkout

Validate that a cart containing products from multiple sellers is correctly split into seller-specific sub-orders during checkout.

Verify:

- Seller-wise net values.
- Seller-wise tax.
- Seller-wise delivery fee.
- Seller-wise totals.
- Grand total.

**Requirements:** BR-04, BR-07, BR-08, BR-09, BR-22

---

## BUY-006 – Minimum Order Validation

Validate the minimum order requirement independently for each seller.

Boundary scenarios:

- Below ₹2,000
- Exactly ₹2,000
- Above ₹2,000
- Multiple sellers with different net values

**Requirement:** CR-07

---

## BUY-007 – Tax Calculation

Validate that tax is correctly calculated for products taxed at:

- 5%
- 18%

Verify tax calculation by rate, aggregation and half-up rounding.

**Requirement:** BR-07

---

## BUY-008 – Delivery Fee

Validate that:

- Seller net value below ₹3,000 attracts ₹100 delivery fee.
- Delivery fee attracts 18% tax.
- Total delivery charge becomes ₹118 when applicable.
- Seller net value at or above ₹3,000 does not attract the delivery fee.
- Delivery fee is calculated independently for each seller.

**Requirement:** BR-08

---

## BUY-009 – Delivery Date & Slot

Validate that buyers can select valid delivery dates and slots within the permitted booking window.

Verify:

- Today
- Today + 7 days
- Dates outside the allowed range
- All configured delivery slots
- Unavailable slots
- Slot availability reasons

**Requirement:** BR-05

---

## BUY-010 – Same-Day Delivery Cut-off

Validate same-day delivery behaviour around the 10:00 cut-off.

Verify:

- Before 10:00
- Exactly at 10:00
- After 10:00
- Applicable same-day slots only

**Requirement:** BR-06

---

## BUY-011 – Delivery Slot Capacity

Validate that each delivery slot accepts no more than the configured maximum number of orders.

Verify behaviour when:

- Capacity is available.
- Capacity reaches the limit.
- Capacity is exceeded.

**Requirements:** BR-05, BR-23

---

## BUY-012 – Stock Validation

Validate that buyers cannot purchase more stock than available.

Verify:

- Available stock.
- Exact stock quantity.
- Insufficient stock.
- Zero stock.
- Concurrent ordering scenarios.

**Requirements:** BR-01, BR-02

---

## BUY-013 – Duplicate Order Submission

Validate that repeated submission of the same checkout does not create duplicate orders.

Verify:

- Double-click.
- Repeated request.
- Rapid repeated submission.

**Requirement:** BR-10

---

## BUY-014 – Order Creation

Validate successful order placement and verify:

- Correct order number.
- Correct seller sub-order numbers.
- Correct totals.
- Correct delivery slot.
- Correct order status.
- Success confirmation message.

**Requirements:** BR-09, BR-10

---

## BUY-015 – Order History

Validate that buyers can view their own orders and that:

- Orders are displayed newest first.
- Seller information is correct.
- Delivery slot is correct.
- Status is correct.
- Cancelled orders remain visible.

**Requirement:** BR-21

---

## BUY-016 – Order Cancellation

Validate buyer cancellation:

- Within the permitted 5-minute window.
- Exactly at 5:00.
- After 5:00.
- Before shipment.
- After shipment.
- After seller cancellation.

Verify that cancelled orders remain in history and stock is returned where applicable.

**Requirements:** BR-11, BR-13

---

## BUY-017 – Order State Management

Validate the supported order lifecycle:

- Placed
  ↓
- Accepted
  ↓
- Shipped
  ↓
- Delivered

## BUY-018 – Delivery Slot Change

Validate that buyers can change the delivery slot before shipment.

Verify that:

- Valid slots can be selected.
- Invalid/unavailable slots cannot be selected.
- Same-day cut-off rules continue to apply.
- Slot changes are unavailable after shipment.

**Requirements:** BR-05, BR-06, BR-15

---

## BUY-019 – Delivery Failure & Redelivery

Validate the buyer redelivery workflow after delivery failure.

Verify:

- Redelivery can only be requested after delivery failure.
- New slot follows delivery rules.
- Same-day cut-off is respected.
- Order moves to the correct subsequent status.

**Requirements:** BR-13, BR-14

---

## BUY-020 – Reorder

Validate that Reorder adds every line from a historical order to the cart.

Verify:

- All lines are added.
- Original quantities are retained.
- Reorder can be performed repeatedly.
- Current stock availability is respected.

**Requirement:** BR-16

---

## BUY-021 – Refund

Validate refunds for delivered orders.

Verify:

- Full refund.
- Partial refund.
- Selected lines.
- Selected quantities.
- Quantity limits.
- Tax included in refund amount.
- Seller approval/rejection.
- Invalid refund attempts.

**Requirements:** BR-17, BR-18

---

## BUY-022 – Exchange

Validate exchanges for delivered orders.

Verify:

- Exchange request creation.
- Selected lines and quantities.
- Quantity limits.
- Seller approval.
- Seller rejection.
- Replacement goods workflow.
- No monetary refund for approved exchanges.

**Requirements:** BR-17, BR-18

---

## BUY-023 – Invoice

Validate invoice generation after order delivery.

Verify:

- Seller name and GSTIN.
- Buyer name, GSTIN and address.
- Invoice date.
- Order number.
- Product lines.
- Quantity and unit price.
- Tax rate and tax amount.
- Delivery fee.
- Tax by rate.
- Total equals order total.
- CR-09 supply type.
- CR-09 place of supply.

**Requirements:** BR-19, BR-07, BR-08, CR-09

---

## BUY-024 – Monthly Statement

Validate monthly statement contents.

Verify:

- Delivered orders are included.
- Cancelled orders are excluded.
- Approved refunds are deducted.
- Statement totals are accurate.

**Requirement:** BR-20

---

## BUY-025 – Buyer Inquiry

Validate buyer inquiry creation and management.

**Note:** Inquiry attachments are excluded because CR-11 is deferred.

---

# 3. Seller Scenarios

## SELL-001 – Seller Authentication

Validate seller login and role-specific access.

---

## SELL-002 – Seller Order Visibility

Validate that sellers can view only their own orders.

Verify:

- Order visibility.
- Buyer information.
- Requested delivery date.
- Status.
- Total.
- Newest-first ordering.

**Requirement:** BR-21

---

## SELL-003 – Order Acceptance

Validate that sellers can accept valid placed orders and that invalid state transitions are prevented.

**Requirement:** BR-13

---

## SELL-004 – Order Shipment

Validate shipment of accepted orders and verify correct state transition.

**Requirement:** BR-13

---

## SELL-005 – Order Delivery

Validate successful delivery of shipped orders and transition to Delivered.

**Requirement:** BR-13

---

## SELL-006 – Seller Cancellation

Validate seller cancellation and verify that the buyer sees the Cancelled status immediately.

**Requirement:** BR-12

---

## SELL-007 – Delivery Failure Handling

Validate seller marking an order as delivery failed and verify the buyer can subsequently request redelivery.

**Requirements:** BR-13, BR-14

---

## SELL-008 – Product Management

Validate seller product management, including product information, price and applicable product data.

---

## SELL-009 – Stock Management

Validate stock updates and ensure stock availability is correctly reflected in the marketplace.

**Requirements:** BR-01, BR-02

---

## SELL-010 – Product CSV Import

Validate:

- Valid rows.
- Invalid rows.
- Required columns.
- Duplicate SKU handling.
- SKU uniqueness per seller.
- Same SKU across different sellers.
- Updates by SKU.
- Line-level validation errors.
- No silent data loss.

**Requirement:** IF-01

---

## SELL-011 – Partner API – Orders

Validate `GET /partner/v1/orders`.

Verify:

- Authentication.
- Seller-specific data.
- Pagination.
- 20 orders per page.
- Complete retrieval across pages.
- No duplicate or missing orders.

**Requirement:** IF-03

---

## SELL-012 – Partner API – Offer Update

Validate `PUT /partner/v1/offers/{id}`.

Verify valid and invalid price/stock updates and authorization.

**Requirement:** IF-03

---

## SELL-013 – Refund Approval / Rejection

Validate seller approval and rejection of buyer refund requests.

**Requirement:** BR-18

---

## SELL-014 – Exchange Approval / Rejection

Validate seller approval and rejection of buyer exchange requests.

**Requirement:** BR-18

---

# 4. Operator Scenarios

## OPER-001 – Operator Authentication

Validate operator login and role-specific access.

---

## OPER-002 – Marketplace Settings

Validate operator configuration of marketplace settings and verify changes take effect immediately.

**Requirement:** BR-23

---

## OPER-003 – Store Clock

Validate setting and resetting of the application store clock.

Use this capability to test time-dependent rules deterministically.

**Requirement:** BR-24

---

## OPER-004 – Delivery Slot Configuration

Validate delivery slot configuration and capacity settings.

Verify changes affect checkout behaviour immediately.

**Requirements:** BR-05, BR-23

---

## OPER-005 – Seller Approval

Validate seller approval workflow and verify approved sellers can perform expected seller operations.

---

## OPER-006 – Logistics Export

Validate logistics export contents.

Verify:

- UTF-8 CSV format.
- Correct column order.
- Accepted orders included.
- Shipped orders included.
- Redelivery-requested orders included.
- Ineligible statuses excluded.
- Correct buyer and delivery information.

**Requirement:** IF-02

---

# 5. Authorization & Data Isolation

## AUTH-001 – Buyer Data Isolation

Verify buyer1 cannot access buyer2 or buyer3 orders, invoices or statements.

**Requirement:** BR-21

---

## AUTH-002 – Seller Data Isolation

Verify seller1 cannot access seller2 or seller3 orders.

**Requirement:** BR-21

---

## AUTH-003 – Role-Based Access Control

Verify that Buyer, Seller and Operator accounts can access only functionality permitted for their respective roles.

---

# 6. Integration Scenarios

## INT-001 – Product CSV Integration

Validate end-to-end CSV processing from file upload through product/stock updates and validation results.

**Requirement:** IF-01

---

## INT-002 – Logistics Export Integration

Validate generation and contents of logistics export data.

**Requirement:** IF-02

---

## INT-003 – Partner API Integration

Validate seller system integration through the documented partner API, including authentication, pagination, order retrieval and offer updates.

**Requirement:** IF-03

---

# 7. Performance Scenarios

## PERF-001 – Checkout Performance

Validate that 95% of checkout requests complete within 3 seconds with approximately 30 concurrent buyers.

**Requirement:** NF-01

---

## PERF-002 – Search Performance

Validate that 95% of search requests complete within 2 seconds.

**Requirement:** NF-02

---

## PERF-003 – API Rate Limiting

Validate that the application supports up to 50 requests per second and returns HTTP 429 when the documented limit is exceeded.

**Requirement:** NF-03

Testing will remain within the assignment's permitted request-rate limits.
