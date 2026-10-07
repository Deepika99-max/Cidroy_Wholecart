# WholeCart Marketplace

**Document:** Questions & Assumptions Log  
**Application:** WholeCart Marketplace  
**Release:** Marketplace Release 1.0  
**Specification:** WC-FDD-1.0-cc34  
**Release Notes:** WC-RN-1.0-cc34  
**Prepared By:** Deepika – Senior QA Automation Engineer

---

## 1. Purpose

This document captures requirement ambiguities, questions that would normally be clarified with the Product Owner or Business Analyst, and the assumptions adopted for testing when clarification is unavailable during the assessment.

The Functional Design Document (FDD) is treated as the primary specification. Approved changes documented in the Release Notes take precedence wherever they conflict with the FDD.

---

## 2. Requirement Precedence

The following order is used when interpreting requirements:

1. **Approved Release Notes**
2. **Functional Design Document**
3. **Observed application behaviour**, provided it does not conflict with an approved requirement

If the application behaviour differs from an approved requirement, the behaviour is considered a potential defect and is logged for investigation.

---

## 3. Questions & Assumptions

| ID | Area | Question  | Assumption  | Impact |
|---|---|---|---|---|
| QA-001 | Minimum Order | The FDD specifies a ₹1,500 minimum order, while CR-07 changes the minimum order value. Which value applies to this release? | CR-07 supersedes BR-04. The minimum order is **₹2,000 net per seller**. | Checkout |
| QA-002 | Multi-Seller Checkout | Is the minimum order amount evaluated independently for each seller? | Yes. Each seller sub-order must independently meet the ₹2,000 net minimum. | Checkout |
| QA-003 | Delivery Fee | Is the ₹3,000 threshold for the delivery fee evaluated per seller or across the complete checkout? | The threshold is evaluated **per seller sub-order**. | Checkout / Invoice |
| QA-004 | Delivery Fee Tax | Does the ₹100 delivery fee attract 18% tax? | Yes. The applicable delivery charge is ₹100 + 18% tax = **₹118**. | Checkout / Invoice |
| QA-005 | Delivery Date Range | BR-05 allows delivery today and the next seven days. Does this represent eight calendar dates in total? | Yes. The selectable range is **today through today + 7 calendar days**, inclusive. | Checkout |
| QA-006 | Same-Day Cut-off | BR-06 specifies a 10:00 cut-off for same-day delivery. Is 10:00 itself allowed? | The cut-off is treated as exclusive. Same-day delivery is available **before 10:00** and unavailable at or after 10:00. | Checkout |
| QA-007 | Delivery Slot Capacity | Is the maximum of five orders applied globally to a slot? | The configured slot capacity is treated as a marketplace-level limit unless the application demonstrates otherwise. | Checkout / Operator |
| QA-008 | Cancellation Window | BR-11 allows cancellation up to and including 5 minutes 0 seconds. What happens immediately after this boundary? | Cancellation is allowed at exactly **5:00 minutes**, provided the order has not shipped. Cancellation is rejected after the boundary. | Orders |
| QA-009 | Duplicate Order Submission | What should happen if the buyer double-clicks Place Order or submits the same checkout more than once? | Only **one order** should be created for the same checkout, as required by BR-10. | Checkout / Orders |
| QA-010 | Stock Concurrency | How should simultaneous checkout attempts for limited stock be handled? | The system must prevent the total quantity ordered from exceeding available stock, including concurrent requests. | Catalogue / Checkout |
| QA-011 | Out-of-Stock Catalogue | Should a product disappear from both catalogue and search immediately after stock reaches zero? | Yes. Products with zero stock must not appear in catalogue or search results. | Catalogue / Search |
| QA-012 | Tax Calculation | At what level should tax be rounded? | Tax is calculated separately for each tax rate on the summed net value and rounded **half-up to the nearest paisa once per rate**. | Checkout / Invoice |
| QA-013 | Refund Quantity | Can cumulative partial refund quantities exceed the original ordered quantity? | No. The cumulative refunded quantity must never exceed the quantity originally ordered for a line. | Refund |
| QA-014 | Exchange Quantity | Can cumulative exchange quantities exceed the originally ordered quantity? | No. The total exchanged quantity must not exceed the quantity originally ordered. | Exchange |
| QA-015 | Exchange Payment | Does an approved exchange result in a monetary refund? | No. An exchange provides replacement goods and returns no money. | Exchange |
| QA-016 | Invoice – CR-09 | What additional information must appear on every tax invoice? | Every invoice must display **Supply Type = B2B** and **Place of Supply = buyer GSTIN-derived state name and state code**. | Invoice |
| QA-017 | Place of Supply | How should the state information be displayed on the invoice? | The expected format is state name and state code, for example **Goa (30)**, derived from the buyer's GSTIN. | Invoice |
| QA-018 | Buyer Data Isolation | Can a buyer access another buyer's orders, invoices or statements? | No. Buyers must only access their own data. | Authorization |
| QA-019 | Seller Data Isolation | Can a seller access orders belonging to another seller? | No. Sellers must only access their own orders. | Authorization |
| QA-020 | Reorder | Does each use of Reorder add every line from the selected historical order to the cart? | Yes. Every line from the previous order is added, and the feature may be used multiple times. | Orders / Cart |
| QA-021 | Monthly Statement | How are approved refunds represented in the monthly statement? | Delivered orders for the month are included and approved refunds are deducted. Cancelled orders are excluded. | Statements |
| QA-022 | Invoice Generation | When should an invoice become available? | An invoice is expected to be issued when the corresponding order is delivered. | Invoice |
| QA-023 | Order Numbering | How should multi-seller order numbers be represented? | One checkout generates seller-specific sub-orders using the format defined in BR-09, e.g. `WC-01005-2`. | Orders |
| QA-024 | Operator Settings | When should a setting changed by an operator take effect? | Operator configuration changes are expected to take effect immediately. | Operator |
| QA-025 | Store Clock | How should time-dependent rules be tested during the assessment? | The operator's store-clock functionality will be used to simulate required times for cut-off and cancellation boundary testing. | Time-Based Testing |
| QA-026 | Partner API Pagination | How should all seller orders be validated through the partner API? | Every page returned by `GET /partner/v1/orders` must be processed. Each seller order should appear exactly once across all pages. | Partner API |
| QA-027 | CSV Import | How should invalid product CSV rows be handled? | Valid rows should be imported/updated while every invalid row must be reported with its line number and reason. No invalid row should be silently dropped. | CSV Import |
| QA-028 | Logistics Export | Which orders should be included in the logistics export? | Orders with **accepted, shipped, or redelivery-requested** status should be included. | Logistics |
| QA-029 | Inquiry Attachments | Should inquiry attachments be tested in this release? | No. CR-11 explicitly defers inquiry attachments to the next release. | Inquiry |
| QA-030 | Catalogue Pricing | Are catalogue prices displayed before tax? | Yes. Catalogue prices are treated as tax-exclusive, while checkout displays applicable tax and delivery fees. | Catalogue / Checkout |

---

## 4. Explicitly Out of Scope

The following items are excluded from testing for this release:

| ID | Item | Reason |
|---|---|---|
| OOS-001 | Inquiry Attachments | CR-11 explicitly defers this functionality to the next release. |
| OOS-002 | Hosting / Infrastructure Attacks | The assignment explicitly prohibits attacks against the hosting infrastructure. |
| OOS-003 | Unapproved External Interfaces | Testing is limited to the application's web screens, documented APIs and partner API. |

---

## 5. Key Requirement Decisions Used During Testing

The following decisions have a direct impact on test execution:

### 5.1 Minimum Order

**Applicable requirement:** CR-07

The minimum order value is:

> **₹2,000 net per seller**

The older ₹1,500 value from BR-04 is not used for this release.

---

### 5.2 Delivery Fee

**Applicable requirement:** BR-08

For each seller sub-order with net value below ₹3,000:

- Delivery fee = ₹100
- Delivery fee tax = 18%
- Total delivery charge including tax = **₹118**

The threshold is evaluated independently for each seller.

---

### 5.3 Invoice – CR-09

Every tax invoice must contain:

- Supply Type: **B2B**
- Place of Supply:
    - State name
    - State code
- Seller name and GSTIN
- Buyer name, GSTIN and address
- Invoice date
- Order number
- Order line details
- Tax by applicable rate
- Delivery fee where applicable
- Final invoice total

---

### 5.4 Deferred Requirement

**CR-11 – Inquiry Attachments**

This functionality is deferred to the next release and therefore is not included in the current release validation scope.

---

## 6. Assumption Review

All assumptions documented above will be validated against:

- Functional Design Document
- Release Notes
- Application behaviour
- API behaviour
- Test execution results

Any observed behaviour that contradicts an approved requirement will be evaluated and, where appropriate, recorded as a defect.