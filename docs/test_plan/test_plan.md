# WholeCart Marketplace – Test Plan

**Document:** Test Plan
**Application:** WholeCart Marketplace  
**Release:** Marketplace Release 1.0  
**Specification:** WC-FDD-1.0-cc34  
**Release Notes:** WC-RN-1.0-cc34  
**Prepared By:** Deepika – Senior QA Automation Engineer

---

## 1. Objective

Assess the release readiness of the WholeCart marketplace by validating critical Buyer, Seller and Operator workflows against the Functional Design Document and approved Release Notes, with emphasis on business-critical rules, data integrity, authorization, integrations and performance.

The test approach is **risk-based**, prioritizing scenarios that can result in financial loss, incorrect orders, stock overselling, data exposure or release-blocking workflow failures.

---

## 2. Scope & Risk Priorities

| **Priority** | **Focus Areas** |
|---|---|
| **P0 – Critical** | Login/access control, stock integrity, multi-seller checkout, ₹2,000 minimum order, tax calculation, delivery fee, order creation/idempotency, cancellation, order lifecycle, refund/exchange, invoice accuracy, buyer/seller data isolation |
| **P1 – High** | Search/catalogue, delivery slot rules, same-day cut-off, slot capacity, reorder, statements, seller product/stock management, CSV import, partner API, logistics export |
| **P2 – Medium** | Inquiries, non-critical validation and usability scenarios |

### Primary User Journeys

**Buyer:**  
Login → Catalogue/Search → Cart → Multi-seller Checkout → Delivery Slot → Order → Cancellation/Slot Change/Redelivery → Reorder → Refund/Exchange → Invoice/Statement

**Seller:**  
Login → Orders → Accept → Ship → Deliver/Delivery Failed → Redelivery → Refund/Exchange → Product/Stock Management

**Operator:**  
Login → Marketplace Settings → Store Clock/Slot Configuration → Seller Approval → Logistics Export

---

## 3. Test Approach

| **Area** | **Approach** |
|---|---|
| Functional | Validate business rules, positive/negative paths and state transitions against approved requirements. |
| Boundary | Focus on ₹2,000 minimum order, ₹3,000 delivery-fee threshold, 1–99 quantity, 5-minute cancellation, 10:00 cut-off, delivery-date window and slot capacity. |
| UI | Validate user workflows, displayed calculations, validations, statuses and role-specific access. |
| API | Validate status codes, payloads, business rules, authentication/authorization, pagination, error handling and data consistency. |
| Integration | Validate CSV import, logistics export and partner API behaviour. |
| End-to-End | Validate cross-role workflows from buyer order creation through seller fulfilment and operator processes. |
| Regression | Prioritize existing ordering functionality and newly introduced marketplace capabilities. |
| Performance | Validate checkout and search response-time requirements and rate-limit behaviour within the permitted environment. |

---

## 4. Test Design & Data Strategy

Testing will use **equivalence partitioning, boundary-value analysis, negative testing, state-transition testing, data/role isolation and risk-based prioritization**.

Key boundary data will include:

- ₹1,999.99 / ₹2,000 / ₹2,000.01 minimum-order boundary
- ₹2,999.99 / ₹3,000 / above ₹3,000 delivery-fee boundary
- Quantity 0 / 1 / 99 / 100
- Cancellation before, at and after 5:00 minutes
- Same-day delivery before and at/after 10:00
- Delivery dates from today through today + 7
- Available, zero-stock and insufficient-stock products
- Single-seller and multi-seller checkouts
- Valid and invalid CSV rows
- Authorized and unauthorized buyer/seller/API access

Test data will be created and controlled using the provided accounts and supported application/API interfaces.

---

## 5. Entry & Exit Criteria

### Entry Criteria

- Application and required test accounts are accessible.
- FDD, Release Notes and API documentation are available and reviewed.
- Test environment and required tools are ready.
- Required test data can be created or is available.

### Exit Criteria

Testing is complete when:

- P0 scenarios have been executed.
- Critical Buyer, Seller and Operator journeys have been validated.
- Planned API/UI regression coverage is completed.
- Defects are documented with reproducible evidence.
- Performance results are analysed against applicable requirements.
- Remaining risks and known limitations are documented.
- A release recommendation can be made based on objective evidence.

---

## 6. Key Risks & Mitigation

| **Risk** | **Mitigation** |
|---|---|
| Financial calculation defects | Validate tax, delivery fee, totals and invoice calculations using independent expected-value calculations. |
| Stock overselling | Test stock boundaries and controlled concurrent checkout/API requests. |
| Invalid order state transitions | Validate the complete order state machine and role-specific actions. |
| Data exposure | Verify buyer/seller/API authorization and cross-account access. |
| Time-dependent behaviour | Use the operator store-clock capability for deterministic boundary testing. |
| Limited assessment time | Execute P0 first, followed by P1/P2 based on remaining time and risk. |
| Environment-dependent performance | Record test conditions, workload and environment before interpreting results. |

---

## 7. Release Decision

The final recommendation will be **GO / GO WITH RISKS / NO-GO**, based on:

- P0/P1 scenario results
- Severity and business impact of open defects
- Financial/data-integrity risks
- API/integration stability
- Regression results
- Performance results
- Remaining accepted risks

**Release-blocking examples:** incorrect order totals, tax/invoice errors, stock overselling, duplicate order creation, unauthorized data access, or critical order-state failures.

---

## 8. Out of Scope

- Inquiry attachments – **CR-11 deferred to next release**
- Hosting/infrastructure attacks
- Testing outside the provided application web interface, documented APIs and partner API
- Undocumented external integrations