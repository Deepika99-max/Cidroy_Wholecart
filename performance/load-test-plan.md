# WholeCart Marketplace – Load Test Plan

## 1. Test Objective

Validate the performance and stability of the WholeCart Marketplace APIs under controlled concurrent load.

## 2. Application

**Application:** WholeCart Marketplace
**Base URL:** `http://13.200.185.1/c/deepika-51b4ee`

## 3. Tool

**Load Testing Tool:** Apache JMeter

## 4. APIs Under Test

### Login API

```text
POST /c/deepika-51b4ee/api/login
```

Request:

```json
{
  "username": "",
  "password": ""
}
```

### Product API

```text
GET /c/deepika-51b4ee/api/products/513
```

## 5. Load Configuration

| Parameter                  |                    Value |
| -------------------------- | -----------------------: |
| Concurrent Users           |                       10 |
| Ramp-up Period             |               10 seconds |
| Test Duration              | Approximately 60 seconds |
| Maximum Allowed Throughput |       50 requests/second |
| Target Throughput          | Below 50 requests/second |

## 6. Performance Metrics

The following metrics will be recorded:

* Total requests
* Average response time
* Minimum response time
* Maximum response time
* 90th percentile response time
* Throughput
* Error percentage

## 7. Server Metrics

The following endpoint will be checked before and after the load test where available:

```text
GET /c/deepika-51b4ee/api/metrics
```

## 8. Entry Criteria

* Application is accessible.
* Login API is working.
* Product API is working.
* Test user credentials are valid.
* Load remains below 50 requests/second.

## 9. Exit Criteria

The test will be completed after the planned load has been executed and the following have been recorded:

* Response times
* Throughput
* Error rate
* Server metrics
* Observed application behavior

## 10. Success Criteria

* Throughput remains below 50 requests/second.
* No unexpected API failures are observed.
* Error rate is recorded and analyzed.
* API response times remain stable during the test.
* No application instability is observed during the test.
