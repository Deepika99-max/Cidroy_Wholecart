# WholeCart Marketplace – Load Test Conclusion

## 1. Summary

The WholeCart Marketplace APIs were tested under controlled concurrent load using Apache JMeter 5.6.3.

The test was executed with 10 concurrent users, a 10-second ramp-up period, and 6 iterations. A total of 180 API samples were generated across the Login API and Product API.

## 2. Performance Assessment

The observed throughput was **0.43 requests/second**, which remained significantly below the required maximum limit of **50 requests/second**.

JMeter reported an **error percentage of 1.0%** during the execution. This should be considered for further investigation if a higher-load or production-level performance test is planned.

The server-side `/api/metrics` endpoint reported:

* **Requests:** 0
* **5xx Errors:** 0
* **P50:** Not available
* **P95:** Not available
* **Orders in Flight:** 0
* **Application Version:** 1.0

## 3. Conclusion

The load test was completed successfully within the required throughput limit.

The observed request rate remained well below the maximum allowed rate of 50 requests/second, and no server-side HTTP 5xx errors were reported by `/api/metrics`.

However, JMeter reported a 1.0% error rate. Therefore, the load test does not establish a completely error-free result and the reported JMeter errors should be reviewed before considering the API fully performance-validated.

## 4. Final Status

**Load Test: COMPLETED**

**Throughput Requirement: PASS**

**Server 5xx Error Check: PASS**

**Overall Performance Validation: PASS WITH OBSERVATION**
