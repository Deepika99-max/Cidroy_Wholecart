# WholeCart — Test Matrix

**Document:** Test Matrix
**Application:** WholeCart Marketplace  
**Release:** Marketplace Release 1.0  
**Specification:** WC-FDD-1.0-cc34  
**Release Notes:** WC-RN-1.0-cc34  
**Prepared By:** Deepika – Senior QA Automation Engineer


## 1. Purpose

The Test Matrix provides traceability between the WholeCart business, integration, and non-functional requirements and the defined test scenarios.

It ensures that:

* Every requirement has corresponding test coverage.
* Positive and negative behaviour is covered.
* Critical business rules are validated.
* Buyer, seller, operator, authentication, integration, and performance areas are represented.
* Test execution results can be traced back to the originating requirement.
* Coverage gaps can be identified before test execution begins.

The matrix is a planning and traceability artifact. Detailed test steps, test data, expected results, and execution evidence will be maintained in the Test Specification and execution records.

---

# 2. Coverage Classification

| Classification | Description                                                                        |
| -------------- | ---------------------------------------------------------------------------------- |
| Functional     | Validates expected business functionality and user workflows                       |
| Negative       | Validates rejection of invalid, restricted, or unsupported operations              |
| Boundary       | Validates minimum, maximum, threshold, and time-bound conditions                   |
| Integration    | Validates interaction between WholeCart and external/internal interfaces           |
| API            | Validates API behaviour, contracts, authentication, pagination, and data integrity |
| Security       | Validates authorization and data isolation between roles                           |
| End-to-End     | Validates complete business workflows across multiple system components            |
| Performance    | Validates response-time, concurrency, throughput, and rate-limiting requirements   |
| Regression     | Validates existing functionality remains unaffected by the release                 |
| UI             | Validates screen-level behaviour, controls, messages, and displayed information    |

---

# 3. Test Scenario Inventory

The current test scenario catalogue is organised into the following functional areas.

| Scenario Group                 | Scenario IDs        | Area                                                |
| ------------------------------ | ------------------- | --------------------------------------------------- |
| Buyer                          | BUY-001 – BUY-025   | Buyer marketplace and order lifecycle               |
| Seller                         | SELL-001 – SELL-014 | Seller order and product management                 |
| Operator                       | OPER-001 – OPER-006 | Marketplace administration and operational controls |
| Authentication / Authorization | AUTH-001 – AUTH-003 | Authentication and role-based access                |
| Integration                    | INT-001 – INT-003   | External and system integrations                    |
| Performance                    | PERF-001 – PERF-003 | Non-functional and performance validation           |

**Total scenario inventory: 57 scenarios**

---

# 4. Business Requirement Coverage Matrix

## 4.1 Business Rules

| Requirement | Business Rule                                                                                               | Primary Scenario Coverage    | Coverage Type                       | Priority | Status  |
| ----------- | ----------------------------------------------------------------------------------------------------------- | ---------------------------- | ----------------------------------- | -------- | ------- |
| BR-01       | Stock is maintained in pieces and orders cannot exceed available stock, including concurrent ordering       | BUY-001, BUY-002             | Functional / Boundary / Concurrency | Critical | Covered |
| BR-02       | Out-of-stock products must not appear in catalogue or search results                                        | BUY-003                      | Functional / Negative               | High     | Covered |
| BR-03       | Cart quantity must be between 1 and 99                                                                      | BUY-004, BUY-005             | Boundary / Negative                 | High     | Covered |
| BR-04       | Minimum order value is ₹1,500 net per seller                                                                | BUY-006, BUY-007             | Boundary / Negative                 | Critical | Covered |
| BR-05       | Delivery slots are available for today and the next 7 days with a maximum of 5 orders per slot              | BUY-008, BUY-009             | Functional / Boundary               | Critical | Covered |
| BR-06       | Same-day delivery is restricted to eligible slots and orders placed before the configured cut-off           | BUY-010, BUY-011             | Boundary / Time-based               | Critical | Covered |
| BR-07       | Tax is calculated separately by tax rate and rounded once per rate                                          | BUY-012, BUY-013             | Functional / Calculation            | Critical | Covered |
| BR-08       | Delivery fee is ₹100 + 18% tax for seller sub-orders below ₹3,000                                           | BUY-014                      | Functional / Calculation            | High     | Covered |
| BR-09       | Order number follows the defined checkout/sub-order format                                                  | BUY-015                      | Functional / Data Validation        | Medium   | Covered |
| BR-10       | Duplicate checkout submission creates only one order                                                        | BUY-016                      | Negative / Idempotency              | Critical | Covered |
| BR-11       | Buyer can cancel eligible orders within 5 minutes and stock is restored                                     | BUY-017                      | Boundary / Functional               | Critical | Covered |
| BR-12       | Seller cancellation is immediately reflected to the buyer                                                   | SELL-001                     | Integration / Functional            | High     | Covered |
| BR-13       | Order status follows the defined lifecycle and permitted cancellation states                                | SELL-002, BUY-018            | Functional / State Transition       | Critical | Covered |
| BR-14       | Failed delivery supports redelivery through a valid delivery slot                                           | BUY-019                      | Functional / Negative               | Critical | Covered |
| BR-15       | Delivery slot can be changed until shipment                                                                 | BUY-018                      | Functional / Boundary               | Critical | Covered |
| BR-16       | Reorder adds every line from the previous order to the cart                                                 | BUY-020                      | Functional / End-to-End             | High     | Covered |
| BR-17       | Refunds and exchanges are available only for delivered orders and quantity cannot exceed ordered quantity   | BUY-021, BUY-022             | Functional / Negative / Boundary    | Critical | Covered |
| BR-18       | Refund includes applicable tax; exchange returns no money; seller approves/rejects request                  | BUY-021, BUY-022, SELL-003   | Functional / Integration            | Critical | Covered |
| BR-19       | Invoice is generated on delivery and contains required seller, buyer, line, tax, fee, and total information | BUY-023                      | Functional / Data Validation        | High     | Covered |
| BR-20       | Monthly statement includes eligible delivered orders less approved refunds and excludes cancelled orders    | BUY-024                      | Functional / Calculation            | High     | Covered |
| BR-21       | Users can access only data permitted for their role/ownership                                               | AUTH-001, AUTH-002, AUTH-003 | Security / Authorization            | Critical | Covered |
| BR-22       | Catalogue prices exclude tax while checkout displays tax and seller-specific delivery fee                   | BUY-012, BUY-014             | Functional / Calculation            | High     | Covered |
| BR-23       | Operator setting changes take effect immediately                                                            | OPER-001, OPER-002           | Functional / Configuration          | High     | Covered |
| BR-24       | Operator can control the store clock for time-dependent testing                                             | OPER-003                     | Functional / Time-based             | High     | Covered |

---

# 5. Integration Requirement Coverage

| Requirement | Interface          | Primary Scenario Coverage                     | Coverage Type                            | Priority | Status  |
| ----------- | ------------------ | --------------------------------------------- | ---------------------------------------- | -------- | ------- |
| IF-01       | Product CSV Import | SELL-004, SELL-005, SELL-006                  | Integration / Negative / Data Validation | Critical | Covered |
| IF-02       | Logistics Export   | OPER-004, OPER-005                            | Integration / Data Validation            | High     | Covered |
| IF-03       | Partner API        | SELL-007, SELL-008, INT-001, INT-002, INT-003 | API / Integration / Security             | Critical | Covered |

### IF-01 — Product CSV Import Coverage

The test coverage must validate:

* Required columns.
* UTF-8 input.
* Valid product rows.
* Product creation.
* Existing SKU update.
* Seller-specific SKU uniqueness.
* Duplicate/invalid data handling.
* Invalid row reporting.
* Line-number reporting.
* Error reason reporting.
* No silent data loss.

### IF-02 — Logistics Export Coverage

The test coverage must validate:

* Accepted orders.
* Shipped orders.
* Redelivery-requested orders.
* Exclusion of unsupported order states.
* Correct order number.
* Buyer information.
* Delivery address.
* Delivery date.
* Slot start and end times.
* Piece quantity.
* Required column order.
* UTF-8 output.
* Scheduled/hourly export behaviour.

### IF-03 — Partner API Coverage

The test coverage must validate:

* API authentication using `X-Api-Key`.
* Seller-specific data access.
* Order retrieval.
* Pagination.
* 20 orders per page.
* Complete traversal across pages.
* No duplicate orders across pages.
* No missing orders.
* Offer price update.
* Offer stock update.
* Invalid authentication.
* Unauthorized seller access.
* API response validation.

---

# 6. Non-Functional Requirement Coverage

| Requirement | Non-Functional Requirement                                                                | Scenario | Measurement                              | Priority | Status  |
| ----------- | ----------------------------------------------------------------------------------------- | -------- | ---------------------------------------- | -------- | ------- |
| NF-01       | 95% of order-submission requests must complete within 3 seconds with 30 concurrent buyers | PERF-001 | Response time / percentile / concurrency | Critical | Covered |
| NF-02       | 95% of search requests must complete within 2 seconds                                     | PERF-002 | Response time / percentile               | High     | Covered |
| NF-03       | System supports up to 50 requests/sec and returns HTTP 429 above the supported rate       | PERF-003 | Throughput / HTTP status / rate limiting | Critical | Covered |

---

# 7. Authentication and Authorization Coverage

| Scenario | Coverage Area                                                  | Requirement           | Priority |
| -------- | -------------------------------------------------------------- | --------------------- | -------- |
| AUTH-001 | Buyer authentication and buyer data isolation                  | BR-21                 | Critical |
| AUTH-002 | Seller authentication and seller-specific order access         | BR-21 / IF-03         | Critical |
| AUTH-003 | Operator authorization and restricted administrative functions | BR-21 / BR-23 / BR-24 | Critical |

The authorization coverage must ensure that:

* Buyers cannot access another buyer's orders.
* Buyers cannot access another buyer's invoices.
* Buyers cannot access another buyer's statements.
* Sellers cannot access another seller's orders.
* Seller-only operations are inaccessible to buyers.
* Operator-only operations are inaccessible to buyers and sellers.
* API-level authorization is enforced independently of UI restrictions.

---

# 8. Seller Coverage Matrix

| Scenario Group      | Area                                                      | Requirement Coverage       |
| ------------------- | --------------------------------------------------------- | -------------------------- |
| SELL-001 – SELL-003 | Order lifecycle, cancellation, refund/exchange processing | BR-12, BR-13, BR-17, BR-18 |
| SELL-004 – SELL-006 | Product and stock management / CSV import                 | BR-01, BR-02, IF-01        |
| SELL-007 – SELL-008 | Partner API / seller integration                          | IF-03, BR-21               |
| SELL-009 – SELL-014 | Seller order management and associated workflows          | BR-13, BR-17, BR-18, BR-21 |

---

# 9. Operator Coverage Matrix

| Scenario | Area                                   | Requirement Coverage | Priority |
| -------- | -------------------------------------- | -------------------- | -------- |
| OPER-001 | Marketplace configuration              | BR-23                | High     |
| OPER-002 | Delivery / operational setting changes | BR-05, BR-06, BR-23  | Critical |
| OPER-003 | Store clock control                    | BR-24                | High     |
| OPER-004 | Logistics export                       | IF-02                | High     |
| OPER-005 | Export content and data valid          |                      |          |
